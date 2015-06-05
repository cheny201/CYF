package com.cy.cyf.exception;

public class TCPException extends CYFException {

private static final long serialVersionUID = 1L;
	
	public TCPException(){
		super();
	}
	
	public TCPException(String message){
		super(message);
	}
	
	public TCPException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public TCPException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
