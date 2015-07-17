package com.cy.cyf.framework.security.voter;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

/**
 * 接口验证器
 * 
 * @author ChenY201
 * @date 2015年7月13日
 */
@SuppressWarnings("rawtypes")
public abstract class InterfaceAccessDecisionVoter implements AbstractAccessDecisionVoter {

	public boolean supports(Class clazz) {
		return true;
	}
	
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	public int vote(Authentication authentication, Object object,
			Collection attributes) {
		FilterInvocation invocation = null;
		String requestUrl = null;
		if(object instanceof FilterInvocation){
			invocation = (FilterInvocation)object;
			requestUrl = invocation.getRequestUrl();
		}
		if(check(requestUrl)){
			return 1;
		}
		return -1;
	}
	
	public abstract boolean check(String requestUrl);
}
