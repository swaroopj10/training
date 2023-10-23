package com.fidelity.india.secondary.assessment;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.jdbc.JdbcTestUtils.deleteFromTables;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.fidelity.india.secondary.assessment.business.Bill;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"},executionPhase = ExecutionPhase.AFTER_TEST_METHOD) 
class MarinaControllerE2eTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Bill bill = new Bill("Belmullet", "Ireland", "Bystolic", 453.3, 37, "€16772.1");
	
	@Test
	public void getBill_vesselDoesNotExist () {
		String url = "/marina/bill/Test";
		ResponseEntity<Bill> response = restTemplate.getForEntity(url, Bill.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void getBill_noVesselInDb() {
		deleteFromTables(jdbcTemplate, "vessels");
		
		String url = "/marina/bill/Bystolic";
		ResponseEntity<Bill> response = restTemplate.getForEntity(url, Bill.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void getBill_returnsOkStatus() {
		
		String url = "/marina/bill/Bystolic";
		ResponseEntity<Bill> response = restTemplate.getForEntity(url, Bill.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getBill_returnsCorrectBody() {
		String url = "/marina/bill/Bystolic";
		ResponseEntity<Bill> response = restTemplate.getForEntity(url, Bill.class);
		
		Bill responseBill = response.getBody();
		assertEquals(bill.getVesselName(), responseBill.getVesselName());
	}

}
