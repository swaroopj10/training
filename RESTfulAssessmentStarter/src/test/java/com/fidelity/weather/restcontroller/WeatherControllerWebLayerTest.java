package com.fidelity.weather.restcontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fidelity.weather.integration.WeatherDao;
import com.fidelity.weather.models.LatitudeLongitude;
import com.fidelity.weather.models.WeatherForecast;
import com.fidelity.weather.service.WeatherService;

@WebMvcTest
public class WeatherControllerWebLayerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private WeatherService mockService;
	
	@MockBean
	private WeatherDao mockDao;
	
	@MockBean
	private RestTemplate mockRestTemplate;
	
	@Test
	public void testGetForecast_SuccessStatus() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(status().isOk());
	}
	
	@Test
	public void testGetForecast_SuccessLat() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(jsonPath("$.latitude").value("39.9042"));
	}
	
	@Test
	public void testGetForecast_SuccessLong() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(jsonPath("$.longitude").value("116.4074"));
	}
	
	@Test
	public void testGetForecast_SuccessHighTemp() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(jsonPath("$.highTemperature").value("79"));
	}
	
	@Test
	public void testGetForecast_SuccessLowTemp() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(jsonPath("$.lowTemperature").value("65"));
	}
	
	@Test
	public void testGetForecast_SuccessForecast() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(jsonPath("$.forecast").value("Partly cloudy"));
	}
	
	@Test
	public void testGetForecast_SuccessDetailedForecast() throws Exception {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		
		when(mockService.getLatitudeLongitude("Beijing", "HE", "CN"))
			.thenReturn(latLong);

		WeatherForecast forecast = new WeatherForecast(39.9042, 116.4074, "F", 79, 65, "Partly cloudy",
									"Partly cloudy. High 79.");
		ResponseEntity<WeatherForecast> response = ResponseEntity.ok(forecast);
		String forecastUrl = WeatherController.baseUrl + latLong.getLatitude() + "," + latLong.getLongitude();
		when(mockRestTemplate.getForEntity(forecastUrl, WeatherForecast.class))
			.thenReturn(response);

		mockMvc.perform(get("/weather/forecast/Beijing/HE/CN"))
		       .andDo(print())
		       .andExpect(jsonPath("$.detailedForecast").value("Partly cloudy. High 79."));
	}
	
}
