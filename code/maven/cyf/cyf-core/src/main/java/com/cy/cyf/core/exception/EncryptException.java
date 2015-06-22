package com.cy.cyf.core.exception;

public class EncryptException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public EncryptException(){
		super();
	}
	
	public EncryptException(String message){
		super(message);
	}
	
	public EncryptException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public EncryptException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
