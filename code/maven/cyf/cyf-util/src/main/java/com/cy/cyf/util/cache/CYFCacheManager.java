package com.cy.cyf.util.cache;

public abstract class CYFCacheManager {
	
	public abstract void start();
	
	public abstract void shutdown();
	
	public abstract <T> T getData(CYFCacheDataProvider<T> cache,String key);
	
}
