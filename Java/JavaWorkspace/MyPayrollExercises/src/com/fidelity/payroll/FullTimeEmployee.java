package com.fidelity.payroll;

import java.util.Objects;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class FullTimeEmployee extends Employee {
	private BigDecimal salary;
	public FullTimeEmployee(String name, LocalDate hireDate , BigDecimal salary) {
		super(name, hireDate);
		this.salary = salary;
	}
	
	public BigDecimal calculateMonthlyPay() {
		BigDecimal pay = new BigDecimal("12");
		return salary.divide(pay, 2, RoundingMode.HALF_UP);
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
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(salary);
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
		FullTimeEmployee other = (FullTimeEmployee) obj;
		return Objects.equals(salary, other.salary);
	}

	@Override
	public String toString() {
		return "FullTimeEmployee [salary=" + salary + ", name=" + name + ", calculateMonthlyPay()="
				+ calculateMonthlyPay() + ", hashCode()=" + hashCode() + ", calculateMonthlyPayment()="
				+ calculateMonthlyPay() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
}
