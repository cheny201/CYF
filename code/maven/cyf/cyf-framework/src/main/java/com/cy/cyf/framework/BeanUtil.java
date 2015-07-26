package com.cy.cyf.framework;

import org.springframework.context.ApplicationContext;

public class BeanUtil {
	
	private static ApplicationContext appliactionContext;

	public static void setAppliactionContext(ApplicationContext appliactionContext) {
		BeanUtil.appliactionContext = appliactionContext;
	}

	public static Object getBean(String name) {
		return appliactionContext.getBean(name);
	}

}
