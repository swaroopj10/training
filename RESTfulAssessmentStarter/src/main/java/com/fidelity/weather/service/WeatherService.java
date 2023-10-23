package com.fidelity.weather.service;

import com.fidelity.weather.models.LatitudeLongitude;

public interface WeatherService {
	
	LatitudeLongitude getLatitudeLongitude(String city, String region, String country);
}
