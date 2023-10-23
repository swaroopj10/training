package com.fidelity.freight;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OfficerTest {
	
	CrewMember crewMember;
	@BeforeEach
	void setUp() throws Exception {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String rank = "Captain";
		BigDecimal salary = new BigDecimal("1000");
		crewMember = new Officer(name, id, payDay, rank, salary);
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
		String rank = "Captain";
		BigDecimal salary = new BigDecimal("1000");
		assertThrows(NullPointerException.class, ()-> {
			crewMember = new Officer(name, id, payDay,  rank, salary);
		});
	}
	
	@Test
	void WrongPayDay() {
		String name = "John Doe";
		int id = 101;
		int payDay = 45;
		String rank = "Captain";
		BigDecimal salary = new BigDecimal("1000");
		assertThrows(IllegalArgumentException.class, ()-> {
			crewMember = new Officer(name, id, payDay,  rank, salary);
		});
	}
	
	@Test
	void RankNotNull() {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String rank = null;
		BigDecimal salary = new BigDecimal("1000");
		assertThrows(NullPointerException.class, ()-> {
			crewMember = new Officer(name, id, payDay,  rank, salary);
		});
	}
	
	@Test
	void salaryEqualZero() {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String rank = "Captain";
		BigDecimal salary = new BigDecimal("0");
		assertThrows(IllegalArgumentException.class, ()-> {
			crewMember = new Officer(name, id, payDay,  rank, salary);
		});
	}
	
	@Test
	void salaryLessThanZero() {
		String name = "John Doe";
		int id = 101;
		int payDay = 5;
		String rank = "Captain";
		BigDecimal salary = new BigDecimal("-2");
		assertThrows(IllegalArgumentException.class, ()-> {
			crewMember = new Officer(name, id, payDay,  rank, salary);
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
	void calculatePayDateLessThan15Dys() {
		BigDecimal expectedPay = new BigDecimal("0");
		LocalDate date = LocalDate.of(2023, 9, 10);
		assertEquals(expectedPay, crewMember.calculatePay(date));
	}
	
	@Test
	void calculatePay() {
		BigDecimal expectedPay = new BigDecimal("1000");
		LocalDate date = LocalDate.of(2023, 9, 24);
		assertEquals(expectedPay, crewMember.calculatePay(date));
	}
}
