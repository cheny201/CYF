package com.cyf.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cyf.exception.DaoException;
import com.cyf.exception.ServiceException;
import com.cyf.security.condition.UserQueryCondition;
import com.cyf.security.dao.UserDao;
import com.cyf.security.pojo.User;
import com.cyf.security.service.UserService;
import com.cyf.util.page.Pager;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	public User getUserByUserAccount(String account) {
		
		try {
			return this.userDao.getUserByUserAccount(account);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getUserForPage(Pager<User> pager,
			UserQueryCondition condition) {
		return userDao.getUserForPage(pager, condition);
	}

	@Override
	public List<User> queryByUserCode(String userCode, String comCode) {
		return userDao.queryByUserCode(userCode, comCode);
	}

	@Override
	public UserDetails loadUserByUsername(String usercode)
			throws UsernameNotFoundException {
		return (UserDetails) userDao.getUserByUserAccount(usercode);
	}

	
}
