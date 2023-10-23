package com.fidelity.integration;

import java.util.List;

import javax.sql.DataSource;

import com.fidelity.model.Employee;

public abstract class EmployeeDao {
	private DataSource dataSource;
	
	public EmployeeDao(DataSource datasource) {
		this.dataSource = datasource;
	}
	
	protected DataSource getDataSource() {
		return dataSource;
	}

	public abstract List<Employee> queryAllEmployees();

	public abstract Employee queryEmployeeById(int empNo);
	
	public List<Employee> queryEmployeeByName(String name) {
		return null;
	}
	
	public List<Employee> queryEmployeeByDeptName(int deptNo) {
		return null;
	}

	public abstract void insertEmployee(Employee newEmployee);
	
	public abstract void updateEmployee(Employee employee);
	
	public abstract void deleteEmployee(Employee employee);

	public abstract List<Employee> queryAllEmployeesWithPerformanceReview();

	public abstract List<Employee> queryAllEmployeesWithPerformanceReviewWithEmpNo(int empNo);
}
