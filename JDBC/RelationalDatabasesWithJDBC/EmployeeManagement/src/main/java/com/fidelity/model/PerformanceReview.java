package com.fidelity.model;

public enum PerformanceReview {
	BELOW(1), AVERAGE(3), ABOVE(5);
	private int code;
	private PerformanceReview(int code) {
		this.code = code;
	}
	public static PerformanceReview of(int code) {
		for (PerformanceReview revRes : 
			PerformanceReview.values()) {
			if (revRes.getCode() == code) {
				return revRes;
			}
		}
		throw new IllegalArgumentException("bad code: " + code);
	}
	public int getCode() { return code; }
}
