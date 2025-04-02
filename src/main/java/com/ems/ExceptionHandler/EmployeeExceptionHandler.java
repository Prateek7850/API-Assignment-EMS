package com.ems.ExceptionHandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionHandler extends RuntimeException {

           @ExceptionHandler(EmployeeNotFoundException.class)
           public ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException exception){
        	   return new ResponseEntity("Employee Not Found",HttpStatus.NOT_FOUND);
           }
           
           @ExceptionHandler(HttpMessageNotReadableException.class)
           public ResponseEntity<String> messageNotReadableException(HttpMessageNotReadableException exception ){  
        	   return new ResponseEntity(exception.getMessage(),HttpStatus.BAD_REQUEST);
           }
           
           @ExceptionHandler(DataIntegrityViolationException.class)
           public ResponseEntity<String> dataIntegrityViolationException(DataIntegrityViolationException exception){
        	   return new ResponseEntity(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
           }
           @ExceptionHandler(JpaObjectRetrievalFailureException.class)
           public ResponseEntity<String> jpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException excepiton){
        	   return new ResponseEntity(excepiton.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
           }
}
