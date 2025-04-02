package com.ems.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.Repository.EmployeeRepository;
import com.ems.entity.Designation;
import com.ems.entity.Employee;

@Service
public class EmployeeServiceImpli implements EmployeeService {

	private EmployeeRepository employeeRepostory;
	
	@Autowired
	public EmployeeServiceImpli(EmployeeRepository employeeRepostory) {
		super();
		this.employeeRepostory = employeeRepostory;
	}


	@Override
	public void addNewEmployee(Employee employee) {
		if(employee.getDesignation()==Designation.JUNIOR_DEVELOPER) {
			employee.setSalary(50000);
		}else if(employee.getDesignation()==Designation.MANAGER) {
			employee.setSalary(100000);
		}else if(employee.getDesignation()==Designation.SENIOR_DEVELOPER) {
			employee.setSalary(150000);
		}
		Employee i = employeeRepostory.save(employee);
	}


	@Override
	public Employee getEmployeeById(Long id) {
		Optional<Employee> employee = employeeRepostory.findById(id);
		Employee empoyee = employee.get();
		return empoyee;
	}


	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> employee = employeeRepostory.findAll();
		return employee;
	}


	@Override
	public Employee updateEmployee(Employee employee) {
	     Employee employeeUpdated = employeeRepostory.save(employee);
		return employeeUpdated;
	}


	@Override
	public void deleteEmployeeById(Long id) {
	   	employeeRepostory.deleteById(id);
	}

}
