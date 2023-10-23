package com.fidelity.weather.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fidelity.weather.integration.mapper.WeatherMapper;
import com.fidelity.weather.models.LatitudeLongitude;

@Repository
public class WeatherDaoImpl implements WeatherDao {
	
	@Autowired
	private WeatherMapper mapper;
	
	@Override
	public LatitudeLongitude getLatitudeAndLongitude(String city, String region, String country) {

		if (city == null || city.isEmpty()) {
			throw new IllegalArgumentException("city cannot be empty");
		}
		if (region == null || region.isEmpty()) {
			throw new IllegalArgumentException("region cannot be empty");
		}
		if (country == null || country.isEmpty()) {
			throw new IllegalArgumentException("country cannot be empty");
		}
		return mapper.getLatitudeLongitude(city, region, country);
	}

}
