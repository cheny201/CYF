package com.cyf.security.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cyf.exception.ServiceException;
import com.cyf.security.dao.RoleResourcesDao;
import com.cyf.security.service.RoleResourcesService;


/**
 * 
 * Copyright FuJian Trust Information Technology CO.,LTD
 * 
 * @since 2013-7-26
 * @author Zhao zhentao
 */
@Service("roleResourcesService")
@Transactional
public class RoleResourcesServiceImpl implements RoleResourcesService {
	@Autowired
	private RoleResourcesDao roleResourcesDao;
	@Override
	public void saveGrantResources(String roleId, List<String> idList)
			throws ServiceException {
		try {
			roleResourcesDao.saveGrantResource(roleId, idList);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
