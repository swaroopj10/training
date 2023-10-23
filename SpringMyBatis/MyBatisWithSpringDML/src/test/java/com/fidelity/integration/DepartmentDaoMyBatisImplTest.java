package com.fidelity.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTableWhere;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

import com.fidelity.business.Department;
import com.fidelity.business.Employee;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
@Transactional
class DepartmentDaoMyBatisImplTest {

	@Autowired
	private DepartmentDaoMyBatisImpl dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private List<Department> allDepartmentsOnly = Arrays.asList(
		new Department(10, "ACCOUNTING", "NEW YORK"),
		new Department(20, "RESEARCH", "DALLAS"),
		new Department(30, "SALES", "CHICAGO"),
		new Department(40, "OPERATIONS", "BOSTON")
	);

	@Test
	void testGetAllDepartments() {

		List<Department> departments = dao.getAllDepartments();

		// getAllDepartments() doesn't select Employees, so all Departments employees lists are null
		assertEquals(allDepartmentsOnly, departments);
	}

	@Test
	void testGetAllDepartmentsAndEmployees() {
		// ARRANGE
		var emp7782 = new Employee(7782, "CLARK", "MANAGER", 7839, LocalDate.of(1981, 6, 9), 2450.0, 0.0, 10);
		var emp7839 = new Employee(7839, "KING", "PRESIDENT", 0, LocalDate.of(1981, 11, 17), 5000.0, 0.0, 10);
		var emp7934 = new Employee(7934, "MILLER", "CLERK", 7782, LocalDate.of(1982, 1, 23), 1300.0, 0.0, 10);

		var dept10 = new Department(10, "ACCOUNTING", "NEW YORK", 
									  Arrays.asList(emp7782, emp7839, emp7934));
		var dept40 = new Department(40, "OPERATIONS", "BOSTON", Arrays.asList());
		
		// ACT
		var departments = dao.getAllDepartmentsAndEmployees();
		
		// ASSERT
		assertEquals(4, departments.size());
		assertTrue(departments.contains(dept10));  // verifies Department properties, including Employees list
		assertTrue(departments.contains(dept40));
	}

	// TODO: Review the following test case. Be sure that you understand the code.
	//       Then implement the required method in the DAO.
	@Test
	void testInsertDepartment() {
		// ARRANGE
		var rowCount = countRowsInTable(jdbcTemplate, "dept");
		// new department to be inserted into the database
		var newDept = new Department(99, "Test Dept", "Test Loc");		

		// ACT
		var success = dao.insertDepartment(newDept);

		// ASSERT
		assertTrue(success);
		// verify that the number of department rows increased by 1
		assertEquals(rowCount + 1, countRowsInTable(jdbcTemplate, "dept"));
		// Verify that all Department properties were inserted correctly.
		assertEquals(1, countRowsInTableWhere(jdbcTemplate, "dept", """
								 deptno = 99
							 and dname = 'Test Dept'
							 and loc = 'Test Loc'
						 """));

		// Alternative verification technique: 
		// Verify a row was inserted correctly by creating a Map of the expected database values 
		// and comparing it with the actual database values using the areEqual() helper method 
		// from DbTestUtils
//		// First, select the new row from the database.
//		Map<String, Object> deptFromDb = jdbcTemplate.queryForMap(
//			"select deptno, dname, loc from dept where deptno = " + newDept.getId());
//		// {"deptno": 50, "dname": "Test Dept", "loc": "Test Loc"} 
//		// Next, create a Map of the new row's expected column values.
//		Map<String, Object> expectedDept = Map.of(
//			"deptno", newDept.getId(),
//			"dname", newDept.getName(),
//			"loc", newDept.getLocation()
//		);
//		// Finally, compare the two maps using a helper function.
//		assertTrue(DbTestUtils.mapsAreEqual(expectedDept, deptFromDb));
	}

	// TODO: after testInsertDepartment() passes, write a test case
	//       for a new updateDepartment() method in the DAO.
	// Note: the requirement for updateDepartment() is to update the
	//       DEPT table only; you don't need to update the EMP table.
	//       IRL it's more complicated: employees may have been added to 
	//       or deleted from the department, which makes the update logic
	//       much more complex.
	@Test
	void updateDepartment() {
		var newDept = new Department(99, "Test Dept", "Test Loc");		
		dao.insertDepartment(newDept);
		var dept = new Department(99, "Test", "Test");		

		var success = dao.updateDepartment(dept);

		assertTrue(success);
		assertEquals(0, countRowsInTableWhere(jdbcTemplate, "dept", """
								 deptno = 99
							 and dname = 'Test Dept'
							 and loc = 'Test Loc'
						 """));

	}
	
	@Test
	void deleteDepartment() {
		var rowCount = countRowsInTable(jdbcTemplate, "dept");
		var success = dao.deleteDepartment(40);		

		assertTrue(success);
		assertEquals(0, countRowsInTableWhere(jdbcTemplate, "dept", """
								 deptno = 40"""));
		assertEquals(rowCount - 1, countRowsInTable(jdbcTemplate, "dept"));
	}
}
