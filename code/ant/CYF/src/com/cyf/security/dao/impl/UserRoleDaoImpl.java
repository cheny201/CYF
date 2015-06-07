package com.cyf.security.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cyf.base.BaseDao;
import com.cyf.exception.DaoException;
import com.cyf.security.dao.UserRoleDao;
import com.cyf.security.pojo.User;
import com.cyf.security.pojo.UserRole;

/**
 * 
 * @author dingkaiqin
 * @since 2014-3-25
 * 
 */
@Repository("userRoleDao")
public class UserRoleDaoImpl extends BaseDao<UserRole> implements UserRoleDao {
	
	/**
	 * 保存授予用户的角色
	 * 
	 * @param 
	 * @param    
	 * @return 
	 * @throws DaoException 访问异常
	 * @since 2013-8-23
	 */
	public void saveGrantRole(final User user, final List<String> idList)
			throws DaoException {
		String delSql = "delete from T_YBT_USER_ROLE where USER_CODE=? ";
		String sql = "insert into T_YBT_USER_ROLE (USER_CODE,ROLE_CODE)values(?,?)";
		try {
			super.executeSql(delSql, new Object[] {user.getUsercode()});
			for (int i = 0; i < idList.size(); i++) {
				super.executeSql(sql,
						new Object[] {user.getUsercode(), idList.get(i)});
			}
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
}
