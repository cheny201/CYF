package com.cy.project.template.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.cy.cyf.framework.dao.BaseDao4Hibernate;
import com.cy.project.template.user.pojo.User;
import com.cy.project.template.user.dao.UserDao;

@Repository("userDao")
public class UserDaoImpl extends BaseDao4Hibernate<User> implements UserDao {

	@Override
	public User queryByUserCode(String userCode) {
		String hql = "from User where userCode=?";
		return super.findObject(hql, userCode);
	}

}
