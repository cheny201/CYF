package com.cyf.security.service;

import java.util.List;

import com.cyf.exception.ServiceException;
import com.cyf.security.condition.ResourceQueryCondition;
import com.cyf.security.pojo.Resources;
import com.cyf.security.pojo.Role;
import com.cyf.util.page.Pager;

public interface ResourcesService {
	/**
	 * 根据父资源代码查找子资源集合
	 * @param parentId
	 * @return
	 * @throws ServiceException
	 */
	public List<Resources> getChildResources(String parentId)
			throws ServiceException;
	/**
	 * 根据权限代码集合查找资源集合
	 * @param roleList
	 * @param flag 是否按照资源类型查找
	 * @return
	 */
	public List<Resources> findResByRoleList(List<Role> roleList, boolean flag);
	/**
	 * 新增资源对象
	 * @param resources
	 * @throws ServiceException
	 */
	public void createResources(Resources resources) throws ServiceException;
	/**
	 * 根据用户账户查找该用户下所拥有的资源集合
	 * @param account 用户账户
	 * @param type 资源类型
	 * @param isRoot 是否为顶层资源
	 * @return
	 */
	public List<Resources> findResourcesByUserIdForUser(String account,
			String type, boolean isRoot);

	/**
	 * 根据条件分页查询资源数据
	 * 
	 * @param pager
	 *            分页信息对象
	 * @param condition
	 *            查询条件对象
	 * @return 符合条件的当前页资源对象集合
	 * @throws ServiceException
	 */
	List<Resources> getResourceForPage(Pager<Resources> pager,
			ResourceQueryCondition condition) throws ServiceException;

	/**
	 * 根据条件查询资源数据
	 * 
	 * @param resourceId
	 *            查询条件
	 * @return 符合条件的资源对象
	 * @throws ServiceException
	 */
	public Resources get(String resourceCode) throws ServiceException;

	/**
	 * 获得资源对象集合
	 * 
	 * @param 
	 *            资源对象
	 * @throws ServiceException
	 */
	List<Resources> getResourceList(ResourceQueryCondition condition) throws ServiceException;

	/**
	 * 通过id 删除资源
	 * 
	 * @param delIds
	 *            删除资源编号集合
	 * @throws ServiceException
	 * 
	 */
	void deleteResourcesByIds(String[] delIds) throws ServiceException;
	/**
	 * 删除资源明细信息
	 * 
	 * @param resources
	 *            资源对象
	 * @throws ServiceException
	 */
	void deleteResources(final Resources resources) throws ServiceException;

	/**
	 * 更新资源明细信息
	 * 
	 * @param resources
	 *            资源对象
	 * @throws ServiceException
	 */
	void updateResources(Resources resources) throws ServiceException;
	/**
	 * 根据权限代码查找该权限下所拥有的资源集合
	 * @param roleCode
	 * @return
	 * @throws ServiceException
	 */
	List<Resources> findResourcesByRoleId(String roleCode)
			throws ServiceException;

}
