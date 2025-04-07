package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ems.entity.Employee;
import com.ems.exceptionhandler.EmployeeExceptionHandler;
import com.ems.exceptionhandler.EmployeeNotFoundException;
import com.ems.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {
    
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/home")
	public String employeeHone() {
		return "home";
	}
     
	@PostMapping("/employees")
	public ResponseEntity<String> addNewEmployee(@RequestBody Employee employee){        
		try {      
		employeeService.addNewEmployee(employee);
		      
		return new ResponseEntity<>("Employee Added!",HttpStatus.ACCEPTED);
		}catch(EmployeeNotFoundException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Long id){
		try {
		Employee employee = employeeService.getEmployeeById(id);	
		return new  ResponseEntity<>(employee,HttpStatus.ACCEPTED);		
		}catch(EmployeeNotFoundException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		}
	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployee(){
		try {
		List<Employee> employee = employeeService.getAllEmployee();	
		return new  ResponseEntity<>(employee,HttpStatus.ACCEPTED);		
		}catch(EmployeeNotFoundException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		}
	
	@PutMapping("/employees")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
		try {
		    Employee employeeUpdated = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(employeeUpdated,HttpStatus.ACCEPTED);
		}catch(EmployeeNotFoundException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id){
		try {
		                  employeeService.deleteEmployeeById(id);
		return new ResponseEntity<>("Employee Deleted!",HttpStatus.ACCEPTED);
		}catch(EmployeeNotFoundException exception) {
			 return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
		}
		}
}
