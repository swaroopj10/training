package com.fidelity.freight;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class FreightShip {
	
	private int maxCapacity;
	private List<CrewMember> crews;
	
	public FreightShip(int maxCapacity) {
		if(maxCapacity <= 0) {
			throw new IllegalArgumentException("Capacity cannot be less than or equal to Zero.");
		}
		this.maxCapacity = maxCapacity;
		crews = new ArrayList<>(maxCapacity);
	}
	
	public void addMember(CrewMember crewMember) {
		if(crewMember == null) {
			throw new NullPointerException("Crew Member cannot be null");
		}
		if(crews.size() >= maxCapacity) {
			throw new IllegalStateException("Crew is full.");
		}
		crews.add(crewMember);
	}
	
	public void removeMember(CrewMember crewMember) {
		if(crewMember == null) {
			throw new NullPointerException("Crew Member cannot be null");
		}
		for(CrewMember crew : crews) {
			int id = crewMember.getId();
			if(id == crew.getId()) {
				crews.remove(crewMember);
				return;
			}
		}
		throw new NoSuchElementException("Crew Member does not exist");
	}
	
	public int getMaxCapacity() {
		return this.maxCapacity;
	}
	
	public int getRemainingCapacity() {
		return this.maxCapacity - crews.size();
	}
	
	public int getCurrentCrewNumber() {
		return crews.size();
	}
	
	public BigDecimal calculateTotalPayRoll() {
		BigDecimal totalPay = BigDecimal.ZERO;
		LocalDate date = LocalDate.of(2023, 9, 23);
		for(CrewMember crew : crews) {
			BigDecimal pay = crew.calculatePay(date);
			totalPay = totalPay.add(pay);
		}
		return totalPay;
	}
}
