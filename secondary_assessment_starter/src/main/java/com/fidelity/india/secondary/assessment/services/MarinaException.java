package com.fidelity.india.secondary.assessment.services;

public class MarinaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public MarinaException() {
	}

	public MarinaException(String message) {
		super(message);
	}

	public MarinaException(Throwable cause) {
		super(cause);
	}

	public MarinaException(String message, Throwable cause) {
		super(message, cause);
	}

	public MarinaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
