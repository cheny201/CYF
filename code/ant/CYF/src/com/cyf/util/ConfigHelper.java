package com.cyf.util;

import java.io.InputStream;

import com.cyf.config.SysContants;

public class ConfigHelper {

	public static InputStream getResourceAsStream(String resource) throws Exception{
		String stripped = resource.startsWith("/") ?
				resource.substring(1) : resource;

		InputStream stream = null;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		if (classLoader!=null) {
			stream = classLoader.getResourceAsStream( stripped );
		}
		if ( stream == null ) {
			stream = SysContants.class.getResourceAsStream( resource );
		}
		if ( stream == null ) {
			stream = SysContants.class.getClassLoader().getResourceAsStream( stripped );
		}
		if ( stream == null ) {
			throw new Exception( resource + " not found" );
		}
		return stream;
	}
}
