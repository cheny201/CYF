package com.cy.cyf.framework.security.handler;

import org.springframework.security.web.access.AccessDeniedHandler;

public interface CYFAccessDeniedHandler extends AccessDeniedHandler {

	/**
	 * 没有权限时执行
	 */
//	public abstract void handle(HttpServletRequest request, HttpServletResponse response,
//			AccessDeniedException arg2);
	
}
