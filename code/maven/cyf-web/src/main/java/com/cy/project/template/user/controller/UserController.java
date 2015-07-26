package com.cy.project.template.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.cyf.framework.controller.BaseController;
import com.cy.project.template.cache.UserCacheService;

@Controller
@RequestMapping(value="/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserCacheService userCacheService;
	
	public void getUser(HttpServletRequest req,HttpServletResponse resp){
	}

}
