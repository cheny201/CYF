package com.cy.cyf.framework.security.service;

import com.cy.cyf.framework.security.pojo.BaseUser;

public interface SecurityService {
	
	public BaseUser queryByUserName(String userName);
	
	public boolean validatePassword(String password,BaseUser user);

}
