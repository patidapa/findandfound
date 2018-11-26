package com.findfound.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler{
	//@ExceptionHandler(value= UserNotFound.class)
	public ResponseEntity<Object> exception( UserNotFound exception)
	{
		return new ResponseEntity<>("User not exist with Given Id",HttpStatus.NOT_FOUND);
	}

}
