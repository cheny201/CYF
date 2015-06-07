package com.cyf.security.dao;

import java.util.List;

import com.cyf.exception.DaoException;
import com.cyf.security.condition.UserQueryCondition;
import com.cyf.security.pojo.User;
import com.cyf.util.page.Pager;


public interface UserDao {

	User getUserByUserAccount(String account);

	List<User> getUserForPage(Pager<User> pager, UserQueryCondition condition)  throws DaoException;
	
	public List<User> queryByUserCode(String userCode, String comCode);
	
	
}
