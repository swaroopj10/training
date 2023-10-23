package com.fidelity.payroll;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FullTimeEmployeeTest {
	
	FullTimeEmployee fullTimeEmployee;
	@BeforeEach
	void setUp() throws Exception {
		String name = "John Doe";
		BigDecimal salary = new BigDecimal("120000");
		LocalDate hireDate = LocalDate.of(2020, 1, 1);
		fullTimeEmployee = new FullTimeEmployee(name, hireDate, salary);
	}

	@AfterEach
	void tearDown() throws Exception {
		fullTimeEmployee = null;
	}

	@Test
	void noArgsnotNull() {
		assertNotNull(fullTimeEmployee);
	}
	
	
	@Test
	void payGreaterThanZero() {
		BigDecimal zeroPay= new BigDecimal("0");
		assertTrue(fullTimeEmployee.calculateMonthlyPay().compareTo(zeroPay) > 0);
	}
	
	@Test
	void getSalary() {
		BigDecimal pay = new BigDecimal("10000.00");
		assertEquals(fullTimeEmployee.calculateMonthlyPay(),pay);
	}
	
	@Test
	void testFullTimeEmployeeEqualityEquals() {
		BigDecimal salary= new BigDecimal("1000");
		LocalDate date = LocalDate.of(2023, 8, 8);
		Employee emp = new FullTimeEmployee("John Doe", date, salary);
		assertEquals(new FullTimeEmployee("John Doe", date, salary), emp);
	}
	
	@Test
	void testFullTimeEmployeeEqualityNotEquals() {
		BigDecimal salary= new BigDecimal("2000");
		LocalDate date = LocalDate.of(2023, 8, 8);
		Employee emp = new FullTimeEmployee("Jon Doe", date, salary);
		assertNotEquals(new FullTimeEmployee("John Doe", date, salary),emp);
	}
	
	@Test
    void testIWithNullName() {
        String name = null;
        LocalDate hireDate = LocalDate.of(2021, 5, 10);
        BigDecimal salary= new BigDecimal("2000");
        assertThrows(NullPointerException.class, () -> new FullTimeEmployee(name, hireDate, salary));
    }
	
	@Test
    void testIWithNullDate() {
        String name = "John Doe";
        LocalDate hireDate = null;
        BigDecimal salary= new BigDecimal("2000");
        assertThrows(NullPointerException.class, () -> new FullTimeEmployee(name, hireDate, salary));
    }
	
	@Test
	public void testCalculatePayToDateWithValidDate() {
	    LocalDate currentDate = LocalDate.of(2023, 9, 8);
	    BigDecimal expectedTotalPay = new BigDecimal("10000.00").multiply(BigDecimal.valueOf(44));
	    BigDecimal actualTotalPay = fullTimeEmployee.calculatePayToDate(currentDate);
	    assertEquals(expectedTotalPay, actualTotalPay);
	}

	@Test
	public void testCalculatePayToDateWithNullDate() {
	    BigDecimal actualTotalPay = fullTimeEmployee.calculatePayToDate(null);
	    assertEquals(BigDecimal.ZERO, actualTotalPay);
	}

	@Test
	public void testCalculatePayToDateWithEarlierDate() {
	    LocalDate hireDate = LocalDate.of(2020, 1, 1);
	    FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("John", hireDate, new BigDecimal("10000"));
	    LocalDate earlierDate = LocalDate.of(2019, 12, 31);
	    BigDecimal actualTotalPay = fullTimeEmployee.calculatePayToDate(earlierDate);
	    assertEquals(BigDecimal.ZERO, actualTotalPay);
	}
	
	@Test
	void testCompareLessThan() {
		Employee fullTime = new FullTimeEmployee("Jane Doe", LocalDate.of(2020, 1, 1), new BigDecimal("10.0"));
		Employee partTime = new FullTimeEmployee("John Doe", LocalDate.of(2020, 1, 1), new BigDecimal("10.0"));
		
		assertTrue(fullTime.compareTo(partTime) < 0);
	}

	@Test
	void testCompareGreaterThan() {
		Employee fullTime = new FullTimeEmployee("Nathan Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.0"));
		Employee partTime = new FullTimeEmployee("John Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.0"));
		
		assertTrue(fullTime.compareTo(partTime) > 0);
	}

	@Test
	void testCompareEqual() {
		Employee fullTime = new FullTimeEmployee("John Doe", LocalDate.of(2019, 1, 1), new BigDecimal("20.0"));
		Employee partTime = new FullTimeEmployee("John Doe", LocalDate.of(2018, 1, 1), new BigDecimal("10.0"));
		
		assertEquals(0, fullTime.compareTo(partTime));
	}
}
