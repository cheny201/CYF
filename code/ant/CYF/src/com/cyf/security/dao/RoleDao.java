package com.cyf.security.dao;

import java.util.List;

import com.cyf.exception.DaoException;
import com.cyf.security.condition.RoleQueryCondition;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;
import com.cyf.util.page.Pager;

public interface RoleDao {

	List<Role> findRoleByUser(User e)throws DaoException;

	Role getRole(String roleId)throws DaoException;

	List<Role> getRoleForPage(Pager<Role> pager, RoleQueryCondition condition)throws DaoException;
	List<Role> findRoleByUserOrgid(String orgId)throws DaoException;

	void insertRole(Role role)throws DaoException;

	void updateRole(Role role)throws DaoException;
	void deleteRole(String roleId) throws DaoException;
}
