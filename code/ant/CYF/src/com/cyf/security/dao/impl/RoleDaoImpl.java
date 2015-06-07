package com.cyf.security.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cyf.base.BaseDao;
import com.cyf.exception.DaoException;
import com.cyf.security.condition.RoleQueryCondition;
import com.cyf.security.dao.RoleDao;
import com.cyf.security.pojo.Role;
import com.cyf.security.pojo.User;
import com.cyf.util.page.Pager;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<Role> implements RoleDao {
	
	public Role getRole(String roleId) {
		try {
			return super.get(roleId);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	
	public List<Role> findRoleByUser(User user) {
		return null;
	}
	public List<String> findRoleIdByUser(User user) {
		return null;
	}
	@Override
	public List<Role> getRoleForPage(Pager<Role> pager,
			RoleQueryCondition condition) {
		return null;
	}

	public List<Role> findRoleByUserOrgid(String orgId) {
		String hql = "from Role where orgCode=? or orgCode =null";
		try {
			return this.find(hql, new Object[] {orgId});
		} catch (DataAccessException e) {
			throw new DaoException("菜单资源查询失败");
			
		}
	}
	
	/**
	 * 更新角色对象信息
	 * 
	 * @param role 角色对象
	 * @return 
	 * @throws DaoException  
	 * @since 2013-8-23
	 */
	public void updateRole(final Role role)  {
		try {
			this.update(role);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 插入一个角色对象
	 * 
	 * @param role 角色对象
	 * @return 
	 * @throws DaoException 访问异常
	 * @since 2013-8-23
	 */
	public void insertRole(final Role role) {
		try {
			this.save(role);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 删除角色对象
	 * 
	 * @param role 角色对象
	 * @return 
	 * @throws DaoException 访问异常
	 * @since 2013-8-23
	 */
	public void deleteRole(String roleId){
		String delSqlUs = "delete from t_ybt_user_role userRole where userRole.role_Code=? ";
		String delSqlRe = "delete from t_ybt_role_resource roleResource where roleResource.role_Code=? ";
		String delHqlRo = "delete from Role role where role.roleCode=?";
		try {
			super.executeSql(delSqlUs, new Object[] {roleId});
			super.executeSql(delSqlRe, new Object[] {roleId});
			super.execute(delHqlRo, new Object[] {roleId});
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 查询所有角色集合
	 * 
	 * @param
	 * @return List<Role> 角色集合
	 * @throws DaoException 访问异常
	 * @since 2013-8-23
	 */
	public List<Role> findAllRoles() {
		String hql = "from Role r where  r.enabled=?";
		try {
			return this.find(hql,new Object[]{"1"});
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 通过角色id集合删除角色
	 * @param  delIds 删除角色id集合
	 * @return 
	 * @throws DaoException 访问异常
	 * @since 2013-8-23
	 */
	public void deleteRolesByIds(final String[] delIds) {
		
	}
}
