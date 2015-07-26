package com.cy.cyf.util.cache;

public abstract class CYFCacheDataProvider<T> {
	
	public abstract String getCacheName();
	
	public abstract T getData(String key);
	
}
