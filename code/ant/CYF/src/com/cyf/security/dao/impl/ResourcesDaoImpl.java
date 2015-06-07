package com.cyf.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cyf.base.BaseDao;
import com.cyf.exception.DaoException;
import com.cyf.exception.ServiceException;
import com.cyf.security.condition.ResourceQueryCondition;
import com.cyf.security.dao.ResourcesDao;
import com.cyf.security.pojo.Resources;
import com.cyf.security.pojo.Role;
import com.cyf.util.Tools;
import com.cyf.util.page.Pager;

@Repository("resourcesDao")
public class ResourcesDaoImpl extends BaseDao<Resources> implements
		ResourcesDao {
	
	/**
	 * 根据父资源代码查找子资源集合
	 * @param parentId
	 * @return 资源集合
	 * @throws DaoException
	 */
	public List<Resources> getChildResources(String parentId) {
		try {
			return this
					.find("from Resources where parentId=? and enabled=? ",
							new Object[]{parentId, "1"});
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 根据权限代码集合查找资源集合
	 * @param roleList
	 * @param flag 是否按照资源类型查找
	 * @return 资源对象集合
	 */
	public List<Resources> findResByRoleList(final List<Role> roles,final boolean flag) {
		return null;
	}
	
	/**
	 * 根据用户账户查找该用户下所拥有的资源集合
	 * @param account 用户账户
	 * @param type 资源类型
	 * @param isRoot 是否为顶层资源
	 * @return 资源对象集合
	 */
	public List<Resources> findResourcesByUserIdForUser(String empnoId,
			String type, boolean isRoot) {
		return null;
	}
	public Resources get(String resourceCode) {
		try {
			return this.get(Resources.class, resourceCode);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 新增资源对象
	 * @param resources
	 *  @throws DaoException
	 */
	public void insertResources(Resources resources) {
		try {
			this.save(resources);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}
	/**
	 * 根据条件分页查询资源数据
	 * 
	 * @param pager
	 *            分页信息对象
	 * @param condition
	 *            查询条件对象
	 * @return 符合条件的当前页资源对象集合
	 *  @throws DaoException
	 */
	@Override
	public List<Resources> getResourceForPage(Pager<Resources> pager,
			ResourceQueryCondition condition) throws DaoException {
		return null;
	}
	/**
	 * 获得资源对象集合
	 * 
	 * @param 
	 * @return 符合条件的资源对象          
	 */
	@Override
	public List<Resources> getResourceList(ResourceQueryCondition condition) {
		StringBuffer hql = new StringBuffer("from Resources where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if(!Tools.isBlank(condition.getResourceType())){
			hql.append(" and resourceType = ?  ");
			params.add(condition.getResourceType());
		}
		if(!Tools.isBlank(condition.getParentId())){
			hql.append(" and parentId = ? ");
			params.add(condition.getParentId());
		}
		hql.append(" order by sortOrder ");
		try {
			return this.find(hql.toString(),params.toArray());
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	/**
	 * 删除资源明细信息
	 * 
	 * @param resources
	 *            资源对象
	 * @throws ServiceException
	 */
	public void deleteResources(final Resources resources){}
	/**
	 * 通过id 删除资源
	 * 
	 * @param delIds
	 *            删除资源编号集合
	 * @throws DaoException
	 * 
	 */
	public void deleteResourcesByIds(final String[] delIds) throws DaoException {
	}
	/**
	 * 更新资源明细信息
	 * 
	 * @param resources
	 *            资源对象
	 * @throws DaoException
	 */
	public void updateResources(final Resources resources){
		try {
			this.update(resources);
		} catch (DataAccessException e) {
			
			throw new DaoException(e);
		}

	}
	/**
	 * 根据权限代码查找该权限下所拥有的资源集合
	 * @param roleCode
	 * @return 资源对象集合
	 * @throws DaoException
	 */
	public List<Resources> findResourcesByRoleId(final String roleCode) {
		return null;
	}
}
