package com.cy.project.security;

import com.cy.cyf.framework.security.pojo.User;
import com.cy.cyf.framework.security.service.SecurityService;

public class SecurityServiceImpl implements SecurityService{

	@Override
	public User queryByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validatePassword(String password, User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
