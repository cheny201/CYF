package com.cy.cyf.exception;

public class SFTPException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public SFTPException(){
		super();
	}
	
	public SFTPException(String message){
		super(message);
	}
	
	public SFTPException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public SFTPException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
