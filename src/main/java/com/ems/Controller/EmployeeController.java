package com.ems.Controller;

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

import com.ems.Service.EmployeeService;
import com.ems.entity.Employee;

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
		      employeeService.addNewEmployee(employee);   
		return new ResponseEntity<>("Employee Added!",HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Employee employee = employeeService.getEmployeeById(id);	
		return new  ResponseEntity<>(employee,HttpStatus.ACCEPTED);		
	}
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getAllEmployee(){
		List<Employee> employee = employeeService.getAllEmployee();	
		return new  ResponseEntity<>(employee,HttpStatus.ACCEPTED);		
	}
	
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
		    Employee employeeUpdated = employeeService.updateEmployee(employee);
		return new ResponseEntity<>(employeeUpdated,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long id){
		                  employeeService.deleteEmployeeById(id);
		return new ResponseEntity<>("Employee Deleted!",HttpStatus.ACCEPTED);
	}
}
