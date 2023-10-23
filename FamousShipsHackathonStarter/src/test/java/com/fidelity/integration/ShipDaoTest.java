package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


import com.fidelity.business.Ship;

/**
 * DaoTest is an integration test for ShipDaoMyBatisImpl.
 * 
 * To verify that the DAO is actually working, we'll need to query the database 
 * directly, so we'll use Spring's JdbcTestUtils class, which has methods like 
 * countRowsInTable() and deleteFromTables().
 *
 * @author ROI Instructor
 *
 */
@SpringBootTest
@Transactional
class ShipDaoTest {

	@Autowired
	private ShipDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void daoNotNull() {
		assertNotNull(dao);
	}
	
	@Test
	void getAllShipsReturnsList() {
		assertTrue(dao.queryAllShips().size() > 0);
	}
	
	@Test
	void testGetAllShipsTableIsEmpty() {
		deleteFromTables(jdbcTemplate, "famousships");

		assertTrue(dao.queryAllShips().size() == 0);
	}

	@Test
	void testGetShipPresent() {
		Ship ship = dao.queryShipById(1);
		
		assertNotNull(ship);
	}

	@Test
	void testGetShipNotPresent() {
		Ship ship = dao.queryShipById(99);
		assertTrue(ship == null);
	}
	
	@Test
	void getCaptainPresent() {
		String captain = dao.queryCaptainByShipName("RMS Titanic");
		assertNotNull(captain);
	}
	
	@Test
	void getCaptainNotPresent() {
		assertEquals(null, dao.queryCaptainByShipName("RMS"));
	}
}
