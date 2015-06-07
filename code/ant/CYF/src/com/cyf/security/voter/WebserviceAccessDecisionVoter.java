package com.cyf.security.voter;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

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
		if(StringUtils.isNotBlank(requestUrl) && requestUrl.length()>10 && "webservice".equals(requestUrl.substring(1, 11))){
			return 1;
		}
		return -1;
	}
}
