package com.cy.cyf.framework.handler;

import javax.servlet.http.HttpServletResponse;

public interface ExceptionHandlerService {
	
	public void doHandler(Exception e, HttpServletResponse resp);

}
