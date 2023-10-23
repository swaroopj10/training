package com.fidelity.integration;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fidelity.model.Car;
import com.fidelity.model.CarBodyType;
import com.fidelity.model.CarEngine;
import com.fidelity.model.CarEngineType;

public class CarDaoImpl implements CarDao {
	
	private DataSource dataSource;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public CarDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private void handleDatabaseException(String message, SQLException e) {
        e.printStackTrace();
        logger.error(message, e);
        throw new DatabaseException(message, e);
    }

    private void closeConnectionAndStatement(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error("Cannot close statement", e);
            }
        }
    }

	private List<Car> executeQuery(String sql, Object... params ) {
		List<Car> carList = new ArrayList<>();
		Car car;
		CarEngine carEngine = null;
		PreparedStatement statement = null;
		Connection connection = null;

		try {
			connection = dataSource.getConnection();
			statement = connection.prepareStatement(sql);

			for (int i = 0; i < params.length; i++) {
				statement.setObject(i + 1, params[i]);
			}

			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String make = resultSet.getString("make");
				String model = resultSet.getString("model");
				LocalDate manufactureDate = resultSet.getDate("MANUFACTURE_DATE").toLocalDate();
				String bodyTypeName = resultSet.getString("body_type_name");
				int engineType = resultSet.getInt("ENGINE_TYPE");
				if (!resultSet.wasNull()) {
					CarEngineType carEngineType = CarEngineType.of(engineType);
					float horsepower = resultSet.getFloat("HORSEPOWER");
					float engineSize = resultSet.getFloat("ENGINE_SIZE_L");
					if (resultSet.wasNull()) {
						engineSize = 0.0f;
					}
					carEngine = new CarEngine(id, carEngineType, engineSize, horsepower);
				}
				car = new Car(id, make, model, bodyTypeName, carEngine, manufactureDate);
				carList.add(car);
			}

		} catch (SQLException e) {
			handleDatabaseException("Cannot execute SQL query", e);
		} finally {
			closeConnectionAndStatement(connection, statement);
		}
		return carList;
	}
	
	@Override
	public List<Car> getCars() {
		String sql = "SELECT c.id,c.make,c.model,cbt.body_type_name,c.manufacture_date,ce.engine_type,ce.horsepower,ce.engine_size_l FROM e_car c LEFT JOIN e_car_engine ce ON ce.id = c.id JOIN e_car_body_type cbt ON cbt.body_type = c.body_type ORDER BY c.id";
		return executeQuery(sql);
		
	}

	@Override
	public void insertCar(Car car) {
		Objects.requireNonNull(car);
		insertCarTable(car);
		if (car.getEngine() != null) {
			insertCarEngineTable(car);
		}
	}
	
	private void insertCarTable(Car car) {
		String carSql = "INSERT INTO e_car(id, make, model, body_type, manufacture_date) VALUES (?,?,?,?,?)";
		CarBodyType bodyType = CarBodyType.of(car.getBodyTypeName());
		int bodyTypeNumber = bodyType.getCode();
		executeUpdate(carSql, 
				car.getId(),
				car.getMake(),
				car.getModel(),
				bodyTypeNumber,
				Date.valueOf(car.getManufactureDate()));
	}
	
	private void insertCarEngineTable(Car car) {
		String engineSql = "INSERT INTO e_car_engine(id, engine_type, engine_size_l, horsepower) VALUES (?,?,?,?)";
		CarEngine carEngine = new CarEngine();
		carEngine = car.getEngine();
		float engineSize;
		if (carEngine.getEngineSizeInLiters() > 0.0f) {
			engineSize = carEngine.getEngineSizeInLiters();
		} else {
			 engineSize = Types.FLOAT;
		}
		executeUpdate(engineSql, 
				carEngine.getCarId(),
				carEngine.getEngineType().getCode(),
				engineSize,
				carEngine.getHorsepower());
	}
	
	private void executeUpdate(String sql, Object... params) {
        PreparedStatement statement = null;
        Connection connection = null;
        int rows;
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
            
           rows = statement.executeUpdate();
           if (rows == 0) {
				throw new DatabaseException("there is no car with the given id ");
			}
        } catch (SQLException e) {
            handleDatabaseException("Cannot execute SQL query", e);
        } finally {
            closeConnectionAndStatement(connection, statement);
        }
    }

	@Override
	public void updateCar(Car car) {
		Objects.requireNonNull(car);
		updateCarTable(car);
		if (car.getEngine() != null) {
			updateCarEngineTable(car);
		}
	}

	public void updateCarTable(Car car) {
		String carQuery = "UPDATE e_car SET make = ?, model = ?, body_type = ? WHERE id = ?";
		CarBodyType bodyType = CarBodyType.of(car.getBodyTypeName());
		int bodyTypeNumber = bodyType.getCode();
		executeUpdate(carQuery, 
				car.getMake(),
				car.getModel(),
				bodyTypeNumber,
				car.getId());
		
	}
	
	public void updateCarEngineTable(Car car) {
		String engineQuery = "UPDATE e_car_engine SET engine_type = ?, horsepower = ? WHERE id = ?";
		CarEngine carEngine = new CarEngine();
		carEngine = car.getEngine();
		executeUpdate(engineQuery,
				carEngine.getEngineType().getCode(),
				carEngine.getHorsepower(),
				carEngine.getCarId());
	}

	@Override
	public void deleteCar(int carId) {
		if(!carEngineExists(carId)) {
			deleteCarTable(carId);
		} else {
			deleteCarEngineTable(carId);
			deleteCarTable(carId);
		}
	}
	
	private void deleteCarEngineTable(int carId) {
		String engineSql = "DELETE FROM e_car_engine WHERE id = ?";
		executeUpdate(engineSql, carId);	
	}

	public void deleteCarTable(int carId) {
		String carSql = "DELETE FROM e_car WHERE id = ?";
		executeUpdate(carSql, carId);
	}
	
	private boolean carEngineExists(int carId) {
	    String query = "SELECT COUNT(*) FROM e_car_engine WHERE id = ?";
	    try (Connection connection = dataSource.getConnection();
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, carId);
	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                int rowCount = resultSet.getInt(1);
	                return rowCount > 0; 
	            }
	        }
	    } catch (SQLException e) {
	        handleDatabaseException("Error checking if car exists", e);
	    }
	    return false; 
	}
}
