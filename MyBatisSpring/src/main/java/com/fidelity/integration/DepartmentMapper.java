package com.fidelity.integration;

import java.util.List;

import com.fidelity.business.Department;

public interface DepartmentMapper {
	List<Department> getAllDepartments();

	List<Department> getAllDepartmentsByDeptNo(int id);

	int insertDepartment(Department department);
	
	List<Department> getAllEmployeesByDept();
}
