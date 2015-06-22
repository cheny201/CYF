package com.cy.cyf.framework.security.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 认证成功处理
 * @author ChenY201
 *
 */
public interface CYFSuccessHandler extends AuthenticationSuccessHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public abstract void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication);

}
