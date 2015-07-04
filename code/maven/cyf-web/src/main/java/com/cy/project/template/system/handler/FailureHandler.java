package com.cy.project.template.system.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.cy.cyf.framework.security.handler.CYFFailureHandler;

@Service("cyfFailureHandler")
public class FailureHandler implements CYFFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception) {
		// TODO Auto-generated method stub

	}

}
