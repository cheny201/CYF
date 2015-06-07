package com.cyf.security.dao;

import java.util.List;

import com.cyf.exception.DaoException;
import com.cyf.security.pojo.User;


public interface UserRoleDao {


	void saveGrantRole(final User user, final List<String> idList)
			throws DaoException;
	
}
