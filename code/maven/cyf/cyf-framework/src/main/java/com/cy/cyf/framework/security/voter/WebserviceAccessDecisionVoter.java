package com.cy.cyf.framework.security.voter;

import java.util.Collection;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.cy.cyf.util.ValidateUtil;

@SuppressWarnings("rawtypes")
public class WebserviceAccessDecisionVoter implements AbstractAccessDecisionVoter {

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
		if(!ValidateUtil.isEmpty(requestUrl) && requestUrl.length()>10 && "webservice".equals(requestUrl.substring(1, 11))){
			return 1;
		}
		return -1;
	}
}
