package com.cy.cyf.exception;

public class HTTPException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public HTTPException(){
		super();
	}
	
	public HTTPException(String message){
		super(message);
	}
	
	public HTTPException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public HTTPException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
