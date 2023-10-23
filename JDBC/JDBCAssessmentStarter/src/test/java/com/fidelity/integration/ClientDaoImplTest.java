package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import java.sql.Connection;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import com.fidelity.model.Client;
import com.fidelity.model.ClientRisk;

class ClientDaoImplTest {
	
	private JdbcTemplate jdbcTemplate;
	private DbTestUtils dbTestUtils;
	private ClientDao dao;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;

	@BeforeEach
	void setUp() throws Exception {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
		dao = new ClientDaoImpl(dataSource);
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
	@DisplayName("Positive - should create dao object")
	void shouldCreate() {
		assertNotNull(dao);
	}
	
	@Test
	@DisplayName("Negative - should not return null on calling the get method")
	void shouldNotReturnNull() {
		assertNotNull(dao.getClients());
	}
	
	@Test
	@DisplayName("Positive - should have entries in the list")
	void shouldHaveSomeDataInTheList() {
		assertTrue(dao.getClients().size() > 0);
	}
	
	@Test
	@DisplayName("Positive - list returned in order of client id") 
	void shouldReturnInOrderOfClientId() {
		int id = 1;
		List<Client> list = dao.getClients();
		assertEquals(id, list.get(0).getClientId());
	}
	
	@Test
	@DisplayName("Negative - Inserting null client")
	void insertNullClient() {
		assertThrows(NullPointerException.class, ()-> {
			dao.insertClient(null);
		});
	}
	
	@Test
	@DisplayName("Negative - insert with existing client id")
	void insertWithExistingClientId() {
	    Client client = new Client(5, "Tony Stark", ClientRisk.POLITICAL, "+455203172212");
	    assertThrows(DatabaseException.class, ()-> {
	    	dao.insertClient(client);
	    });
	}
	
	@Test
	@DisplayName("Negative - adding the same client twice")
	void insertDuplicateClient() {
		Client client = new Client(7, "Tony Stark", ClientRisk.POLITICAL, "+455203172212");
		dao.insertClient(client);
		assertThrows(DatabaseException.class, ()-> {
			dao.insertClient(client);
		});
	}
	
	@Test
	@DisplayName("Negative - adding row with negative id")
	void insertNegativeId() {
		Client client = new Client(-1, "Tony Stark", ClientRisk.POLITICAL, "+455203172212");
		assertThrows(DatabaseException.class, ()-> {
			dao.insertClient(client);
		});
	}
	
	@Test
	@DisplayName("Negative - adding row with null name")
	void insertNullName() {
		Client client = new Client(7, null, ClientRisk.POLITICAL, "+455203172212");
		assertThrows(DatabaseException.class, ()-> {
			dao.insertClient(client);
		});
	}
	
	@Test
	@DisplayName("Negative - adding row with null client risk")
	void insertNullClientRisk() {
		Client client = new Client(7, "Tony Stark", null, "+455203172212");
		assertThrows(NullPointerException.class, ()-> {
			dao.insertClient(client);
		});
	}
	
	@Test
	@DisplayName("Positive - succesful insert with all fields")
	void testInsertClientIncrementsTableSize() {
	    int oldSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "aa_client");
	    Client client = new Client(7, "Tony Stark", ClientRisk.POLITICAL, "+455203172212");
	    dao.insertClient(client);

	    int newSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "aa_client");

	    assertEquals(oldSize + 1, newSize);
	}
	
	@Test
	@DisplayName("Positive - row exists after insertion with the given condition")
	void insertCLientTableValid() {
		assertEquals(0, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "aa_client", "client_id=7"));
		Client client = new Client(7, "Tony Stark", ClientRisk.POLITICAL, "+455203172212");
		
		dao.insertClient(client);
		
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "aa_client", "client_id=7"));
	}
	
	@Test
	@DisplayName("Positive - adding the client with null work number")
	void insertClientWithoutPhoneNumber() {
		int oldSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "aa_client");
	    Client client = new Client(7, "Tony Stark", ClientRisk.POLITICAL, null);
	    
	    dao.insertClient(client);

	    int newSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "aa_client");
	    assertEquals(oldSize + 1, newSize);	
	}
	
	@Test
	@DisplayName("Adding the client with null work number no change in phone number table")
	void insertClientWithoutPhoneNumberTableChange() {
		int oldSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "aa_client_phone");
	    Client client = new Client(7, "Tony Stark", ClientRisk.POLITICAL, null);
	    dao.insertClient(client);

	    int newSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "aa_client_phone");

	    assertEquals(oldSize, newSize);	
	}
	
	@Test
	@DisplayName("Negative - deleting client that does not exist")
	void deleteClientWithWrongId() {
		assertThrows(DatabaseException.class, ()-> {
			dao.deleteClient(9);
		});
	}
	
	@Test
	@DisplayName("Negative - deleting client twice")
	void deleteClientTwice() {
		dao.deleteClient(3);
		assertThrows(DatabaseException.class, ()-> {
			dao.deleteClient(3);
		});
	}
	
	@Test
	@DisplayName("Positive - deleting client delta case")
	void testDeleteClient()  {
		int oldSize = countRowsInTable(jdbcTemplate, "aa_client");
		dao.deleteClient(2);
		int newSize = countRowsInTable(jdbcTemplate, "aa_client");
		assertEquals(oldSize - 1, newSize);
	}
	
	@Test
	@DisplayName("Positive - deleting client removes the row from table")
	void testDeleteClientRow(){
		dao.deleteClient(1);
		int rowsWithDeletedId=JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "aa_client","client_id=1");
		assertEquals(0,rowsWithDeletedId);

	}
	
	@Test
	@DisplayName("Positive - delete row from phone number table")
	void testDeleteClientPhoneNumber() {
		dao.deleteClient(1);
		int rowsWithDeletedId=JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "aa_client_phone","client_id=1");
		assertEquals(0,rowsWithDeletedId);
	}
	
	@Test
	@DisplayName("Deleting client with no phone number") 
	void deleteClientWithNoPhoneNumber() {
		int oldSize = countRowsInTable(jdbcTemplate, "aa_client_phone");
		dao.deleteClient(5);
		int newSize = countRowsInTable(jdbcTemplate, "aa_client_phone");
		assertEquals(oldSize, newSize);
	}
}