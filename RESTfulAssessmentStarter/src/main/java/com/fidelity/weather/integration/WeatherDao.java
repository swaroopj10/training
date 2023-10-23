package com.fidelity.weather.integration;

import com.fidelity.weather.models.LatitudeLongitude;

public interface WeatherDao {
	
	LatitudeLongitude getLatitudeAndLongitude(String city, String region, String country);
}
