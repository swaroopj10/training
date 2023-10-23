package com.fidelity.payroll;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartTimeEmployeeTest {
	private PartTimeEmployee partTimeEmployee;

	@BeforeEach
	void setUp() throws Exception {
		String name = "John Doe";
		BigDecimal hourlyRate = new BigDecimal("250");
		int hoursForMonth = 100;
		LocalDate hireDate = LocalDate.of(2020, 1, 1);
		partTimeEmployee = new PartTimeEmployee(name, hireDate, hourlyRate, hoursForMonth);
	}

	@AfterEach
	void tearDown() throws Exception {
		partTimeEmployee = null;
	}

	@Test
	void NoArgsNotNull() {
		assertNotNull(partTimeEmployee);
	}
	
	@Test
	void overLoadedNotNull() {
		assertNotNull(partTimeEmployee);
	}
	
	@Test
	void setsName() {
		String name = "John Doe";
		assertEquals(name, partTimeEmployee.getName());		
	}
	
	@Test
	void setsHourlyRate() {
		BigDecimal hourlyRate = new BigDecimal("250");
		assertEquals(0, hourlyRate.compareTo(partTimeEmployee.getHourlyRate()));	
	}
	
	@Test
	void setsHoursForMonth() {
		double hoursForMonth = 100;
		assertEquals(hoursForMonth, partTimeEmployee.getHoursForMonth());		
	}
	
	@Test
	void returnPayNotZero() {
		BigDecimal pay = partTimeEmployee.calculateMonthlyPay();
		BigDecimal num2 = new BigDecimal("0");
		assertTrue(pay.compareTo(num2) > 0);
	}
	
	@Test
	void returnCorrectPay() {
		BigDecimal expectedPay = new BigDecimal("25000");
		BigDecimal pay = partTimeEmployee.calculateMonthlyPay();
		assertEquals(0, expectedPay.compareTo(pay));
	}
	
	@Test
    void testIWithNullName() {
        String name = null;
        LocalDate hireDate = LocalDate.of(2021, 5, 10);
        BigDecimal hourlyRate = new BigDecimal("250");
		int hoursForMonth = 100;
        assertThrows(NullPointerException.class, () -> new PartTimeEmployee(name, hireDate, hourlyRate, hoursForMonth));
    }
	
	@Test
    void testIWithNullDate() {
        String name = "John Doe";
        LocalDate hireDate = null;
        BigDecimal hourlyRate = new BigDecimal("250");
		int hoursForMonth = 100;
        assertThrows(NullPointerException.class, () -> new PartTimeEmployee(name, hireDate, hourlyRate, hoursForMonth));
    }
	
	@Test
	void returnPayNotZeroWithBonus() {
		BigDecimal bonus = new BigDecimal("100");
		BigDecimal pay = partTimeEmployee.calculateMonthlyPayment(bonus);
		BigDecimal num2 = new BigDecimal("0");
		int comparisonResult = pay.compareTo(num2);
		assertTrue(comparisonResult > 0);
	}
	
	@Test
	void returnCorrectPayWithBonus() {
        BigDecimal bonus = new BigDecimal("100");
        BigDecimal pay = partTimeEmployee.calculateMonthlyPayment(bonus);
        BigDecimal hourlyRate = new BigDecimal("250");
        int hoursForMonth = 100;
        BigDecimal expectedPay = hourlyRate.multiply(new BigDecimal(hoursForMonth)).add(bonus);
        assertEquals(expectedPay, pay);
	}
	
	@Test
	void testPartTimeEmployeeEqualityEquals() {
		BigDecimal hourlyRate = new BigDecimal("10");
		LocalDate date = LocalDate.of(2023, 8, 8);
		Employee emp = new PartTimeEmployee("John Doe", date, hourlyRate, 100);
		assertEquals(new PartTimeEmployee("John Doe", date, hourlyRate, 100), emp);
	}
	
	@Test
	void testParTimeEmployeeEqualityNotEquals() {
		BigDecimal hourlyRate = new BigDecimal("10");
		LocalDate date = LocalDate.of(2023, 8, 8);
		Employee emp = new PartTimeEmployee("John Doe", date, hourlyRate, 100);
		assertNotEquals(new PartTimeEmployee("John Doe", date, hourlyRate, 200), emp);
	}
	
	@Test
	public void testCalculatePayToDateWithValidDate() {
	    LocalDate currentDate = LocalDate.of(2023, 9, 8);
	    BigDecimal expectedTotalPay = new BigDecimal("250").multiply(BigDecimal.valueOf(100).multiply(BigDecimal.valueOf(44))).setScale(2);
	    BigDecimal actualTotalPay = partTimeEmployee.calculatePayToDate(currentDate);
	    assertEquals(expectedTotalPay, actualTotalPay);
	}
	
	@Test
	public void testCalculatePayToDateWithNullDate() {
	    BigDecimal actualTotalPay = partTimeEmployee.calculatePayToDate(null);
	    assertEquals(BigDecimal.ZERO, actualTotalPay);
	}

	@Test
	public void testCalculatePayToDateWithEarlierDate() {
	    LocalDate hireDate = LocalDate.of(2020, 1, 1);
	    PartTimeEmployee partTimeEmployee = new PartTimeEmployee("Alice", hireDate, new BigDecimal("20"), 100);
	    LocalDate earlierDate = LocalDate.of(2019, 12, 31);
	    BigDecimal actualTotalPay = partTimeEmployee.calculatePayToDate(earlierDate);
	    assertEquals(BigDecimal.ZERO, actualTotalPay);
	}
	
	@Test
    void testPartTimeEmployeeEqual() {
    	Employee employee = new PartTimeEmployee("John Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.00"), 100);
    	assertEquals(new PartTimeEmployee("John Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.00"), 100), employee);
    }
	
	@Test
    void testPartTimeEmployeeNotEqual() {
    	Employee employee = new PartTimeEmployee("John Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.0"), 100);
    	assertNotEquals(new PartTimeEmployee("John Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.00"), 200), employee);
    }
}
