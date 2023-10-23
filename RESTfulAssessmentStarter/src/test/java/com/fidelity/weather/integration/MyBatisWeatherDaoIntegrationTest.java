package com.fidelity.weather.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.weather.models.LatitudeLongitude;

@SpringBootTest
@Transactional
class MyBatisWeatherDaoIntegrationTest {
	
	@Autowired
	private WeatherDao dao;
	
	@Test
	void weatherDaoIsNotNull() {
		assertNotNull(dao);
	}
	
	@Test
	void getLatLong_cityIsNull() {
		assertThrows(IllegalArgumentException.class, ()-> {
			dao.getLatitudeAndLongitude(null, "HE", "CN");
		});
	}
	
	@Test
	void getLatLong_regionIsNull() {
		assertThrows(IllegalArgumentException.class, ()-> {
			dao.getLatitudeAndLongitude("Beijing", null, "CN");
		});
	}
	
	@Test
	void getLatLong_countryIsNull() {
		assertThrows(IllegalArgumentException.class, ()-> {
			dao.getLatitudeAndLongitude("Beijing", "HE", null);
		});
	}
	
	@Test
	void getLatLong_allInputsAreNull() {
		assertThrows(IllegalArgumentException.class, ()-> {
			dao.getLatitudeAndLongitude(null, null, null);
		});
	}
	
	@Test
	void getLatLang_cityInvalidInput() {
			assertNull(dao.getLatitudeAndLongitude("Mumbai", "HE", "CN"));
	}
	
	@Test
	void getLatLang_regionInvalidInput() {
			assertNull(dao.getLatitudeAndLongitude("Beijing", "MU", "CN"));
	}
	
	@Test
	void getLatLang_countryInvalidInput() {
			assertNull(dao.getLatitudeAndLongitude("Beijing", "HE", "IN"));
	}
	
	@Test
	void getLatLang_allInvalidInput() {
			assertNull(dao.getLatitudeAndLongitude("Mumbai", "MU", "IN"));
	}
	
	@Test
	void getLatLong_returnsNotNullLatitude() {
		assertNotNull(dao.getLatitudeAndLongitude("Beijing", "HE", "CN").getLatitude());
	}
	
	@Test
	void getLatLong_returnsNotNullLongitude() {
		assertNotNull(dao.getLatitudeAndLongitude("Beijing", "HE", "CN").getLongitude());
	}
	
	@Test
	void getLatLong_returnsNotNullObject() {
		assertNotNull(dao.getLatitudeAndLongitude("Beijing", "HE", "CN"));
	}
	
	@Test
	void getLatLong_returnsExpectedOutput() {
		LatitudeLongitude latLong = new LatitudeLongitude("39.9042", "116.4074");
		assertEquals(latLong, dao.getLatitudeAndLongitude("Beijing", "HE", "CN"));
	}
}
