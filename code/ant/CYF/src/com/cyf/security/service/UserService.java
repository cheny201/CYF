package com.cyf.security.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cyf.exception.ServiceException;
import com.cyf.security.condition.UserQueryCondition;
import com.cyf.security.pojo.User;
import com.cyf.util.page.Pager;

public interface UserService extends UserDetailsService{

	public User getUserByUserAccount(String account);
	/**
	 * 根据条件分页查询资源数据
	 * @param pager 分页信息对象
	 * @param condition 查询条件对象
	 * @return 符合条件的当前页资源对象集合
	 * @author dingkaiqin
	 * @since 2014-3-25
	 */
	List<User> getUserForPage(Pager<User> pager,UserQueryCondition condition) throws ServiceException;
	
	/**
	 * 根据员工账号，归属机构进行模糊查询
	 * @param userCode
	 * @param comCode
	 * @return
	 */
	List<User> queryByUserCode(String userCode,String comCode);
	
	
	void aa() throws Exception;
	
}
