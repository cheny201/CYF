package com.cyf.exception;

/**
 * Service层异常
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:29:09
 */
public class ServiceException extends CYFException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable throwable) {
		super(message, throwable);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

}
