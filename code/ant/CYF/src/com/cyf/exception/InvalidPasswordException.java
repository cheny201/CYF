package com.cyf.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 用户验证时，密码错误抛出的异常
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:28:29
 */
public class InvalidPasswordException extends AuthenticationException {

	private static final long serialVersionUID = 1L;
	/**
	 * @param msg
	 * @param t
	 */
	public InvalidPasswordException(String msg, Throwable t) {
		super(msg, t);
	}

}
