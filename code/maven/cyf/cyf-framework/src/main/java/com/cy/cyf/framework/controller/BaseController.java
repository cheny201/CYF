package com.cy.cyf.framework.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cy.cyf.framework.handler.ExceptionHandlerService;
import com.cy.cyf.log.CYFLog;

@Controller
public class BaseController {
	
	@Autowired
	private ExceptionHandlerService exceptionHandlerService;
	
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception e, HttpServletResponse resp) {
		CYFLog.error("Controller异常..................", e);
		exceptionHandlerService.doHandler(e, resp);
		if (e instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
		}
	}

}
