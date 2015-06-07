package com.cyf.exception;

/**
 * CYF自定义异常
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:27:52
 */
public class CYFException extends RuntimeException{
	private static final long serialVersionUID = -1328735343040010701L;

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
