package com.fidelity.freight;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Officer extends CrewMember {
	private String rank;
	private BigDecimal MonthlySalary;

	public Officer(String name, int id, int payDay, String rank, BigDecimal MonthlySalary) {
		super(name, id, payDay);
		if(rank == null) {
			throw new NullPointerException("Rank cannot be null");
		}
		if(MonthlySalary.compareTo(new BigDecimal("0")) <= 0) {
			throw new IllegalArgumentException("Salary cannot be less than or equal to 0.");
		}
		this.rank = rank;
		this.MonthlySalary = MonthlySalary;
	}

	@Override
	public BigDecimal calculatePay(LocalDate currentDate) {
		BigDecimal pay;
		if(currentDate == null) {
			throw new NullPointerException("Date cannot be null");
		}
		if(currentDate.getDayOfMonth() < payDay || currentDate.getDayOfMonth() - payDay < 15) {
			return new BigDecimal("0");
		} else {
			pay = MonthlySalary;
		}
		return pay;	
	}
}
