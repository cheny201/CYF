package com.cyf.security.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.cyf.base.BaseDao;
import com.cyf.exception.DaoException;
import com.cyf.security.condition.UserQueryCondition;
import com.cyf.security.dao.UserDao;
import com.cyf.security.pojo.User;
import com.cyf.util.page.Pager;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User> implements UserDao {

	public User getUserByUserAccount(String account) {
		try {
			return this.findObject("from User where usercode=?", account);
		} catch (DataAccessException e) {
			throw new DaoException(e);
		}
	}

	/** 根据机构代码查找此机构和下属子机构的用户
	 * @param orgCode
	 * @return
	 * @since 2013-10-15 
	 */
	 List<User> getUserByOrgCode(String orgCode){
		 return null;
	 }

	@Override
	public List<User> queryByUserCode(String usercode, String comCode) {
		String hql = "from User where comcode like '"+comCode+"%' and usercode like '"+usercode+"%'";
		return super.find(hql);
	}

	@Override
	public List<User> getUserForPage(Pager<User> pager,
			UserQueryCondition condition) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveUser(User u) {
		super.save(u);
	}

	@Override
	public void delUser(String usercode) {
		String hql = "delete User where usercode=?";
		super.execute(hql,new Object[]{usercode});
	}
}
