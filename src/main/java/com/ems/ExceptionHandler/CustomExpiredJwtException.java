package com.ems.exceptionhandler;

public class CustomExpiredJwtException extends RuntimeException{
       
	public CustomExpiredJwtException(String message) {
		super(message);
	}
}
