package com.cy.cyf.exception;

public class CYFException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public CYFException(){
		super();
	}
	
	public CYFException(String message){
		super(message);
	}
	
	public CYFException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public CYFException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
