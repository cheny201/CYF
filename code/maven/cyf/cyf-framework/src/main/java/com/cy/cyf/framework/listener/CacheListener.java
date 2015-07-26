package com.cy.cyf.framework.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.cy.cyf.log.CYFLog;
import com.cy.cyf.util.cache.CYFCacheProvider;

public class CacheListener implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		CYFLog.info("销毁缓存服务.....................");
		CYFCacheProvider.shutdown();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		CYFLog.info("创建缓存服务.....................开始");
		CYFCacheProvider.getInstance(CYFCacheProvider.CACHE_TYPE_EHCACHE);
		CYFCacheProvider.start();
		CYFLog.info("创建缓存服务.....................完成");
	}
	
}
