package com.fidelity.payroll;

import java.util.Objects;
import java.time.LocalDate;
import java.math.BigDecimal;

public class PartTimeEmployee extends Employee {
	private BigDecimal hourlyRate;
    private int hoursForMonth;
	
	
	public PartTimeEmployee(String name, LocalDate hireDate, BigDecimal hourlyRate, int hoursForMonth) {
		super(name, hireDate);
		this.hourlyRate = hourlyRate;
		this.hoursForMonth = hoursForMonth;
	}
	
	public String getName() {
		return this.name;
	}
	
	public BigDecimal getHourlyRate() {
		return this.hourlyRate;
	}
	
	public int getHoursForMonth() {
		return this.hoursForMonth;
	}
	
	public BigDecimal calculateMonthlyPay() {
		BigDecimal hoursBigDecimal = new BigDecimal(hoursForMonth);
        return hourlyRate.multiply(hoursBigDecimal);
	}

	public BigDecimal calculateMonthlyPayment(BigDecimal bonus) {
		BigDecimal hoursBigDecimal = new BigDecimal(hoursForMonth);
		return hourlyRate.multiply(hoursBigDecimal).add(bonus);
	}
	
	@Override
	public BigDecimal calculatePayToDate(LocalDate date) {
	    if (date == null || date.isBefore(hireDate)) {
	        return BigDecimal.ZERO;
	    }

	    int yearsWorked = date.getYear() - hireDate.getYear();
	    int monthsWorked = yearsWorked * 12 + date.getMonthValue() - hireDate.getMonthValue();

	    BigDecimal monthlyPay = calculateMonthlyPay(); 
	    BigDecimal totalPay = monthlyPay.multiply(BigDecimal.valueOf(monthsWorked)).setScale(2);

	    return totalPay;
	}

	@Override
	public String toString() {
		return "PartTimeEmployee [hourlyRate=" + hourlyRate + ", hoursForMonth=" + hoursForMonth + ", name=" + name
				+ ", getName()=" + getName() + ", getHourlyRate()=" + getHourlyRate() + ", getHoursForMonth()="
				+ getHoursForMonth() + ", calculateMonthlyPayment()=" + calculateMonthlyPay() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(hourlyRate, hoursForMonth);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PartTimeEmployee other = (PartTimeEmployee) obj;
		return Objects.equals(hourlyRate, other.hourlyRate) && hoursForMonth == other.hoursForMonth;
	}
}
