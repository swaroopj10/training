package com.fidelity.weather.restcontroller;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerErrorException;

import com.fidelity.weather.models.LatitudeLongitude;
import com.fidelity.weather.models.WeatherForecast;
import com.fidelity.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {
	
	public static final String baseUrl = "http://localhost:8088/forecast/";
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WeatherService service;
	
	@GetMapping(value="/ping", produces=MediaType.ALL_VALUE)
	public String ping() {
		return "Forecast web service is alive at " + LocalDateTime.now();
	}
	
	@GetMapping("/forecast/{city}/{region}/{country}")
	public ResponseEntity<WeatherForecast> getForecast(@PathVariable String city, @PathVariable String region, @PathVariable String country) {
		
		try {
			ResponseEntity<WeatherForecast> forecast;
			
			LatitudeLongitude latLong = service.getLatitudeLongitude(city, region, country);
			
			if(latLong != null) {
				WeatherForecast serverForecast = getForecastFromServer(latLong.getLatitude(), latLong.getLongitude());
				
				forecast = ResponseEntity.ok(serverForecast);
			} else {
				logger.warn("getForecast: " + city + ", " + region + ", " + country + "not found");
				forecast = ResponseEntity.badRequest().build();
			}
			return forecast;
		} catch (ForecastException e) {
			logger.error("getForecast({}, {}): {}", city, region, country, e.getMessage());
			
			throw new ServerErrorException("Server Error", e);
		}
	}

	private WeatherForecast getForecastFromServer(String latitude, String longitude) {

		String forecastUrl = baseUrl + latitude + "," + longitude;
		
		ResponseEntity<WeatherForecast> forecastResponse = restTemplate.getForEntity(forecastUrl, WeatherForecast.class);
		if (forecastResponse.getStatusCode() != HttpStatus.OK) {
			String message = "Error getting forecast for " + latitude + "," + longitude;
			throw new ForecastException(message);
		}
		WeatherForecast forecast = forecastResponse.getBody();
		return forecast;
	}
}
