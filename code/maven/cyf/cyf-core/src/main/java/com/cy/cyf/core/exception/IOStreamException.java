package com.cy.cyf.core.exception;

public class IOStreamException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public IOStreamException(){
		super();
	}
	
	public IOStreamException(String message){
		super(message);
	}
	
	public IOStreamException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public IOStreamException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
