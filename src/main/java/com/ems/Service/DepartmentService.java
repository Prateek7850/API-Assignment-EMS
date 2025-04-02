package com.ems.Service;

import java.util.List;

import com.ems.entity.Department;

public interface DepartmentService {
            
	public void addDepartment(Department department);

	public List<Department> getAllDepartment();

	public Department getDepartmentById(Long id);

	public Department updateDepartment(Department department);

	public void deleteById(Long id);
}
