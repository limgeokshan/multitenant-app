package com.smu.multitenantapp.exception;

public class LookupNotFoundException extends RuntimeException{

	public LookupNotFoundException(String msg) {
		super(msg);
	}
	
	public LookupNotFoundException(String msg, Throwable t) {
		super(msg,t);
	}
	
	

}
