package com.fidelity.weather.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.fidelity.weather.models.WeatherForecast;

/**
 * IMPORTANT: be sure to run the database initialization scripts in SQL Developer
 * before running these test cases:
 *	 src/main/resources/schema.sql
 *	 src/main/resources/data.sql
 */

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"},executionPhase = ExecutionPhase.AFTER_TEST_METHOD) 
public class WeatherControllerE2eTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy", "Partly cloudy. High 79.");
	
	@Test
	public void getForecast_cityDoesNotExist () {
		String url = "/weather/forecast/New Jersey/NY/US";
		ResponseEntity<WeatherForecast> response = restTemplate.getForEntity(url, WeatherForecast.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void getForecast_regionDoesNotExist () {
		String url = "/weather/forecast/New York/NJ/US";
		ResponseEntity<WeatherForecast> response = restTemplate.getForEntity(url, WeatherForecast.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void getForecast_countryDoesNotExist () {
		String url = "/weather/forecast/New York/NY/USA";
		ResponseEntity<WeatherForecast> response = restTemplate.getForEntity(url, WeatherForecast.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	public void getForecast_noCityInDb() {
		deleteFromTables(jdbcTemplate, "city");
		
		String url = "/weather/forecast/Beijing/HE/CN";
		ResponseEntity<WeatherForecast> response = restTemplate.getForEntity(url, WeatherForecast.class);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	void getForecast_returnsOkStatus() {
		
		String url = "/weather/forecast/Beijing/HE/CN";
		ResponseEntity<WeatherForecast> response = restTemplate.getForEntity(url, WeatherForecast.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	void getForecast_returnsCorrectBody() {
		String url = "/weather/forecast/Beijing/HE/CN";
		ResponseEntity<WeatherForecast> response = restTemplate.getForEntity(url, WeatherForecast.class);
		
		WeatherForecast responseForecast = response.getBody();
		assertEquals(forecast, responseForecast);
	}
}
