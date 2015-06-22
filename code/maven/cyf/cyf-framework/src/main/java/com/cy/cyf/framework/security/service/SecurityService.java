package com.cy.cyf.framework.security.service;

import com.cy.cyf.framework.security.pojo.User;

public interface SecurityService {
	
	public User queryByUserName(String userName);
	
	public boolean validatePassword(String password,User user);

}
