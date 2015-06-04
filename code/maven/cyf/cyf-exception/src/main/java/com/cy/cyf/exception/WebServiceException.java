package com.cy.cyf.exception;

public class WebServiceException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public WebServiceException(){
		super();
	}
	
	public WebServiceException(String message){
		super(message);
	}
	
	public WebServiceException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public WebServiceException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
