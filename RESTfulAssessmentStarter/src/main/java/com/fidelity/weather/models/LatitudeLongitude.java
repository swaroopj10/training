package com.fidelity.weather.models;

import java.util.Objects;

public class LatitudeLongitude {
	
	private String latitude;
	private String longitude;
	
	public LatitudeLongitude() {}
	
	public LatitudeLongitude(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public int hashCode() {
		return Objects.hash(latitude, longitude);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LatitudeLongitude other = (LatitudeLongitude) obj;
		return Objects.equals(latitude, other.latitude) && Objects.equals(longitude, other.longitude);
	}

	@Override
	public String toString() {
		return "LatitudeLongitude [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
