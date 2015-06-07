package com.cyf.security.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cyf.exception.ServiceException;
import com.cyf.security.pojo.Resources;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;
import com.cyf.security.service.Application;
import com.cyf.security.service.ResourcesService;
import com.cyf.security.service.RoleService;
import com.cyf.security.service.UserService;

public class ApplicationImpl implements Application {

	private RoleService roleService;
	private UserService userService;
	private ResourcesService resourcesService;
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

	public User queryUserByAccount(String account) {
		User user=null;
		try {
			user = userService.getUserByUserAccount(account);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<Role> queryUserRoles(String userId) {
		try {
			return roleService.findRoleByUserId(userId);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Resources> queryResources(String parentId) {
		// 载入根菜单时，默认parentId为0
		if (parentId == null) {
			parentId = "0";
		}
		List<Resources> resourcesList=null;
		try {
			resourcesList = resourcesService
					.getChildResources(parentId);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return resourcesList;
	}

	/**
	 * 查询roleList中所有角色所包含的resourceType类型的资源(不包含permission)
	 */
	public List<Resources> queryRoleResourcesViaIds(List<String> roleIdList,
			String resourceType) {
		if(roleIdList == null || roleIdList.size() <= 0)
			return null;
		List<Resources> resourceList = new ArrayList<Resources>();
		for (int i = 0; i < roleIdList.size(); i++) {
			String roleId = roleIdList.get(i);
			List<Resources> roleResourceList = (List<Resources>) this.queryRoleResources(roleId, resourceType);
			resourceList.addAll(roleResourceList);
		}
		return resourceList;
	}

	/**
	 * 查询roleId的角色所包含的resourceType类型的资源(不包含permission)
	 */
	public List<Resources> queryRoleResources(String roleId, String resourceType) {
		Role role;
		try {
			role = roleService.getRole(roleId);
			List<Resources> roleResourceList = role.getResourcesList(resourceType);
			return roleResourceList;
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public List<Resources> queryRoleResourcesViaRole(List<Role> roleList,
			String resourceType) {
		if(roleList == null || roleList.size() <= 0)
			return null;
		try {
			return resourcesService.findResByRoleList(roleList,false);
		} catch (ServiceException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 查询当前用户的菜单权限
	 * 
	 * @return
	 */
	public Map<String, Resources> queryUserMenuResource() {
		return null;
	}

	@SuppressWarnings("unused")
	public void editMenuPosition(String menuId, String oldParentId,
			String newParentId, Integer order) {
		Resources resources;
		try {
			resources = resourcesService.get(menuId);
			Resources newParent = resourcesService.get(newParentId);
			Resources oldParent = resourcesService.get(oldParentId);
			List<Resources> oldChildList = resourcesService
					.getChildResources(oldParentId);
			List<Resources> newChildList = resourcesService
					.getChildResources(newParentId);
			// 更新order
			Integer oldChildOrder = 0;
			if (!oldParentId.equals(newParentId)) {
				for (int i = 0; i < oldChildList.size(); i++) {
					Resources m = oldChildList.get(i);
					if (m.getResourceCode().equals(resources.getResourceCode())) {
						continue;
					}
					m.setSortOrder(oldChildOrder);
					oldChildOrder++;
				}
			}
			Integer newChildOrder = 0;
			for (int i = 0; i < newChildList.size(); i++) {
				Resources m = newChildList.get(i);
				if (order.equals(newChildOrder)) {
					newChildOrder++;
				}
				if (m.getResourceCode().equals(resources.getResourceCode())) {
					continue;
				}
				m.setSortOrder(newChildOrder);
				newChildOrder++;
			}
//			newParent.setHasChild(Boolean.TRUE);
			resources.setSortOrder(order);
			resources.setParent(newParent);
			resourcesService.createResources(resources);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
