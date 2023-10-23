package com.fidelity.payroll;

import java.time.LocalDate;
import java.util.Objects;
import java.math.BigDecimal;

public class Consultant extends Employee {
	
	private BigDecimal contractAmount;
	private int contractLengthInMonths;
	public Consultant(String name, LocalDate hireDate, BigDecimal contractAmount, int contractLengthInMonths) {
		super(name, hireDate);
		validateContractAmount(contractAmount);
        validateContractLength(contractLengthInMonths);
		this.contractAmount = contractAmount;
		this.contractLengthInMonths = contractLengthInMonths;	
	}
	
	private void validateContractAmount(BigDecimal contractAmount) {
		if(contractAmount == null) {
			throw new NullPointerException("Contract amount cannot be null");
		}
        if (contractAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Contract amount cannot be negative");
        }
    }

    private void validateContractLength(int contractLengthInMonths) {
        if (contractLengthInMonths <= 0) {
            throw new IllegalArgumentException("Contract length must be greater than zero");
        }
    }

	public BigDecimal calculateMonthlyPay() {
		return contractAmount.divide(new BigDecimal(contractLengthInMonths));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(contractAmount, contractLengthInMonths);
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
		Consultant other = (Consultant) obj;
		return Objects.equals(contractAmount, other.contractAmount)
				&& contractLengthInMonths == other.contractLengthInMonths;
	}

	@Override
	public String toString() {
		return "Consultant [contractAmount=" + contractAmount + ", contractLengthInMonths=" + contractLengthInMonths
				+ ", name=" + name + ", hireDate=" + hireDate + ", calculateMonthlyPay()=" + calculateMonthlyPay()
				+ ", hashCode()=" + hashCode() + ", calculateMonthlyPayment()=" + calculateMonthlyPay()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
}
