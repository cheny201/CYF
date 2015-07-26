package com.cy.cyf.util.cache;

import java.io.InputStream;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EHCacheUtil {
	
	private static CacheManager cacheManager;
	private static EHCacheUtil cacheUtil;
	
	private EHCacheUtil(InputStream ips){
		cacheManager = CacheManager.create(ips);
	}
	
	public static EHCacheUtil getInstance(InputStream ips){
		if(cacheUtil == null){
			cacheUtil = new EHCacheUtil(ips);
		}
		return cacheUtil;
	}

	public void shutdown() {
		cacheManager.shutdown();
	}
 
	public Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if(cache == null){
			cache = new Cache(cacheName, 10000, true, false, 120L, 120L, false, 120L);
			cacheManager.addCache(cache);
		}
		return cache;
	}
	
	public void addData(String cacheName,String key,Object value){
		getCache(cacheName).put(new Element(key, value));
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getData(String cacheName,String key,Class<T> clazz){
		Cache cache = getCache(cacheName);
		if(cache.isKeyInCache(key)){
			return (T) cache.get(key).getObjectValue();
		}else{
			return null;
		}
	}
	
}
