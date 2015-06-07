package com.cyf.security.dao;

import java.util.List;

import com.cyf.exception.DaoException;

public interface RoleResourcesDao {
	void saveGrantResource(final String roleId, final List<String> idList)
			throws DaoException;

}
