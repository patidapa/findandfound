package com.findfound.demo.exception;

public class UserNotFound extends RuntimeException{
	
	public UserNotFound(String message)
	{
		super(message);
	}

}
