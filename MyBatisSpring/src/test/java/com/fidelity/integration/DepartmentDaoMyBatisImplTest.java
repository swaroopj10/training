package com.fidelity.integration;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fidelity.business.Department;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:beans.xml")
class DepartmentDaoMyBatisImplTest {
	
	@Autowired
	DepartmentDaoMyBatisImpl dao;
	
	@Test 
	void queryAllDepartmentNotNull() {
		List<Department> departmentList = dao.getAllDepartments();
		assertNotNull(departmentList);
	}
	
	@Test
	void queryAllDepartmentsReturnList() {
		List<Department> departmentList = dao.getAllDepartments();
		assertTrue(departmentList.size() > 0);
	}
	
	@Test
	void queryAllDepartmentsWithDeptNoNotNull() {
		int id = 10;
		List<Department> departmentList = dao.getAllDepartmentsByDeptNo(id);
		assertNotNull(departmentList);
	}
	
	@Test
	void queryAllDepartmentsWithDeptNoReturnList() {
		int id = 10;
		List<Department> departmentList = dao.getAllDepartmentsByDeptNo(id);
		assertTrue(departmentList.size() > 0);
	}
	
	@Test
    public void getEmployeeByDepartmentNotNull() {
    	List<Department> departmentList = dao.getAllEmployeesByDept();
    	assertNotNull(departmentList);
    }
	
	@Test
    public void getEmployeeByDepartment() {
    	List<Department> departmentList = dao.getAllEmployeesByDept();
    	assertTrue(departmentList.size() > 0);
    }
	
	@Test
	public void sizeOfEmployeeList() {
		List<Department> departmentList = dao.getAllEmployeesByDept();
		assertEquals(3, departmentList.size());
	}
	
	@Test
	public void sizeOfEmployeeListDept() {
		List<Department> departmentList = dao.getAllEmployeesByDept();
		assertEquals(3, departmentList.get(0).getEmployees().size());
	}
	
	@Test
	public void sizeOfEmployeeListDept2() {
		List<Department> departmentList = dao.getAllEmployeesByDept();
		assertEquals(5, departmentList.get(1).getEmployees().size());
	}
	
//	@Test
//    public void testInsertValidDepartment() {
//        Department department = new Department(50, "ENGINEERING", "AUSTIN");
//        int rowsAffected = dao.insertDepartment(department);
//        assertEquals(1, rowsAffected);
//    }
//
//    @Test
//    public void testInsertMultipleDepartments() {
//        Department department1 = new Department(60, "SUPPORT", "NEW YORK");
//        Department department2 = new Department(70, "HR", "CHICAGO");
//        
//        int rowsAffected1 = dao.insertDepartment(department1);
//        int rowsAffected2 = dao.insertDepartment(department2);
//        
//        assertEquals(1, rowsAffected1);
//        assertEquals(1, rowsAffected2);
//    }
//
//    @Test
//    public void testInsertNullDepartment() {
//        Department department = null;
//        
//        assertThrows(Exception.class, () -> {
//            dao.insertDepartment(department);
//        });
//    }
//
//    @Test
//    public void testInsertMissingFields() {
//        Department department = new Department(100, null, "NEW JERSEY");
//
//        assertThrows(Exception.class, () -> {
//            dao.insertDepartment(department);
//        });
//    }
}
