package com.cy.cyf.framework.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * 认证失败处理
 * @author ChenY201
 *
 */
public interface CYFFailureHandler extends AuthenticationFailureHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	public abstract void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception);

}
