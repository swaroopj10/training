package com.fidelity.restcontroller;

import java.util.Objects;

public class ShipCaptainResponse {
	@Override
	public String toString() {
		return "ShipCaptainResponse [captain=" + captain + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(captain);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShipCaptainResponse other = (ShipCaptainResponse) obj;
		return Objects.equals(captain, other.captain);
	}

	private String captain;
	
	public ShipCaptainResponse() {}
	
    public ShipCaptainResponse(String captain) {
        this.captain = captain;
    }

    public String getCaptain() {
        return captain;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }
}
