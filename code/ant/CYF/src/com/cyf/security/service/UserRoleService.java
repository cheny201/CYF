package com.cyf.security.service;

import java.util.List;

import com.cyf.exception.ServiceException;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;
public interface UserRoleService {

	List<Role> findRoleByUserOrgid(String orgId);
	/**
	 * 保存用户角色
	 * 
	 * @param user
	 *            操作的用户对象
	 * @param idList
	 *            赋予的角色集合编号
	 * @throws ServiceException
	 * @since 2013-7-30
	 */
	void saveGrantRole(User user, List<String> idList) throws ServiceException;

	
}
