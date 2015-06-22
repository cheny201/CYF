package com.cy.cyf.core.exception;

public class FTPException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public FTPException(){
		super();
	}
	
	public FTPException(String message){
		super(message);
	}
	
	public FTPException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public FTPException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
