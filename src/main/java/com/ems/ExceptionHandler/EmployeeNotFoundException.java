package com.ems.ExceptionHandler;

import org.springframework.http.HttpStatus;

public class EmployeeNotFoundException  extends RuntimeException{
   
	public String message;
    public HttpStatus status;
    
	
	public EmployeeNotFoundException(String string,HttpStatus status) {
	 
	}

}
