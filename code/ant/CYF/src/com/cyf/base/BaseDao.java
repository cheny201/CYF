package com.cyf.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 数据操作类，实现了常用数据库操作，并对HibernateTemplate,JdbcTemplate进行了封装
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:19:11
 */
@SuppressWarnings({"unchecked","deprecation","rawtypes"})
public class BaseDao<T> {
	
	private Class<T> entityClass;
	public BaseDao(){
		Type t = this.getClass().getGenericSuperclass();
		ParameterizedType pt = null;
		if(t instanceof ParameterizedType){
			pt=ParameterizedType.class.cast(t);
			Type[]  ts = pt.getActualTypeArguments();
			entityClass = (Class)ts[0];
		}
	}
	
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 保存对象
	 * @param entity 要保存的对象
	 * @return 生成的对象ID
	 * @since 2013-6-7
	 */
	public Serializable save(T entity){
		return this.hibernateTemplate.save(entity);
	}
	
	/**
	 * 保存对象
	 * @param entity 要保存的对象
	 * @return 生成的对象ID
	 * @since 2013-6-7 
	 */
	public Serializable save(String entityName,T entity){
		return this.hibernateTemplate.save(entityName,entity);
	}
	
	/**
	 * 批量保存或更新对象
	 * @param entities 要保存的对象集
	 * @since 2013-6-7 
	 */
	public void saveAll(Collection<?> entities){
		this.hibernateTemplate.saveOrUpdateAll(entities);
	}
	
	/**
	 * 保存或更新对象
	 * @param entity 要保存或更新的对象
	 * @since 2013-6-7 
	 */
	public void saveOrUpdate(T entity){
		this.hibernateTemplate.saveOrUpdate(entity);
	}
	
	/**
	 * 保存或更新对象
	 * @param entity 要保存或更新的对象
	 * @since 2013-6-7 
	 */
	public void saveOrUpdate(String entityName,T entity){
		this.hibernateTemplate.saveOrUpdate(entityName,entity);
	}
	
	/**
	 * 删除对象
	 * @param entity 要删除的对象
	 * @since 2013-6-7 
	 */
	public void delete(T entity){
		this.hibernateTemplate.delete(entity);
	}
	
	/**
	 * 删除对象
	 * @param entityName 要删除的对象的名称
	 * @param entity 要删除的对象
	 * @since 2013-6-7 
	 */
	public void delete(String entityName,T entity){
		this.hibernateTemplate.delete(entityName,entity);
	}
	
	
	
	/**
	 * 删除指定的所有对象
	 * @param entities 要删除的数据集
	 * @since 2013-6-7 
	 */
	public void deleteAll(Collection<T> entities){
		this.hibernateTemplate.deleteAll(entities);
	}
	
	/**
	 * 更新对象
	 * @param entity 要更新的对象
	 * @since 2013-6-7 
	 */
	public void update(T entity){
		this.hibernateTemplate.update(entity);
	}
	
	/**
	 * 更新对象
	 * @param entityName 要更新的对象的名称
	 * @param entity 要更新的对象
	 * @since 2013-6-7 
	 */
	public void update(String entityName,T entity){
		this.hibernateTemplate.update(entityName,entity);
	}
	
	/**
	 * 执行hql语句
	 * @param hql 待执行的hql语句
	 * @return 删除或更新的记录数
	 * @since 2013-6-7 
	 */
	public int execute(final String hql){
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 执行hql语句
	 * @param hql 待执行的hql语句
	 * @param values hql中参数的值集合
	 * @return 删除或更新的记录数
	 * @since 2013-6-7 
	 */
	public int execute(final String hql,final Object[] values){
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(hql);
				if(values!=null && values.length>0){
					for(int x=0;x<values.length;x++){
						query.setParameter(x, values[x]);
					}
				}
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 执行sql语句
	 * @param sql 待执行的sql语句
	 * @return 删除或更新的记录数
	 * @since 2013-6-7 
	 */
	public int executeSql(final String sql){
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 执行sql语句
	 * @param sql 待执行的sql语句
	 * @param values sql语句中参数的值集合
	 * @return 删除或更新的记录数
	 * @since 2013-6-7 
	 */
	public int executeSql(final String sql,final Object[] values){
		return this.hibernateTemplate.execute(new HibernateCallback<Integer>() {
			
			public Integer doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery query = session.createSQLQuery(sql);
				if(values!=null && values.length>0){
					for(int x=0;x<values.length;x++){
						query.setParameter(x, values[x]);
					}
				}
				return query.executeUpdate();
			}
		});
	}
	
	/**
	 * 根据Class查询
	 * @param entityClass Class对象
	 * @param id 对象标识
	 * @return 查询到的对象
	 * @since 2013-6-7 
	 */
	public T get(Class<T> entityClass,Serializable id){
		return (T)this.hibernateTemplate.get(entityClass, id);
	}
	
	/**
	 * 根据Class查询（使用类型参数）
	 * @param id 对象标识
	 * @return 查询到的对象
	 * @since 2013-6-7 
	 */
	public T get(Serializable id){
		return (T)this.hibernateTemplate.get(entityClass, id);
	}
	
	/**
	 * 查询映射的所有记录
	 * @param entityClass Class对象
	 * @return 该Class所表示的类映射的所有记录集合
	 * @since 2013-6-7 
	 */
	public List<T> loadAll(Class<T> entityClass){
		return this.hibernateTemplate.loadAll(entityClass);
	}
	
	/**
	 * 查询映射的所有记录（使用类型参数）
	 * @return 该Class所表示的类映射的所有记录集合
	 * @since 2013-6-7 
	 */
	public List<T> loadAll(){
		return this.hibernateTemplate.loadAll(entityClass);
	}
	
	/**
	 * 根据标识加载数据
	 * @param entityClass Class对象
	 * @param id 对象标识
	 * @return 查询到的对象
	 * @since 2013-6-7 
	 */
	public <TT> TT load(Class<TT> entityClass,Serializable id){
		return (TT)this.hibernateTemplate.load(entityClass, id);
	}
	
	/**
	 * 根据标识加载数据（使用类型参数：BaseDao<T>）
	 * @param id 对象标识
	 * @return 查询到的对象
	 * @since 2013-6-7 
	 */
	public T load(Serializable id){
		return (T)this.hibernateTemplate.load(entityClass, id);
	}
	
	/**
	 * 根据hql语句查询数据
	 * @param queryString 查询hql语句
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> find(String queryString){
		return this.hibernateTemplate.find(queryString);
	}
	
	/**
	 * 根据hql语句查询对象
	 * @param queryString 查询hql语句
	 * @return 对象
	 * @since 2013-6-7 
	 */
	public T findObject(String queryString){
		List<T> rs = this.hibernateTemplate.find(queryString);
		return (rs==null || rs.size() == 0)?null:rs.get(0);
	}
	
	/**
	 * 根据hql语句查询数据
	 * @param queryString 查询hql语句
	 * @param value hql绑定变量的值
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> find(String queryString,Object value){
		return this.hibernateTemplate.find(queryString,value);
	}
	
	/**
	 * 根据hql语句查询对象
	 * @param queryString 查询hql语句
	 * @param value hql绑定变量的值
	 * @return 对象
	 * @since 2013-6-7 
	 */
	public T findObject(String queryString,Object value){
		List<T> rs = this.hibernateTemplate.find(queryString,value);
		return (rs == null || rs.size()==0)?null:rs.get(0);
	}
	
	/**
	 * 根据hql语句查询数据
	 * @param queryString 查询hql语句
	 * @param values hql绑定变量的值
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> find(String queryString,Object[] values){
		return this.hibernateTemplate.find(queryString,values);
	}
	
	/**
	 * 根据hql语句查询对象
	 * @param queryString 查询hql语句
	 * @param values hql绑定变量的值
	 * @return 对象
	 * @since 2013-6-7 
	 */
	public T findObject(String queryString,Object[] values){
		List<T> rs = this.hibernateTemplate.find(queryString,values);
		return (rs==null || rs.size() == 0)?null:rs.get(0);
	}
	/**
	 * 根据hql语句查询对象
	 * @param queryString 查询hql语句
	 * @param values hql绑定变量的值
	 * @return 对象
	 * @since 2013-6-7 
	 */
	public Object findColumn(String queryString,Object[] values){
		List<Object> rs = this.hibernateTemplate.find(queryString,values);
		return (rs==null || rs.size() == 0)?null:rs.get(0);
	}
	
	/**
	 * 根据hql语句查询数据
	 * @param queryString 查询hql语句
	 * @param firstResult 分页查询的起始行号（从0开始）
	 * @param maxResults 每页查询的记录数
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByHqlForPage(final String queryString,final int firstResult,final int maxResults){
		return (List<T>)this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(queryString);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				return query.list();
			}
		});
	}
	
	/**
	 * 根据hql语句查询数据
	 * @param queryString 查询hql语句
	 * @param firstResult 分页查询的起始行号（从0开始）
	 * @param maxResults 每页查询的记录数
	 * @param values hql语句中参数值集合
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByHqlForPage(final String queryString,
								final int firstResult,final int maxResults,
								final Object[] values){
		return (List<T>)this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query = session.createQuery(queryString);
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResults);
				if(values!=null && values.length>0){
					for(int x =0;x<values.length;x++){
						query.setParameter(x,values[x]);
					}
				}
				return query.list();
			}
		});
	}
	
	/**
	 * 根据sql语句查询数据
	 * @param sqlString 查询sql语句
	 * @param firstResult 分页查询的起始行号（从0开始）
	 * @param maxResults 每页查询的记录数
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<?> findBySqlForPage(final String sqlString,
								final int firstResult,final int maxResults){
		return (List<?>)this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sq = session.createSQLQuery(sqlString);
				sq.setFirstResult(firstResult);
				sq.setMaxResults(maxResults);
				return sq.list();
			}
		});
	}
	
	/**
	 * 根据sql语句查询数据
	 * @param sqlString 查询sql语句
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<?> findBySql(final String sqlString){
		return (List<?>)this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sq = session.createSQLQuery(sqlString);
				return sq.list();
			}
		});
	}
	
	/**
	 * 根据sql语句查询数据
	 * @param sqlString 查询sql语句
	 * @param firstResult 分页查询的起始行号（从0开始）
	 * @param maxResults 每页查询的记录数
	 * @param values sql语句中参数值集合
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findBySqlForPage(final String sqlString,
								final int firstResult,final int maxResults,
								final Object[] values){
		return (List<T>)this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sq = session.createSQLQuery(sqlString);
				sq.setFirstResult(firstResult);
				sq.setMaxResults(maxResults);
				if(values!=null && values.length>0){
					for(int x=0;x<values.length;x++){
						sq.setParameter(x, values[x]);
					}
				}
				return sq.list();
			}
		});
	}
	
	/**
	 * 根据sql语句查询数据
	 * @param sqlString 查询sql语句
	 * @param values sql语句中参数值集合
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<?> findBySqlForPage(final String sqlString,
								final Object[] values){
		return (List<T>)this.hibernateTemplate.executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				SQLQuery sq = session.createSQLQuery(sqlString);
				if(values!=null && values.length>0){
					for(int x=0;x<values.length;x++){
						sq.setParameter(x, values[x]);
					}
				}
				return sq.list();
			}
		});
	}
	
	/**
	 * 根据命名参数hql查询
	 * @param queryString 含有命名参数的hql
	 * @param paramNames hql中参数的名称集合
	 * @param values 各参数的值
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedParam(String queryString,String[] paramNames,Object[] values){
		return this.hibernateTemplate.findByNamedParam(queryString, paramNames, values);
	}
	
	/**
	 * 根据命名参数hql查询
	 * @param queryString 含有命名参数的hql
	 * @param paramName hql中参数的名称
	 * @param value 参数的值
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedParam(String queryString,String paramName,Object value){
		return this.hibernateTemplate.findByNamedParam(queryString, paramName, value);
	}
	
	/**
	 * 执行hbm文件中命名的语句
	 * @param queryName 语句名称
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedQuery(String queryName){
		return this.hibernateTemplate.findByNamedQuery(queryName);
	}
	
	/**
	 * 执行hbm文件中命名的语句
	 * @param queryName 语句名称
	 * @param value 参数值
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedQuery(String queryName,Object value){
		return this.hibernateTemplate.findByNamedQuery(queryName,value);
	}
	
	/**
	 * 执行hbm文件中命名的语句
	 * @param queryName 语句名称
	 * @param values 参数值集合
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedQuery(String queryName,Object[] values){
		return this.hibernateTemplate.findByNamedQuery(queryName,values);
	}
	
	/**
	 * 执行hbm文件中命名的使用命名参数的语句
	 * @param queryName 语句名称
	 * @param paramName 参数名称
	 * @param value 参数值
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedQueryAndNamedParam(String queryName,String paramName,Object value){
		return this.hibernateTemplate.findByNamedQueryAndNamedParam(queryName, paramName, value);
	}
	
	/**
	 * 执行hbm文件中命名的使用命名参数的语句
	 * @param queryName 语句名称
	 * @param paramNames 参数名称集合
	 * @param values 参数值集合
	 * @return 对象集合
	 * @since 2013-6-7 
	 */
	public List<T> findByNamedQueryAndNamedParam(String queryName,String[] paramNames,Object[] values){
		return this.hibernateTemplate.findByNamedQueryAndNamedParam(queryName, paramNames, values);
	}
	
	/**
	 * 执行sql返回int型数据
	 * @param sql 待执行的sql语句
	 * @return int型结果
	 * @since 2013-6-7 
	 */
	public int queryForInt(String sql){
		return this.jdbcTemplate.queryForInt(sql);
	}
	
	/**
	 * 执行sql返回int型数据
	 * @param sql 待执行的sql语句
	 * @param paramValues sql参数值集合
	 * @param paramTypes sql中参数类型（java.sql.Types）
	 * @return int型结果
	 * @since 2013-6-7 
	 */
	public int queryForInt(String sql,Object[] paramValues,int[] paramTypes){
		return this.jdbcTemplate.queryForInt(sql,paramValues,paramTypes);
	}
	
	/**
	 * 执行sql返回long型数据
	 * @param sql 待执行的sql语句
	 * @param paramValues sql中参数的值集合
	 * @param paramTypes sql中参数类型集合（java.sql.Types）
	 * @return long型结果
	 * @since 2013-6-7 
	 */
	public long queryForLong(String sql,Object[] paramValues,int[] paramTypes){
		return this.jdbcTemplate.queryForLong(sql,paramValues,paramTypes);
	}
	
	/**
	 * 执行sql返回long型数据
	 * @param sql 待执行的sql语句
	 * @return long型结果
	 * @since 2013-6-7 
	 */
	public long queryForLong(String sql){
		return this.jdbcTemplate.queryForLong(sql);
	}
	
	/**
	 * 执行返回单个列的sql，并根据指定类型返回数据对象
	 * @param sql 待执行sql语句
	 * @param values sql绑定变量的参数值
	 * @param requireClass 待返回的数据类型
	 * @return 转换成指定类型后的数据对象
	 * @since 2013-6-17
	 */
	public <E> E querySingleBySqlRequireClass(String sql,Object[] values,Class<E> requireClass){
		return this.jdbcTemplate.queryForObject(sql, values, requireClass);
	}
	
	/**
	 * 执行返回单个列的sql，并根据指定类型返回数据对象
	 * @param sql 待执行sql语句
	 * @param values sql绑定变量的参数值
	 * @param argTypes 绑定变量的参数类型（{@link java.sql.Types}）
	 * @param requireClass 待返回的数据类型
	 * @return 转换成指定类型后的数据对象
	 * @since 2013-6-17
	 */
	public <E> E querySingleBySqlRequireClass(String sql,Object[] values,int[] argTypes,Class<E> requireClass){
		return this.jdbcTemplate.queryForObject(sql, values, argTypes,requireClass);
	}
	
	/**
	 * 执行sql，返回查询结果数据
	 * @param sql 待执行的sql语句
	 * @values 绑定变量参数值
	 * @return 单行结果集，key:列名,value:列值
	 * @since 2013-10-28
	 */
	public Map<String, Object> queryForMap(String sql,Object[] values){
		return this.jdbcTemplate.queryForMap(sql,values);
	}
	
	/**
	 * 执行sql，返回查询结果数据
	 * @param sql 待执行的sql语句
	 * @values 绑定变量参数值
	 * @return 行结果集，key:列名,value:列值
	 * @since 2013-10-28
	 */
	public List<Map<String, Object>> queryForMaps(String sql,Object[] values){
		return this.jdbcTemplate.queryForList(sql, values);
	}
	
	/**
	 * 批量执行sql语句
	 * @param sqls 待执行的sql语句集
	 * @return 每条语句在数据库中受影响的行数
	 * @since 2013-6-7 
	 */
	public int[] batchUpdate(String[] sqls){
		return this.jdbcTemplate.batchUpdate(sqls);
	}
	
	/*public HibernateTemplate getHibernateTemplate() {
		return this.hibernateTemplate;
	}
	public JdbcTemplate getJdbcTemplate() {
		return this.jdbcTemplate;
	}*/
	
	public JdbcTemplate getJdbcTemplate(){
		return this.jdbcTemplate;
	}
}
