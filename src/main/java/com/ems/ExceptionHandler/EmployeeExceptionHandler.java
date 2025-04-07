package com.ems.exceptionhandler;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;

@RestControllerAdvice
public class EmployeeExceptionHandler extends RuntimeException {

           @ExceptionHandler(EmployeeNotFoundException.class)
           public ResponseEntity<String> employeeNotFoundException(EmployeeNotFoundException exception){
        	   return new ResponseEntity(exception.getMessage(),HttpStatus.NOT_FOUND);
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
           @ExceptionHandler(Exception.class)
           public ResponseEntity<String> handleGenericException(Exception ex) {
               return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
           }
           @ExceptionHandler(ExpiredJwtException.class)
           public ResponseEntity<String> handleJwtExpiration(ExpiredJwtException exception){
        	   return ResponseEntity
        	            .status(HttpStatus.UNAUTHORIZED)
        	            .body("Your session has expired. Please log in again.");
           }
           @ExceptionHandler(CustomExpiredJwtException.class)
           public ResponseEntity<String> handleJwtExpirationCustom(CustomExpiredJwtException exception){
        	   return new ResponseEntity<>("An error occurred: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
           }
}
