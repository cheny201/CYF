package com.cyf.security.service;

import java.util.List;

import com.cyf.security.pojo.Resources;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;


public interface Application {
	/**
	 * 根据父菜单id查询该父资源下所有的子资源
	 * @param parentId
	 * @return
	 */
	public List<Resources> queryResources(String parentId);
	/**
     * 根据username查询User实体
     * 该方法返回的是一个持久化对象，
	 * 对其进行的操作在事务提交后会被保存到数据库中
     * @param username User对象的用户名
     * @return User持久化对象
     */
    public User queryUserByAccount(String account);
    
    /**
     * 查询用户拥有的角色列表
     * @param userId 用户id
     * @return 角色集合
     * @since 2013-8-29
     */
    public List<Role> queryUserRoles(String userId);

	
	/**
	 * 根据roleIdList,resourceType查询该角色的资源
	 * @param roleIdList 角色id集合
	 * @param resourceType 资源类型
	 * @return 资源集合
	 */
	public List<Resources> queryRoleResourcesViaIds(List<String> roleIdList, String resourceType);
	
	/**
	 * 根据roleList,resourceType查询该角色的资源
	 * @param roleList 角色对象集合
	 * @param resourceType 资源类型
	 * @return 资源集合
	 */
	public List<Resources> queryRoleResourcesViaRole(List<Role> roleList, String resourceType);
	
	/**
	 * 根据roleId,resourceType查询该角色的资源
	 * @param roleId
	 * @return
	 */
	public List<Resources> queryRoleResources(String roleId, String resourceType);
	
	/**
	 * 编辑菜单资源的位置
	 * @param menuResourceId
	 * @param oldParentId
	 * @param newParentId
	 * @param order
	 */
	public void editMenuPosition(String menuId, String oldParentId, String newParentId, Integer order);
	
    
	
}
