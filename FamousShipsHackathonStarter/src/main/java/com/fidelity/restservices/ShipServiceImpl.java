package com.fidelity.restservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Ship;
import com.fidelity.integration.ShipDao;

@Service
@Transactional
public class ShipServiceImpl implements ShipService {
	
	@Autowired
	private ShipDao dao;

	@Override
	public List<Ship> queryAllShips() {
		List<Ship> ships;
		try {
			ships = dao.queryAllShips();
		} catch (Exception e) {
			String msg = "Error querying all Ships in the Ship database.";
			throw new ShipDatabaseException(msg, e);
		}
		return ships;
	}

	@Override
	public Ship queryShipById(int id) {
		if (id < 1) {
			throw new ShipDatabaseException("invalid ship id " + id);
		}
		Ship ship = null;
		try {
			ship = dao.queryShipById(id);
		} catch (Exception e) {
			String msg = "Error querying all Ships in the Ship database.";
			throw new ShipDatabaseException(msg, e);
		}
		return ship;
	}

	@Override
	public String queryCaptainByShipName(String name) {
		if (name == null) {
			throw new ShipDatabaseException("invalid ship name " + name);
		}
		String captain = null;
		try {
			captain = dao.queryCaptainByShipName(name);
		} catch (Exception e) {
			String msg = "Error querying all Ships in the Ship database.";
			throw new ShipDatabaseException(msg, e);
		}
		return captain;
	}
}
