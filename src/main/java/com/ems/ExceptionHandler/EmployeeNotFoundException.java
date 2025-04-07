package com.ems.exceptionhandler;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException  extends RuntimeException{
   

    
    public EmployeeNotFoundException() {
   	     super();
	}
	
	public EmployeeNotFoundException(String message) {
	               super(message);
	}

}
