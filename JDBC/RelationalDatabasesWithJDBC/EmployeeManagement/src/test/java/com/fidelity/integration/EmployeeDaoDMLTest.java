package com.fidelity.integration;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;
import static org.junit.jupiter.api.Assertions.*;

import com.fidelity.model.Employee;
import com.fidelity.model.PerformanceReview;

class EmployeeDaoDMLTest {
	private JdbcTemplate jdbcTemplate;
	private DbTestUtils dbTestUtils;
	private EmployeeDao dao;
	private SimpleDataSource dataSource;
	private Connection connection;
	private TransactionManager transactionManager;
	private Employee emp7369;
	private Employee emp7934;
	  
	@BeforeEach
	void setUp() throws SQLException {
		dataSource = new SimpleDataSource();
		connection = dataSource.getConnection();
		dao = new EmployeeDaoOracleImpl(dataSource);
		transactionManager = new TransactionManager(dataSource);
		transactionManager.startTransaction(); 
		// Start the transaction
		
		dbTestUtils = new DbTestUtils(connection);		
		jdbcTemplate = dbTestUtils.initJdbcTemplate();
		
		
		// Note format of date depends on query and/or database parameters
		// NULL commission handled correctly
		emp7369 = new Employee(7369, "BRAD", "CEO", 7902, LocalDate.of(1980, 12,	17), new BigDecimal("800.00"), null, 20);
		emp7934 = new Employee(7934, "MILLER", "CLERK", 7782, LocalDate.of(1982, 01, 23), new BigDecimal("1300.00"), null, 10);
	}

	@AfterEach
	void tearDown() throws SQLException {
		transactionManager.rollbackTransaction();
		dataSource.shutdown();
	}

	@Test
	void testInsertEmployee() throws SQLException {
		int oldSize = JdbcTestUtils.countRowsInTable(jdbcTemplate, "emp");
		assertEquals(0, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "emp", "empno = 8000"));
		Employee newEmployee = new Employee(8000, "HEYES", "ANALYST", 7934, LocalDate.of(1980, 12, 17), new BigDecimal("500.00"), null, 10);
		dao.insertEmployee(newEmployee);
		assertEquals(oldSize + 1, JdbcTestUtils.countRowsInTable(jdbcTemplate, "emp"));
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "emp", "empno = 8000"));
	}
	
	@Test
	void testInsertEmployeeForAllFields() throws SQLException {
		Employee newEmployee = new Employee(8000, "HEYES", "ANALYST", 7934, LocalDate.of(1980, 12, 17), new BigDecimal("500.00"), null, 10);
		dao.insertEmployee(newEmployee);
		assertEquals(1, JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "emp", """
				empno = 8000
				AND ename = 'HEYES'
				AND job = 'ANALYST'
				AND mgr = 7934
				AND hiredate = '17-DEC-80'
				AND sal = 500.00
				AND comm IS NULL
				AND deptno = 10
				"""));
	}
	
	@Test
	void testDeleteEmployee() throws SQLException {
		int oldSize = countRowsInTable(jdbcTemplate, "emp");
		dao.deleteEmployee(emp7934);
		int newSize = countRowsInTable(jdbcTemplate, "emp");
		assertEquals(oldSize - 1, newSize);
	}
	
	@Test
	void testUpdateEmployee() throws SQLException {
		int expectedRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "emp");
		String whereCondition = "empno = 7369 and ename = 'BRAD' and job='CEO'";
		int rowcount = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "emp", whereCondition);
		assertEquals(0, rowcount);
		dao.updateEmployee(emp7369);
		int actualRows = JdbcTestUtils.countRowsInTable(jdbcTemplate, "emp");
		assertEquals(expectedRows, actualRows);
		rowcount = JdbcTestUtils.countRowsInTableWhere(jdbcTemplate, "emp", whereCondition);
		assertEquals(1, rowcount);

	}
	
	@Test
	void testInsertDuplicatePrimaryKeyThrowsException() {
		int duplicatePrimaryKey = 7369;
		LocalDate hireDate = LocalDate.parse("1980-12-17");
		assertThrows(DatabaseException.class, () -> {
			Employee upd7369 = new Employee(duplicatePrimaryKey, "HEYES", "ANALYST", 7934, hireDate,
					new BigDecimal("500.00"), new BigDecimal("2"), 10);
			dao.insertEmployee(upd7369);
		});
	}

	@Test
	void retrieveQueryAllEmployeeWithPerfomanceReviewFirstElement() {
		List<Employee> employeeList=dao.queryAllEmployeesWithPerformanceReview();
		assertEquals(PerformanceReview.ABOVE, employeeList.get(0).getPerformanceReview());
	}
	
	@Test
	void retrieveQueryAllEmployeeWithPerfomanceReviewWithEmpNoFirstElement() {
		List<Employee> employeeList=dao.queryAllEmployeesWithPerformanceReviewWithEmpNo(7521);
		assertEquals(PerformanceReview.AVERAGE, employeeList.get(0).getPerformanceReview());
	}

	
	/***** Utility Methods *****/
	private void beginTransaction() throws SQLException {
		connection.setAutoCommit(false);
	}
	
	private void rollbackTransaction() throws SQLException {
		connection.rollback();	
	}
}
