package com.cy.project.template.system.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.cy.cyf.framework.security.handler.CYFSuccessHandler;

@Service("cyfSuccessHandler")
public class SuccessHandler implements CYFSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub

	}

}
