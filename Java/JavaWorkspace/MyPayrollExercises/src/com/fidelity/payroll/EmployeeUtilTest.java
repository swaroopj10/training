package com.fidelity.payroll;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmployeeUtilTest {

	private List<Employee> employee;
	private Employee fullTime;
	private Employee partTime;
	private Employee consultant;

	@BeforeEach
	void setUp() {
		employee = new ArrayList<>();
		fullTime = new FullTimeEmployee("John Smith", LocalDate.of(2018, 8, 18), new BigDecimal("10000.0"));
		partTime = new PartTimeEmployee("Jane Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.0"), 100);
		consultant = new Consultant("Walter White", LocalDate.of(2018, 7, 11), new BigDecimal("8000.0"), 10);
		employee.add(fullTime);
		employee.add(partTime);
		employee.add(consultant);
	}

	@Test
	void testTotalSalary() {
		assertEquals(new BigDecimal("2633.33"), EmployeeUtil.calcTotalMonthlyPay(employee));
	}

	@Test
	void testTotalSalaryAdd() {
		employee.add(new FullTimeEmployee("Jon Snow", LocalDate.of(2019, 1, 20), new BigDecimal("14000.0")));
		assertEquals(new BigDecimal("3800.00"), EmployeeUtil.calcTotalMonthlyPay(employee));
	}
	
	@Test
	void testTotalToDate() {
		assertEquals(new BigDecimal("8166.65"), EmployeeUtil.calcTotalPayToDate(employee, LocalDate.of(2019, 1, 1)));
	}

	@Test
	void testTotalToDateChange() {
		assertEquals(new BigDecimal("4066.66"), EmployeeUtil.calcTotalPayToDate(employee, LocalDate.of(2018, 10, 11)));
	}

	@Test
	void testTotalToDateNull() {
		assertEquals(new BigDecimal("0.00"), EmployeeUtil.calcTotalPayToDate(null, LocalDate.of(2018, 8, 11)));
	}

	@Test
	void testTotalSalaryNull() {
		assertEquals(new BigDecimal("0.00"), EmployeeUtil.calcTotalMonthlyPay(null));
	}
	
	@Test
	void testSort() {
		EmployeeUtil.sort(employee);
		assertEquals(partTime, employee.get(0));
		assertEquals(fullTime, employee.get(1));
		assertEquals(consultant, employee.get(2));
	}
	
	@Test
	void employeeListSortingByMonthlyPayUsingAnonymousInnerClassWorks() {
		List<Employee> employeesSortedByMonthlyPay = new ArrayList<>();
		employeesSortedByMonthlyPay.add(new PartTimeEmployee("Jane Doe", LocalDate.of(2019, 1, 1), new BigDecimal("10.0"), 100));
		employeesSortedByMonthlyPay.add(new FullTimeEmployee("John Smith", LocalDate.of(2018, 8, 18), new BigDecimal("10000.0")));
		employeesSortedByMonthlyPay.add(new Consultant("Walter White", LocalDate.of(2018, 7, 11), new BigDecimal("8000.0"), 10));
	
		EmployeeUtil.sort(employee, new Comparator<Employee>() {
			@Override
			public int compare(Employee employee1, Employee employee2) {
				int monthlyPayComparison = employee2.calculateMonthlyPay().compareTo(employee1.calculateMonthlyPay());
				if(monthlyPayComparison == 0) {
					return employee1.getName().compareTo(employee2.getName());
				}
				return monthlyPayComparison;
			}
		});
		assertEquals(employeesSortedByMonthlyPay.get(0), employee.get(0));
	}
}
