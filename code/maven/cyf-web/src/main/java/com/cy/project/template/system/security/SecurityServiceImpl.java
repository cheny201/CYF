package com.cy.project.template.system.security;

import org.springframework.stereotype.Service;

import com.cy.cyf.framework.security.pojo.BaseUser;
import com.cy.cyf.framework.security.service.SecurityService;
import com.cy.project.template.user.pojo.User;

@Service
public class SecurityServiceImpl implements SecurityService{

	@Override
	public User queryByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validatePassword(String password, BaseUser user) {
		// TODO Auto-generated method stub
		return false;
	}

}
