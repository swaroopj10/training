package com.fidelity.freight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class CrewMember {
	
	private String name;
	private int id;
	protected int payDay;
	
	public CrewMember(String name, int id, int payDay) {
		if(name == null) {
			throw new NullPointerException("Name cannot be null");
		}
		if(payDay < 0 || payDay > 30) {
			throw new IllegalArgumentException("Enter a valid Date");
		}
		this.name = name;
		this.id = id;
		this.payDay = payDay;
	}
	
	public abstract BigDecimal calculatePay(LocalDate currentDate);
	
	public int getId() {
		return this.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, payDay);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CrewMember other = (CrewMember) obj;
		return id == other.id && Objects.equals(name, other.name) && payDay == other.payDay;
	}

	@Override
	public String toString() {
		return "CrewMember [name=" + name + ", id=" + id + ", payDay=" + payDay + ", getId()=" + getId()
				+ ", hashCode()=" + hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString()
				+ "]";
	}
}
