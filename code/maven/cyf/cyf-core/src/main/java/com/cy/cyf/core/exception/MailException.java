package com.cy.cyf.core.exception;

public class MailException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	protected String ERROR_CODE;
	
	public MailException(){
		super();
	}
	
	public MailException(String message){
		super(message);
	}
	
	public MailException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public MailException(Throwable throwable){
		super(throwable);
	}
	
	public MailException setCode(String errorCode){
		this.ERROR_CODE = errorCode;
		return this;
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
