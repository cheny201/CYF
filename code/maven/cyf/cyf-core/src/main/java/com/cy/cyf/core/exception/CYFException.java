package com.cy.cyf.core.exception;

public class CYFException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	protected String ERROR_CODE;
	
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
	
	public CYFException setCode(String errorCode){
		this.ERROR_CODE = errorCode;
		return this;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
