package com.fidelity.integration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.fidelity.business.Department;

@Primary
@Repository("departmentsDao")
public class DepartmentDaoMyBatisImpl implements DepartmentMapper{
	
	@Autowired
	private DepartmentMapper departmentMapper;

	@Override
	public List<Department> getAllDepartments() {
		return departmentMapper.getAllDepartments();
	}
	
	@Override
	public List<Department> getAllDepartmentsByDeptNo(int id) {
		return departmentMapper.getAllDepartmentsByDeptNo(id);
	}
	
	@Override
	public int insertDepartment(Department department) {
		return departmentMapper.insertDepartment(department);
	}

	@Override
	public List<Department> getAllEmployeesByDept() {
		return departmentMapper.getAllEmployeesByDept();
	}
}
