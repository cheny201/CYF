package com.cy.cyf.framework.security.voter;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.cy.cyf.core.Constant;
import com.cy.cyf.framework.security.pojo.BaseUser;
import com.cy.cyf.log.CYFLog;

@SuppressWarnings("rawtypes")
public abstract class URLAccessDecisionVoter implements AbstractAccessDecisionVoter {

	public boolean supports(Class clazz) {
		return true;
	}

	public boolean supports(ConfigAttribute attribute) {
		return Constant.SECURITY_ROLE.NONE.getCode().equals(attribute.getAttribute())
				|| Constant.SECURITY_ROLE.LOGIN.getCode().equals(attribute.getAttribute())
				|| Constant.SECURITY_ROLE.LOGIN_VALID.getCode().equals(attribute.getAttribute());
	}

	public int vote(Authentication authentication, Object object,
			Collection attributes) {

		FilterInvocation invocation = null;
		String requestUrl = null;
		String fullRequestUrl = null;
		HttpServletRequest request = null;
		if (object instanceof FilterInvocation) {
			invocation = (FilterInvocation) object;
			requestUrl = invocation.getRequestUrl();
			fullRequestUrl = invocation.getFullRequestUrl();

			requestUrl = requestUrl.substring(1, requestUrl.length());
			fullRequestUrl = fullRequestUrl.substring(0,
					fullRequestUrl.length());

			request = invocation.getHttpRequest();
		} else {
			return -1;
		}

		// 不拦截ROLE_NOFILTER匹配的URL
		if (attributes != null && attributes.size() > 0) {
			Object[] list = attributes.toArray();
			for (int i = 0; i < list.length; i++) {
				ConfigAttribute ca = (ConfigAttribute)list[i];
				if (ca.getAttribute().equals(Constant.SECURITY_ROLE.NONE.getCode())) {
					return 1;
				}
			}
		}

		Object principal = authentication.getPrincipal();
		if (!(principal instanceof BaseUser)) {
			CYFLog.debug("IP[" + request.getRemoteAddr() + "]未登陆，访问URL["
					+ requestUrl + "]");
			return -1;
		}
		
		
		if (attributes != null && attributes.size() > 0) {
			Object[] list = attributes.toArray();
			for (int i = 0; i < list.length; i++) {
				ConfigAttribute ca = (ConfigAttribute)list[i];
				if (ca.getAttribute().equals(Constant.SECURITY_ROLE.LOGIN.getCode())) {
					return 1;
				}
			}
		}
		
		// 判断用户是否有此URL访问权限
		BaseUser user = (BaseUser) principal;
		if(hasResource(requestUrl,user)){
			return 1;
		}
		CYFLog.info("用户[" + user.getUsername() + "]没有权限访问URL[" + requestUrl + "]");
		// 禁止访问
		return -1;
	}
	
	public abstract boolean hasResource(String requestUrl,BaseUser user);
}
