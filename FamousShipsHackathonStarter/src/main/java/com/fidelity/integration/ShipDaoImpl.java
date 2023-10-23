package com.fidelity.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Ship;
import com.fidelity.integration.mapper.ShipMapper;

@Repository
public class ShipDaoImpl implements ShipDao {
	
	@Autowired
	private ShipMapper mapper;

	@Override
	public List<Ship> queryAllShips() {
		List<Ship> ships = mapper.getAllShips();
		return ships;
	}

	@Override
	public Ship queryShipById(int id) {
		Ship ship = mapper.getShipById(id);
		return ship;
	}

	@Override
	public String queryCaptainByShipName(String name) {
		String captain = mapper.getCaptainByShipName(name);
		return captain;
	}
}
