package com.cyf.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统信息
 * @author ChenY201
 * @version 1.0
 * @created 2014-11-12 下午8:19:53
 */
public class SystemInfo {
	private static Map<String, String> propertiesMap = new HashMap<String, String>();

	private static void init() {
		if(propertiesMap.isEmpty()){
			propertiesMap.put("", "");
		}
	}
	
	public static String getProperties(String key){
		SystemInfo.init();
		return propertiesMap.get(key);
	}

}
