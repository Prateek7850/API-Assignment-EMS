package com.ems.Service;

import java.util.List;

import com.ems.entity.Employee;

public interface EmployeeService {
        
	    public void addNewEmployee(Employee employee);

		public Employee getEmployeeById(Long id);

		public List<Employee> getAllEmployee();

		public Employee updateEmployee(Employee employee);

		public void deleteEmployeeById(Long id);
    
}
