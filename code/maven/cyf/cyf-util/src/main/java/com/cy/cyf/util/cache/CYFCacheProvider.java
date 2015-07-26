package com.cy.cyf.util.cache;

import com.cy.cyf.util.cache.CYFCacheDataProvider;
import com.cy.cyf.util.cache.CYFCacheManager;
import com.cy.cyf.util.cache.impl.EHCacheManager;

public class CYFCacheProvider {
	
	public static final String CACHE_TYPE_EHCACHE="ehcache";

	private static CYFCacheManager cacheManager;
	
	private CYFCacheProvider(){}
	
	public static void getInstance(String cacheType){
		if(CACHE_TYPE_EHCACHE.equals(cacheType)){
			cacheManager = new EHCacheManager();
		}
	}
	
	public static void start(){
		cacheManager.start();
	};
	
	public static void shutdown(){
		cacheManager.shutdown();
	};
	
	public static <T> T getData(CYFCacheDataProvider<T> cacheDataProvider,String key){
		return cacheManager.getData(cacheDataProvider, key);
	};
	
}
