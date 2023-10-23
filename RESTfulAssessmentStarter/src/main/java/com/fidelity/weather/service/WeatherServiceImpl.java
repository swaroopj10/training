package com.fidelity.weather.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.weather.integration.WeatherDao;
import com.fidelity.weather.models.LatitudeLongitude;

@Service
@Transactional
public class WeatherServiceImpl implements WeatherService {
	
	@Autowired
	private Logger logger;
	
	@Autowired
	private WeatherDao dao;
	
	@Override
	public LatitudeLongitude getLatitudeLongitude(String city, String region, String country) {
		
		LatitudeLongitude latLong = null;
		
		if (city == null || city.isEmpty()) {
			throw new IllegalArgumentException("city cannot be empty");
		}
		if (region == null || region.isEmpty()) {
			throw new IllegalArgumentException("region cannot be empty");
		}
		if (country == null || country.isEmpty()) {
			throw new IllegalArgumentException("country cannot be empty");
		}
		
		try {
			latLong = dao.getLatitudeAndLongitude(city, region, country);
			
		} catch (DataAccessException e) {
			logger.error("DataAccessException found", e);
			throw new WeatherDatabaseException(e);
			
		} catch (RuntimeException e) {
			logger.error("RunTimeException found", e);
			throw new WeatherDatabaseException(e);
		}
		return latLong;
	}

}
