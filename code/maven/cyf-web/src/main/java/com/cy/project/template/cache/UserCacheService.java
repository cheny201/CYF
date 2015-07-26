package com.cy.project.template.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.cyf.util.cache.CYFCacheDataProvider;
import com.cy.project.template.user.dao.UserDao;
import com.cy.project.template.user.pojo.User;

@Service
public class UserCacheService extends CYFCacheDataProvider<User>{

	@Autowired
	private UserDao userDao;
	
	@Override
	public String getCacheName() {
		return "UserCacheService";
	}

	@Override
	public User getData(String key) {
		return userDao.queryByUserCode(key);
	}

}
