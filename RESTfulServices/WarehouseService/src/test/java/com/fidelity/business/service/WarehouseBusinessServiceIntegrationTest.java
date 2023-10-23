package com.fidelity.business.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.test.context.jdbc.Sql;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Gadget;
import com.fidelity.business.Widget;

/**
 * WarehouseBusinessServiceIntegrationTest is an integration test for WarehouseBusinessServiceImpl.
 * 
 * Notice that the database schema and data scripts are run
 * after setting the DataSource. 
 * The database scripts are in the folder: src/test/resources/
 * This guarantees that the database is in a known state before the tests are run.
 *  
 * To verify that the DAO is actually working, we'll need to query the database 
 * directly, so we'll use Spring's JdbcTestUtils class, which has methods like 
 * countRowsInTable() and deleteFromTables().
 *
 * Notice the use of @Transactional to automatically rollback 
 * any changes to the database that may be made in a test.
 *
 * Note that Spring Boot needs to find an application class in order to scan
 * for components. The trivial class com.fidelity.TestApplication in src/test/java 
 * contains the @SpringBootApplication annotation, which triggers the component scan.
 * 
 * @author ROI Instructor
 *
 */

// TODO: add the necessary Spring annotations to:
//       1. define this class as a Spring Boot test
//       2. enable automatic transaction management so database changes 
//          are rolled back after every test case
// HINT: See slide 3-20


class WarehouseBusinessServiceIntegrationTest {
	// TODO: add an autowired field for the business service being tested


	
	@Autowired
	private JdbcTemplate jdbcTemplate;  // for executing SQL queries
	
	// TODO: define a List of all widgets in the database

	// Because the test database is tiny, we can check all products.
	// If the database was larger, we could just spot-check a few products.
	
	// TODO: write integration tests to verify the operation of the
	//       WarehouseBusinessService methods:
	//			findAllWidgets()
	//			findWidgetById()
	//			removeWidget()
	//			addWidget()
	//			modifyWidget()
	// HINT: the tests will be similar to the DAO integration test cases
	
	@Test
	void testGetAllWidgets() {
		fail("Complete this test case!");
	}

	@Test
	void testFindWidgetById() {
		fail("Complete this test case!");
	}

	@Test
	void testDeleteWidget() {
		fail("Complete this test case!");
	}
	
	@Test
	void testInsertWidget() {
		fail("Complete this test case!");
	}
	
	@Test
	void testUpdateWidget() {
		fail("Complete this test case!");
	}

	// ***** Utility Method to Load a Widget from the Database *****

	private Widget loadWidgetFromDb(int id) {
		String sql = "select * from widgets where id = " + id;
		
		return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> 
			new Widget(rs.getInt("id"), rs.getString("description"), rs.getDouble("price"), 
					   rs.getInt("gears"), rs.getInt("sprockets")));
	}

}
