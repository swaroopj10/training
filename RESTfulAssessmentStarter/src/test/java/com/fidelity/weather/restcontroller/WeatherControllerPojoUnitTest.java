package com.fidelity.weather.restcontroller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fidelity.weather.integration.WeatherDao;
import com.fidelity.weather.models.LatitudeLongitude;
import com.fidelity.weather.models.WeatherForecast;
import com.fidelity.weather.service.WeatherService;

class WeatherControllerPojoUnitTest {
	
	@InjectMocks
	private WeatherController controller;
	
	@Mock
	private RestTemplate mockRestTemplate;
	
	@Mock
	private WeatherService mockService;
	
	@Mock
	private WeatherDao mockDao;
	
	@Mock
	private Logger mockLogger;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void getForecast_throwsDaoException(){
		String city = "Beijing";
		String region = "HE";
		String country = "CN";

		when(mockService.getLatitudeLongitude(city, region, country))
			.thenThrow(new IllegalArgumentException("mock DAO exception"));
		
		assertThrows(IllegalArgumentException.class, () -> {
			controller.getForecast(city, region, country);
		});
	}

	@Test
	public void getForecast_cityDoesNotExist() {
		String city = "Mumbai";
		String region = "MU";
		String country = "IN";

		when(mockService.getLatitudeLongitude(city, region, country))
			.thenReturn(null);
		
		ResponseEntity<WeatherForecast> response = controller.getForecast(city, region, country);
		
		assertEquals(400, response.getStatusCodeValue());
	}
	
	@Test
	public void getForecast_okStatus() {
		String city = "Beijing";
		String region = "He";
		String country = "CN";
		String lat = "39.9042";
		String lon = "116.4074";

		when(mockService.getLatitudeLongitude(city, region, country))
			.thenReturn(new LatitudeLongitude(lat, lon));

		WeatherForecast forecast = new WeatherForecast();
		forecast.setDetailedForecast("Sunny, high 85");
		when(mockRestTemplate.getForEntity(WeatherController.baseUrl + lat + "," + lon, WeatherForecast.class))
			.thenReturn(ResponseEntity.ok(forecast));

		ResponseEntity<WeatherForecast> response = controller.getForecast(city, region, country);
		
		assertEquals(200, response.getStatusCodeValue());
	}
	
	@Test
	public void getForecast_correctForecast() {
		String city = "Beijing";
		String region = "He";
		String country = "CN";
		String lat = "39.9042";
		String lon = "116.4074";

		when(mockService.getLatitudeLongitude(city, region, country))
			.thenReturn(new LatitudeLongitude(lat, lon));

		WeatherForecast forecast = new WeatherForecast();
		forecast.setDetailedForecast("Sunny, high 85");
		when(mockRestTemplate.getForEntity(WeatherController.baseUrl + lat + "," + lon, WeatherForecast.class))
			.thenReturn(ResponseEntity.ok(forecast));

		ResponseEntity<WeatherForecast> response = controller.getForecast(city, region, country);
		
		assertEquals("Sunny, high 85", response.getBody().getDetailedForecast());
	}
}
