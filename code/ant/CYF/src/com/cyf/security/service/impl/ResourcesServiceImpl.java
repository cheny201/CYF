package com.cyf.security.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyf.exception.DaoException;
import com.cyf.exception.ServiceException;
import com.cyf.security.condition.ResourceQueryCondition;
import com.cyf.security.dao.ResourcesDao;
import com.cyf.security.pojo.Resources;
import com.cyf.security.pojo.Role;
import com.cyf.security.service.ResourcesService;
import com.cyf.util.page.Pager;


@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService {

	@Autowired
	private ResourcesDao resourcesDao;
	/**
	 * 根据父资源代码查找子资源集合
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<Resources> getChildResources(String parentId) {
		try {
			return resourcesDao.getChildResources(parentId);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 根据权限代码集合查找资源集合
	 * @param roleList
	 * @param flag 是否按照资源类型查找
	 * @return
	 */
	public List<Resources> findResByRoleList(List<Role> roleList, boolean flag) {
		try {
			return this.resourcesDao.findResByRoleList(roleList, flag);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	/**
	 * 新增资源对象
	 * @param resources
	 * @throws ServiceException
	 */
	public void createResources(Resources resources) {
		try {
			this.resourcesDao.insertResources(resources);
		} catch (DaoException e) {
			throw new ServiceException(e);

		}
	}
	/**
	 * 根据用户账户查找该用户下所拥有的资源集合
	 * @param account 用户账户
	 * @param type 资源类型
	 * @param isRoot 是否为顶层资源
	 * @return
	 */
	public List<Resources> findResourcesByUserIdForUser(String empnoId,
			String type, boolean isRoot) {
		try {
			return this.resourcesDao.findResourcesByUserIdForUser(empnoId, type,
					isRoot);
		} catch (DaoException e) {
			throw new ServiceException(e);
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
	 * 
	 */
	@Override
	public List<Resources> getResourceForPage(Pager<Resources> pager,
			ResourceQueryCondition condition) {
		return resourcesDao.getResourceForPage(pager, condition);
	}
	/**
	 * 根据条件查询资源数据
	 * 
	 * @param resourceId
	 *            查询条件
	 * @return 符合条件的资源对象
	 * 
	 */
	@Override
	public Resources get(String resourceCode) throws DaoException{
		return resourcesDao.get(resourceCode);
	}

	/**
	 * 获得资源对象集合
	 * 
	 * @param 
	 *            资源对象
	 * @throws ServiceException
	 */
	@Override
	public List<Resources> getResourceList(ResourceQueryCondition condition) throws DaoException{
		return resourcesDao.getResourceList(condition);
	}
	/**
	 * 通过id 删除资源
	 * 
	 * @param delIds
	 *            删除资源编号集合
	 * @throws ServiceException
	 * 
	 */
	public void deleteResourcesByIds(final String[] delIds)
			throws ServiceException {
		try {
			this.resourcesDao.deleteResourcesByIds(delIds);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 通过id 删除资源
	 * 
	 * @param delIds
	 *            删除资源编号集合
	 * @throws ServiceException
	 * 
	 */
	public void deleteResources(final Resources resources)
			throws DaoException {
		try {
			this.resourcesDao.deleteResources(resources);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 更新资源明细
	 * 
	 * @param resources
	 *            资源对象
	 * @throws ServiceException
	 */
	public void updateResources(final Resources resources)
			throws DaoException {
		try {
			this.resourcesDao.updateResources(resources);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
	/**
	 * 根据权限代码查找该权限下所拥有的资源集合
	 * @param roleCode
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Resources> findResourcesByRoleId(String roleCode)
			throws DaoException {
		try {
			return this.resourcesDao.findResourcesByRoleId(roleCode);
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
