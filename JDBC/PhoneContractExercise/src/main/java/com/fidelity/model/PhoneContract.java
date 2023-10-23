package com.fidelity.model;

import java.math.BigDecimal;
import java.util.Objects;

public class PhoneContract {

	private int phoneContractId;
	private String phoneContractName;
	private String rateName;
	private int quantity;
	private BigDecimal totalValue;
	
	// Eclipse-generated from here

	public PhoneContract(int pcId, String pcName, String rateName, int quantity,
			BigDecimal totalValue) {
		super();
		this.phoneContractId = pcId;
		this.phoneContractName = pcName;
		this.rateName = rateName;
		this.quantity = quantity;
		this.totalValue = totalValue;
	}
	
	public int getPhoneContractId() {
		return phoneContractId;
	}

	public String getPhoneContractName() {
		return phoneContractName;
	}

	public String getRateName() {
		return rateName;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "PhoneContract [phoneContractId=" + phoneContractId + ", phoneContractName=" + phoneContractName
				+ ", rateName=" + rateName + ", quantity=" + quantity + ", totalValue=" + totalValue
				+ ", getPhoneContractId()=" + getPhoneContractId() + ", getPhoneContractName()="
				+ getPhoneContractName() + ", getRateName()=" + getRateName() + ", getQuantity()=" + getQuantity()
				+ ", getTotalValue()=" + getTotalValue() + ", hashCode()=" + hashCode() + "]";
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(phoneContractId, phoneContractName, quantity, rateName, totalValue);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneContract other = (PhoneContract) obj;
		return phoneContractId == other.phoneContractId && Objects.equals(phoneContractName, other.phoneContractName)
				&& quantity == other.quantity && Objects.equals(rateName, other.rateName)
				&& Objects.equals(totalValue, other.totalValue);
	}
}
