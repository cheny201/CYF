package com.cyf.security.service;

import java.util.List;

import com.cyf.exception.ServiceException;


public interface RoleResourcesService {

	/**
	 * 保存授予角色的资源
	 * @param role 赋权角色对象
	 * @param idList 赋予的资源编号集合
	 * @throws ServiceException 
	 * @since 2013-7-30
	 */
	 void saveGrantResources(String roleId, List<String> idList) throws ServiceException;

}
