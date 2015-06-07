package com.cyf.security.service;

import java.util.List;

import com.cyf.security.condition.RoleQueryCondition;
import com.cyf.security.pojo.Role;
import com.cyf.util.page.Pager;


public interface RoleService {

	public List<Role> findRoleByUserId(String userId);

	public Role getRole(String roleId);

	public List<Role> getRoleForPage(Pager<Role> pager,
			RoleQueryCondition condition);
	List<Role> findRoleByUserOrgid(String orgId);

	public void addRole(Role role);

	public void updateRole(Role role);

	public void delRole(String id);
}

