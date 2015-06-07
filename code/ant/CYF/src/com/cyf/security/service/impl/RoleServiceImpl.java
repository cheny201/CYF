package com.cyf.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyf.exception.DaoException;
import com.cyf.exception.ServiceException;
import com.cyf.security.condition.RoleQueryCondition;
import com.cyf.security.dao.RoleDao;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;
import com.cyf.security.service.RoleService;
import com.cyf.util.page.Pager;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	public List<Role> findRoleByUserId(String userId) {
		User user = new User();
		user.setUsercode(userId);
		try {
			return this.roleDao.findRoleByUser(user);
		} catch (DaoException e) {
			throw new ServiceException(e);

		}
	}

	public Role getRole(String roleId) {

		Role role = null;
		try {
			role = (Role) this.roleDao.getRole(roleId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		return role;
	}

	@Override
	public List<Role> getRoleForPage(Pager<Role> pager,
			RoleQueryCondition condition) {
		try {
			return roleDao.getRoleForPage(pager, condition);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<Role> findRoleByUserOrgid(String orgId) {
		try {
			return this.roleDao.findRoleByUserOrgid(orgId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void addRole(Role role) {
		try {
			this.roleDao.insertRole(role);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public void updateRole(Role role) {
		try {
			this.roleDao.updateRole(role);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delRole(String id) {
		try {
			this.roleDao.deleteRole(id);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
		
	}

}
