package com.fidelity.restcontroller;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;
import static org.springframework.test.jdbc.JdbcTestUtils.dropTables;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.util.UriComponentsBuilder;

import com.fidelity.business.Ship;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql({"classpath:dev-schema.sql", "classpath:dev-data.sql"})
class ShipControllerE2eTest {
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void getAllShipsOkStatus() {
		String requestUrl = "/ships";
		ResponseEntity<Ship[]> response = restTemplate.getForEntity(requestUrl, Ship[].class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getAllShipsListSize() {
		int shipCount = countRowsInTable(jdbcTemplate, "famousships");
		
		String requestUrl = "/ships";
		ResponseEntity<Ship[]> response = restTemplate.getForEntity(requestUrl, Ship[].class);
		
		Ship[] responseShips = response.getBody();
		assertEquals(shipCount, responseShips.length); 
	}
	
	@Test
	public void testQueryForAllShips_NoShipsInDb() {
		deleteFromTables(jdbcTemplate, "famousships");
		
		String requestUrl = "/ships";
		ResponseEntity<Ship[]> response = restTemplate.getForEntity(requestUrl, Ship[].class);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testQueryForAllShips_FirstElement() {
		int id = 1;
		String requestUrl = "/ships";
	    ResponseEntity<Ship[]> response = restTemplate.getForEntity(requestUrl, Ship[].class);
	    
	    Ship[] ships = response.getBody();
	    assertEquals(id, ships[0].getId());
	}
	
	@Test
	void getShipByIdOkStatus() {
		int id = 1;
		String requestUrl = UriComponentsBuilder.fromPath("/ships/byId/{id}")
		            .buildAndExpand(id)
		            .toUriString();
		
		ResponseEntity<Ship> response = restTemplate.getForEntity(requestUrl, Ship.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getShipByIdListSize() {
		
		int id = 1;
		String requestUrl = UriComponentsBuilder.fromPath("/ships/byId/{id}")
		            .buildAndExpand(id)
		            .toUriString();
		ResponseEntity<Ship> response = restTemplate.getForEntity(requestUrl, Ship.class);
		
		Ship responseShip = response.getBody();
		assertEquals(id, responseShip.getId()); 
	}
	
	@Test
	public void testQueryForShipNotInDb_NoShipsInDb() {
		int id = 99;
		String requestUrl = UriComponentsBuilder.fromPath("/ships/byId/{id}")
		            .buildAndExpand(id)
		            .toUriString();
		ResponseEntity<Ship> response = restTemplate.getForEntity(requestUrl, Ship.class);
		
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testQueryGetCaptainByNameStatus() {
		String name = "RMS Titanic";
		String requestUrl = UriComponentsBuilder.fromPath("/ships/byName/{name}")
	            .buildAndExpand(name)
	            .toUriString();
		
		ResponseEntity<ShipCaptainResponse> response = restTemplate.getForEntity(requestUrl, ShipCaptainResponse.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void testQueryGetCaptainByNameNotInDB() {
		String name = "RMS";
		String requestUrl = UriComponentsBuilder.fromPath("/ships/byName/{name}")
	            .buildAndExpand(name)
	            .toUriString();
		
		ResponseEntity<ShipCaptainResponse> response = restTemplate.getForEntity(requestUrl, ShipCaptainResponse.class);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}
	
	@Test
	public void testQueryGetCaptainByName() {
		String name = "RMS Titanic";
		String requestUrl = UriComponentsBuilder.fromPath("/ships/byName/{name}")
	            .buildAndExpand(name)
	            .toUriString();
		
		ResponseEntity<ShipCaptainResponse> response = restTemplate.getForEntity(requestUrl, ShipCaptainResponse.class);
		
		assertEquals(new ShipCaptainResponse("Captain Edward J. Smith"), response.getBody());
	}
	
//	@Test
//	public void testGetShipById_NoShipTable() {
//		dropTables(jdbcTemplate, "famousships");
//
//		int id = 99;
//		String requestUrl = UriComponentsBuilder.fromPath("/ships/byId/{id}")
//		            .buildAndExpand(id)
//		            .toUriString();
//		ResponseEntity<Ship> response = restTemplate.getForEntity(requestUrl, Ship.class);
//
//		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//	}
}
