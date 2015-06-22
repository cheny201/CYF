package com.cy.cyf.core.exception;

public class SecurityException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public SecurityException(){
		super();
	}
	
	public SecurityException(String message){
		super(message);
	}
	
	public SecurityException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public SecurityException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
