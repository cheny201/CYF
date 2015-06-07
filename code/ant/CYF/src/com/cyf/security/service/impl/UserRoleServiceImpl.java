package com.cyf.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyf.exception.ServiceException;
import com.cyf.security.dao.UserRoleDao;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;
import com.cyf.security.service.UserRoleService;

@Service("userRoleService")
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public List<Role> findRoleByUserOrgid(String orgId) {
		// TODO Auto-generated method stub
	return null;
	}

	@Override
	public void saveGrantRole(User user, List<String> idList)
			throws ServiceException {
			userRoleDao.saveGrantRole(user, idList);
		
	}
	
	
}
