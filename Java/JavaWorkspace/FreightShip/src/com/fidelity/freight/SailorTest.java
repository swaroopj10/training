package com.fidelity.freight;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SailorTest {
	
	CrewMember crewMember;
	@BeforeEach
	void setUp() throws Exception {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String jobDescription = "Day Shift";
		BigDecimal hourlyRate = new BigDecimal("12");
		int hoursWorked = 10;
		crewMember = new Sailor(name, id, payDay, jobDescription, hourlyRate, hoursWorked);
	}

	@AfterEach
	void tearDown() throws Exception {
		crewMember = null;
	}

	@Test
	void NotNull() {
		assertNotNull(crewMember);
	}
	
	@Test
	void NameNotNull() {
		String name = null;
		int id = 101;
		int payDay = 5;
		String jobDescription = "Day Shift";
		BigDecimal hourlyRate = new BigDecimal("12");
		int hoursWorked = 10;
		assertThrows(NullPointerException.class, ()-> {
			crewMember = new Sailor(name, id, payDay, jobDescription, hourlyRate, hoursWorked);
		});
	}
	
	@Test
	void WrongPayDay() {
		String name = "John Doe";
		int id = 101;
		int payDay = 45;
		String jobDescription = "Day Shift";
		BigDecimal hourlyRate = new BigDecimal("12");
		int hoursWorked = 10;
		assertThrows(IllegalArgumentException.class, ()-> {
			crewMember = new Sailor(name, id, payDay, jobDescription, hourlyRate, hoursWorked);
		});
	}
	
	@Test
	void JobDescriptionNotNull() {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String jobDescription = null;
		BigDecimal hourlyRate = new BigDecimal("12");
		int hoursWorked = 10;
		assertThrows(NullPointerException.class, ()-> {
			crewMember = new Sailor(name, id, payDay, jobDescription, hourlyRate, hoursWorked);
		});
	}
	
	@Test
	void WrongHourlyRate() {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String jobDescription = "Day Shift";
		BigDecimal hourlyRate = new BigDecimal("0");
		int hoursWorked = 10;
		assertThrows(IllegalArgumentException.class, ()-> {
			crewMember = new Sailor(name, id, payDay, jobDescription, hourlyRate, hoursWorked);
		});
	}
	
	@Test
	void WrongHoursWorked() {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String jobDescription = "Day Shift";
		BigDecimal hourlyRate = new BigDecimal("12");
		int hoursWorked = -1;
		assertThrows(IllegalArgumentException.class, ()-> {
			crewMember = new Sailor(name, id, payDay, jobDescription, hourlyRate, hoursWorked);
		});
	}
	
	@Test
	void calculatePayNotNull() {
		LocalDate date = LocalDate.of(2023, 9, 11);
		assertNotNull(crewMember.calculatePay(date));
	}
	
	@Test
	void calculatePayDateNotNull() {
		assertThrows(NullPointerException.class, ()-> {
			crewMember.calculatePay(null);
			});
	}
	
	@Test
	void calculatePayDateLessThanPayDay() {
		BigDecimal expectedPay = new BigDecimal("0");
		LocalDate date = LocalDate.of(2023, 9, 4);
		assertEquals(expectedPay, crewMember.calculatePay(date));
	}
	
	@Test
	void calculatePay() {
		BigDecimal expectedPay = new BigDecimal((24-5)*12*10);
		LocalDate date = LocalDate.of(2023, 9, 24);
		assertEquals(expectedPay, crewMember.calculatePay(date));
	}
}
