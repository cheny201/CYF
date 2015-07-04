package com.cy.project.template.system.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.cy.cyf.framework.security.handler.CYFAccessDeniedHandler;

@Service("cyfAccessDeniedHandler")
public class AccessDeniedHandler implements CYFAccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response, AccessDeniedException arg2) {
		// TODO Auto-generated method stub

	}

}
