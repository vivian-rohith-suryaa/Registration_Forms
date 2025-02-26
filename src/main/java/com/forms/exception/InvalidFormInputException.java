package com.forms.exception;

public class InvalidFormInputException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public InvalidFormInputException(String message){
		super(message);
	}
	
	public InvalidFormInputException(String message,Throwable cause){
		super(message,cause);
	}
}
