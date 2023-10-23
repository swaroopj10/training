package com.fidelity.integration;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fidelity.business.Department;

@Repository("departmentsDao")
public class DepartmentDaoMyBatisImpl {
	@Autowired
	private Logger logger;

	@Autowired
	DepartmentMapper departmentMapper;

	public List<Department> getAllDepartments() {
		logger.debug("enter");
		return departmentMapper.getAllDepartments();
	}

	public List<Department> getAllDepartmentsAndEmployees() {
		logger.debug("enter");
		return departmentMapper.getAllDepartmentsAndEmployees();
	}
	
	@Transactional
	public boolean insertDepartment(Department newDept) {
		return departmentMapper.insertDepartment(newDept);
	}
	
	@Transactional
	public boolean updateDepartment(Department dept) {
		return departmentMapper.updateDepartment(dept);
	}
	
	@Transactional
	public boolean deleteDepartment(int deptNo) {
		return departmentMapper.deleteDepartment(deptNo);
	}
}
