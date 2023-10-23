package com.fidelity.integration;

import java.math.BigDecimal;
import java.util.List;

import com.fidelity.model.PhoneContract;

public interface PhoneContractDao {
	List<PhoneContract> getFullPhoneContracts();
	List<PhoneContract> getPhoneContractByID(int pcId);
	BigDecimal calculateTotalValue(List<PhoneContract> mainPhoneContractList);
}
