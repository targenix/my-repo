package com.stacksimplify.restservices.exceptions;

public class PersonNotFoundException extends Exception{
	
	private static final long serialVersionUID =1L;
	
	public PersonNotFoundException(String message) {
		super(message);
	}

}
