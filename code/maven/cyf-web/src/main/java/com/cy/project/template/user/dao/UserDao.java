package com.cy.project.template.user.dao;

import com.cy.project.template.user.pojo.User;

public interface UserDao{
	
	public User queryByUserCode(String userCode);

}
