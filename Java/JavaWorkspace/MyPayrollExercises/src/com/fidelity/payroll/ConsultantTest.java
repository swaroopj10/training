package com.fidelity.payroll;
import java.math.BigDecimal;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsultantTest {
	Consultant consultant;
	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
		consultant = null;
	}

	@Test
	void NotNull() {
		String name = "Alice";
	    LocalDate hireDate = LocalDate.of(2022, 1, 15);
	    BigDecimal contractAmount = new BigDecimal("12000");
	    int contractLengthInMonths = 12;
	     
	    consultant = new Consultant(name, hireDate, contractAmount, contractLengthInMonths);
		assertNotNull(consultant);
	}
	
    @Test
    void testCalculateMonthlyPay() {
        BigDecimal contractAmount = new BigDecimal("12000");
        int contractLengthInMonths = 12;
        consultant = new Consultant("Bob", LocalDate.of(2022, 2, 20), contractAmount, contractLengthInMonths);
        BigDecimal expectedMonthlyPay = contractAmount.divide(BigDecimal.valueOf(contractLengthInMonths));
        BigDecimal actualMonthlyPay = consultant.calculateMonthlyPay();
        assertEquals(expectedMonthlyPay, actualMonthlyPay);
    }
    
    @Test
    void testInstantiateConsultantWithNullContractAmount() {
        String name = "Eve";
        LocalDate hireDate = LocalDate.of(2021, 5, 10);
        BigDecimal contractAmount = null;
        int contractLengthInMonths = 6;
        assertThrows(NullPointerException.class, () -> new Consultant(name, hireDate, contractAmount, contractLengthInMonths));
    }

    @Test
    void testInstantiateConsultantWithNegativeContractAmount() {
        String name = "Eve";
        LocalDate hireDate = LocalDate.of(2021, 5, 10);
        BigDecimal contractAmount = new BigDecimal("-5000");
        int contractLengthInMonths = 6;
        assertThrows(IllegalArgumentException.class, () -> new Consultant(name, hireDate, contractAmount, contractLengthInMonths));
    }

    @Test
    void testInstantiateConsultantWithZeroContractLength() {
        String name = "Dave";
        LocalDate hireDate = LocalDate.of(2023, 9, 1);
        BigDecimal contractAmount = new BigDecimal("8000");
        int contractLengthInMonths = 0;
        assertThrows(IllegalArgumentException.class, () -> new Consultant(name, hireDate, contractAmount, contractLengthInMonths));
    }
    
    @Test
    void testConsultantEquality() {
    	Employee emp = new Consultant("Jane Doe", LocalDate.of(2019, 1, 10), new BigDecimal("15000.00"), 10);
    	assertEquals(new Consultant("Jane Doe", LocalDate.of(2019, 1, 10), new BigDecimal("15000.00"), 10), emp);
    	assertNotEquals(new Consultant("Jane Doe", LocalDate.of(2019, 1, 10), new BigDecimal("12000.00"), 20), emp);
    }
}
