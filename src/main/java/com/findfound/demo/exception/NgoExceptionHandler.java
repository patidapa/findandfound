package com.findfound.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class NgoExceptionHandler {
	@ExceptionHandler(value= NgoNotFound.class)
	public ResponseEntity<Object> exception( NgoNotFound exception)
	{
		return new ResponseEntity<>("Account not exist",HttpStatus.NOT_FOUND);
	}
}
