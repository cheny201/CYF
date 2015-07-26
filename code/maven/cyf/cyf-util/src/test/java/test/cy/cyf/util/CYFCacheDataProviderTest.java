package test.cy.cyf.util;

import com.cy.cyf.util.cache.CYFCacheDataProvider;
import com.cy.cyf.util.cache.CYFCacheProvider;

public class CYFCacheDataProviderTest extends CYFCacheDataProvider<String>{

	@Override
	public String getCacheName() {
		return "CYFCacheDataProviderTest-Cache";
	}

	@Override
	public String getData(String key) {
		return getCacheName()+"-----"+key;
	}
	
	public static void main(String[] args) {
		CYFCacheProvider.getInstance(CYFCacheProvider.CACHE_TYPE_EHCACHE);
		CYFCacheProvider.start();
		CYFCacheDataProvider<String> a = new CYFCacheDataProviderTest();
		String aa = CYFCacheProvider.getData(a, "001");
		System.out.println(aa);
		CYFCacheProvider.shutdown();
	}

}
