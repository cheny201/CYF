package com.cyf.security.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cyf.base.BaseDao;
import com.cyf.exception.DaoException;
import com.cyf.security.dao.RoleResourcesDao;
import com.cyf.security.pojo.RoleResource;

@Repository("roleResourcesDao")
public class RoleResourcesDaoImpl extends BaseDao<RoleResource> implements RoleResourcesDao {
	
	/**
	 * 保存授予角色的资源
	 * 
	 * @param role 角色对象
	 * @param idList  资源id集合List<Long>    
	 * @return 
	 * @throws DaoException 访问异常
	 * @since 2013-8-23
	 */
	public void saveGrantResource(final String roleId, final List<String> idList)
			throws DaoException {
		String delSql = "delete from T_YBT_ROLE_RESOURCE where ROLE_CODE=? ";
		String sql = "insert into T_YBT_ROLE_RESOURCE(ROLE_CODE,RESOURCE_CODE)values(?,?)";
		try {
			super.executeSql(delSql, new Object[] {roleId});
			for (int i = 0; i < idList.size(); i++) {
				super.executeSql(sql,
						new Object[] {roleId, idList.get(i)});
			}
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

}
