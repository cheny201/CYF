package com.cyf.exception;

/**
 * 邮件异常
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-22 下午12:58:12
 */
public class CYFMailException extends CYFException{
	private static final long serialVersionUID = -5843385988203547983L;

	public CYFMailException(){
		super();
	}
	
	public CYFMailException(String message){
		super(message);
	}
	
	public CYFMailException(String message,Throwable throwable){
		super(message,throwable);
	}
	
	public CYFMailException(Throwable throwable){
		super(throwable);
	}
	
	@Override
	public String getMessage() {
		return "CYFMailException------"+super.getMessage();
	}

}
