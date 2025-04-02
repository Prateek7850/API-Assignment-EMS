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

import com.ems.Service.DepartmentService;
import com.ems.entity.Department;
import com.ems.entity.Employee;

@RestController
@RequestMapping("/api/")
public class DepartmentController {

	@Autowired 
	public DepartmentService departmentService;
    
     @PostMapping("department")
      public ResponseEntity<String> addDepartment(@RequestBody Department department){
    	 departmentService.addDepartment(department);
    	 return new ResponseEntity("Department Added!",HttpStatus.OK);
     }
     
     @GetMapping("department")
     public ResponseEntity<List<Department>> getAllDepartment(){
    	 List<Department> department = departmentService.getAllDepartment();
    	 return new ResponseEntity(department,HttpStatus.OK);
     }
     
     @GetMapping("department/{id}")
     public ResponseEntity<Department> getEmployeeById(@PathVariable Long id){
    	 Department department = departmentService.getDepartmentById(id);
    	 return new ResponseEntity<>(department,HttpStatus.OK);
     }
     
     @PutMapping("department")
     public ResponseEntity<Department> updateEmployee(@RequestBody Department department){
    	 Department departmentUpdated = departmentService.updateDepartment(department);
    	 return new ResponseEntity<>(departmentUpdated,HttpStatus.OK);
     }
     
     @DeleteMapping("department/{id}")
     public ResponseEntity<String> deleteEmployee(@PathVariable Long id){
    	         departmentService.deleteById(id);
    	 return new ResponseEntity<>("DEPARTMENT Deleted!",HttpStatus.OK);
     }
}
