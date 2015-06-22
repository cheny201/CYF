package com.cy.cyf.framework.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao4MyBatis<T> {

	// 插入:insert*
	/**
	 * 插入对象
	 * 
	 * @param record
	 * @return
	 */
	int insert(T record);

	/**
	 * 选择性添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(T record);

	// 删除:delete*

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 根据唯一键删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByUniqueMap(Map<String, Object> params);

	// 修改:update*

	/**
	 * 根据主键选择更新
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(T record);

	/**
	 * 根据主键更新
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(T record);

	// 查询(单个或多个对象):select*

	/**
	 * 查询结果集或分页
	 * 
	 * @param params查询参数
	 * @return 实体集合
	 */
	public List<T> selectByMap(Map<String, Object> params);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	T selectByPrimaryKey(String id);

	/**
	 * 根据唯一键查询
	 * 
	 * @param id
	 * @return
	 */
	T selectByUniqueMap(Map<String, Object> params);

	/**
	 * 查询多张表或分页
	 * 
	 * @param params查询参数
	 * @return 实体集合
	 */
	public List<T> selectJoinTablesByMap(Map<String, Object> params);

	/**
	 * 查询序列值
	 * 
	 * @param id
	 * @return
	 */
	public String selectNextVal();

	// 获取总数:get*

	/**
	 * 获取总条数
	 * 
	 * @param params
	 * @return
	 */
	public int getCountByMap(Map<String, Object> params);
	
}
