package com.fidelity.weather.restcontroller;

public class ForecastException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ForecastException() {
	}

	public ForecastException(String message) {
		super(message);
	}

	public ForecastException(Throwable cause) {
		super(cause);
	}

	public ForecastException(String message, Throwable cause) {
		super(message, cause);
	}

	public ForecastException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
