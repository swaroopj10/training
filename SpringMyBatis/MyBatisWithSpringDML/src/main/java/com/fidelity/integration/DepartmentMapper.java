package com.fidelity.integration;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.fidelity.business.Department;

public interface DepartmentMapper {
	@Select("""
			SELECT deptno AS id,
			   dname AS name,
			   loc AS location
		FROM   dept
		ORDER BY
			   id
			""")
	List<Department> getAllDepartments();
	
	@Select("""
			SELECT d.deptno AS id,
			   d.dname,
			   d.loc,
			   e.empno,
			   e.ename,
			   e.job,
			   e.mgr,
			   e.hiredate,
			   e.sal,
			   e.comm,
			   e.deptno
		FROM   dept d
		LEFT OUTER JOIN 
		       emp e
		ON     d.deptno = e.deptno
		ORDER BY
			   id, e.empno
			"""
			)
	@ResultMap("com.fidelity.integration.DepartmentMapper.DepartmentAndEmployees")
	List<Department> getAllDepartmentsAndEmployees();
	
	@Insert("""
			INSERT INTO dept (deptno, dname, loc)
		VALUES (#{id}, #{name}, #{location})
			""")
	boolean insertDepartment(Department newDept);
	
	@Update("""
			UPDATE dept
		SET dname = #{name}, loc = #{location}
		WHERE deptno = #{id}
			""")
	boolean updateDepartment(Department dept);
	
	@Delete("""
			DELETE FROM dept
			WHERE deptno = #{deptNo}
			""")
	boolean deleteDepartment(int deptNo);
}
