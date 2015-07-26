package com.cy.cyf.util.cache.impl;

import com.cy.cyf.util.cache.CYFCacheDataProvider;
import com.cy.cyf.util.cache.CYFCacheManager;
import com.cy.cyf.util.cache.EHCacheUtil;

public class EHCacheManager extends CYFCacheManager{
	
	private EHCacheUtil ecacheUtil;
	
	@Override
	public void start() {
		ecacheUtil = EHCacheUtil.getInstance(EHCacheManager.class.getResourceAsStream("/com/cy/config/ehcache.xml"));
	}

	@Override
	public void shutdown() {
		ecacheUtil.shutdown();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getData(CYFCacheDataProvider<T> cache, String key) {
		Object obj = ecacheUtil.getData(cache.getCacheName(),key,Object.class);
		if(obj == null){
			obj = cache.getData(key);
			if(obj != null){
				ecacheUtil.addData(cache.getCacheName(), key, obj);
			}
		}
		return (T) obj;
	}

}
