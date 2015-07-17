package com.cy.cyf.framework.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cy.cyf.framework.handler.ExceptionHandlerService;
import com.cy.cyf.log.CYFLog;
import com.google.gson.GsonBuilder;

@Controller
public class BaseController {
	
	private static GsonBuilder gsonBuilder = null;
	
	{
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	@Autowired
	private ExceptionHandlerService exceptionHandlerService;
	
	@ExceptionHandler(Exception.class)
	public void exceptionHandler(Exception e, HttpServletResponse resp) {
		CYFLog.error("Controller异常..................", e);
		exceptionHandlerService.doHandler(e, resp);
		if (e instanceof org.springframework.web.multipart.MaxUploadSizeExceededException) {
		}
	}
	
	protected void write(HttpServletResponse resp,Object obj){
		try {
			String re = null;
			if(obj instanceof String){
				re = (String) obj;
			}else{
				re = gsonBuilder.create().toJson(obj);
			}
			resp.getWriter().write(re);
		} catch (IOException e) {
			CYFLog.error("返回信息失败",e);
		}
	}

}
