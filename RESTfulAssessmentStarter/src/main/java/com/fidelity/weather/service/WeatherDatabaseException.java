package com.fidelity.weather.service;

public class WeatherDatabaseException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public WeatherDatabaseException() {
	}

	public WeatherDatabaseException(String message) {
		super(message);
	}

	public WeatherDatabaseException(Throwable cause) {
		super(cause);
	}

	public WeatherDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public WeatherDatabaseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
