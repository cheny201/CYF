package test.cy.cyf.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.cy.cyf.framework.dao.BaseDao4Hibernate;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.util.cache.CYFCacheDataProvider;
import com.cy.cyf.util.cache.CYFCacheProvider;
import com.cy.project.template.cache.UserCacheService;
import com.cy.project.template.user.dao.UserDao;
import com.cy.project.template.user.pojo.User;

public class UserTest extends TestBase{
	
	@Autowired
	private UserCacheService userCacheService;
	@Autowired
	private UserDao userDao;
	
	@Test
	public void testCache(){
		CYFCacheProvider.getInstance(CYFCacheProvider.CACHE_TYPE_EHCACHE);
		CYFCacheProvider.start();
		List<User> ls = new ArrayList<User>();
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			ls.add(CYFCacheProvider.getData(userCacheService, "11"));
		}
		long end = System.currentTimeMillis();
		CYFLog.error("1========"+ls.size()+"==========="+(end-start));
		CYFCacheProvider.shutdown();
		
		
		ls = new ArrayList<User>();
		start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			ls.add(userDao.queryByUserCode("11"));
		}
		end = System.currentTimeMillis();
		CYFLog.error("2========"+ls.size()+"==========="+(end-start));
	}
	


}
