package com.cyf.exception;

/**
 * DAO层异常
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:28:13
 */
public class DaoException extends CYFException {

	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message, Throwable throwable) {
		super(message, throwable);
		// TODO Auto-generated constructor stub
	}

	public DaoException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DaoException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}
	
}
