package com.fidelity.weather.integration.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fidelity.weather.models.LatitudeLongitude;

@Mapper
public interface WeatherMapper {
	
	LatitudeLongitude getLatitudeLongitude(@Param("city") String city, @Param("region") String region, @Param("country") String country); 
}
