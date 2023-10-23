package com.fidelity.payroll;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Employee implements Comparable<Employee> {

	protected String name;
	protected LocalDate hireDate;

	public Employee(String name, LocalDate hireDate) {
		if(name == null) {
			throw new NullPointerException("Nme cannot be null");
		}
		if(hireDate == null) {
			throw new NullPointerException("Date cannot be null");
		}
		this.name = name;
		this.hireDate = hireDate;
	}
	
	public String getName() {
		return name;
	}

	public abstract BigDecimal calculateMonthlyPay();
	
	public BigDecimal calculateMonthlyPay(BigDecimal bonus) {
		return calculateMonthlyPay().add(bonus).setScale(2);
	}

	public BigDecimal calculatePayToDate(LocalDate date) {
		if (date == null || date.isBefore(hireDate)) {
	        return BigDecimal.ZERO;
	    }

	    int yearsWorked = date.getYear() - hireDate.getYear();
	    int monthsWorked = yearsWorked * 12 + date.getMonthValue() - hireDate.getMonthValue();

	    if (date.getDayOfMonth() < hireDate.getDayOfMonth()) {
	        monthsWorked--;
	    }

	    BigDecimal monthlyPay = calculateMonthlyPay();
	    BigDecimal totalPay = monthlyPay.multiply(BigDecimal.valueOf(monthsWorked)).setScale(2);

	    return totalPay;
	}
	
	@Override
	public int compareTo(Employee employee) {
		return name.compareTo(employee.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(hireDate, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(hireDate, other.hireDate) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", hireDate=" + hireDate + ", calculateMonthlyPayment()="
				+ calculateMonthlyPay() + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}
}