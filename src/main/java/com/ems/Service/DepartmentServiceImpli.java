package com.ems.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ems.Repository.DepartmentRepository;
import com.ems.entity.Department;

@Service
public class DepartmentServiceImpli implements DepartmentService{

	@Autowired
	public DepartmentRepository departmentRepository;
	
	@Override
	public void addDepartment(Department department) {
		departmentRepository.save(department);
	}

	@Override
	public List<Department> getAllDepartment() {
		List<Department> department = departmentRepository.findAll();
		return department;
	}

	@Override
	public Department getDepartmentById(Long id) {
		 Optional<Department> department = departmentRepository.findById(id);		  
		return department.get();
	}

	@Override
	public Department updateDepartment(Department department) {
		   Department departmentUpdated = departmentRepository.save(department);
		return departmentUpdated;
	}

	@Override
	public void deleteById(Long id) {
		      departmentRepository.deleteById(id);
	}
    	
}
