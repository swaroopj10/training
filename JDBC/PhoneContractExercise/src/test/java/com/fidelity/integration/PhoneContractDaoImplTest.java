package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.fidelity.model.PhoneContract;

class PhoneContractDaoImplTest {
	
	private JdbcTemplate jdbcTemplate;
	private DbTestUtils dbTestUtils;
	private PhoneContractDao dao;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;

	@BeforeEach
	void setUp() throws Exception {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
		dao = new PhoneContractDaoImpl(dataSource);
		transactionManager = new TransactionManager(dataSource);
		transactionManager.startTransaction(); 
		dbTestUtils = new DbTestUtils(connection);		
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
	}

	@AfterEach
	void tearDown() throws Exception {
		transactionManager.rollbackTransaction();
		dataSource.shutdown();
	}

	@Test
	void notNull() {
		assertNotNull(dao.getFullPhoneContracts());
	}
	
	@Test
	void correctTableSize() {
		assertTrue(dao.getFullPhoneContracts().size() > 1);
	}
	
	@Test
	void correctTableSizeWithId() {
		assertTrue(dao.getPhoneContractByID(3).size() > 1);
	}
	
	@Test
	@DisplayName("Positive - should create dao object")
	void shouldCreate() {
		assertNotNull(dao);
	}
	
	@Test
	@DisplayName("Negative - should not return null as list")
	void shouldNotReturnNull() {
		List phoneContractsList = dao.getFullPhoneContracts();
		assertNotNull(phoneContractsList);
	}
	
	@Test
	@DisplayName("Positive - should have some records in the list")
	void shouldHaveSomeDataInTheList() {
		List phoneContractsList = dao.getFullPhoneContracts();
		assertTrue(phoneContractsList.size()>0);
	}
	
	@Test
	@DisplayName("Negative - Should throw Exception When Id not null")
	void shouldThrowExceptionWhenIdNotFound() {
		Exception exception = assertThrows(DatabaseException.class, ()->{
			
			List phoneContractsList = dao.getPhoneContractByID(1000);
			
		});
		assertEquals(exception.getMessage(), "No phone contract with that ID found");
		
	}
	
	@Test
	@DisplayName("Positive - By id should have some records in the list")
	void shouldHaveSomeDataInTheListWhenById() {
		List phoneContractsList = dao.getPhoneContractByID(1);
		assertTrue(phoneContractsList.size()>0);
	}
	
	@Test
	@DisplayName("Positive - Get by id phone contracts should have correct Id")
	void shouldHaveCorrectId() {
		
			List<PhoneContract> phoneContractsList = dao.getPhoneContractByID(1);
			for(PhoneContract phoneContract:phoneContractsList) {
				assertEquals(phoneContract.getPhoneContractId(), 1);
			}
			
	}
	
}
