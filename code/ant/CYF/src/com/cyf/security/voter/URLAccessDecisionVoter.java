package com.cyf.security.voter;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.cyf.log.CYFLog;
import com.cyf.security.pojo.Resources;
import com.cyf.security.pojo.User;

@SuppressWarnings("rawtypes")
public class URLAccessDecisionVoter implements AbstractAccessDecisionVoter {

	public boolean supports(Class clazz) {
		return true;
	}

	public boolean supports(ConfigAttribute attribute) {
		return ROLE_VALID.equals(attribute.getAttribute())
				|| ROLE_NONE.equals(attribute.getAttribute())
				|| NOFILTER.equals(attribute.getAttribute());
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
				if (ca.getAttribute().equals(NOFILTER)) {
					return 1;
				}
			}
		}

		Object principal = authentication.getPrincipal();
		if (!(principal instanceof User)) {
			CYFLog.info("IP[" + request.getRemoteAddr() + "]未登陆，访问URL["
					+ requestUrl + "]");
			return -1;
		}
		
		
		if (attributes != null && attributes.size() > 0) {
			Object[] list = attributes.toArray();
			for (int i = 0; i < list.length; i++) {
				ConfigAttribute ca = (ConfigAttribute)list[i];
				if (ca.getAttribute().equals(ROLE_NONE)) {
					return 1;
				}
			}
		}
		
		// 用户已锁定
		// if(SecurityContext.isLock(request.getSession(false)) &&
		// !"user/nofilter_unLock.action".equals(requestUrl)){
		// return -1;
		// }
		User user = (User) principal;
		// 判断用户是否有此URL访问权限
		List<Resources> resources = user.getResources();
		if (resources != null) {
			for (Resources r : resources) {
				if (StringUtils.isBlank(r.getUrl()))
					continue;
				if (requestUrl.equals(r.getUrl().substring(1,
						r.getUrl().length()))) {
					return 1;
				}
				if (fullRequestUrl.equals(r.getUrl())) {
					return 1;
				}
				if (requestUrl.lastIndexOf("?") != -1) {
					if (requestUrl.substring(0, requestUrl.lastIndexOf("?"))
							.equals(r.getUrl())) {
						return 1;
					}
					if (requestUrl.substring(0, requestUrl.lastIndexOf("?"))
							.equals(r.getUrl()
									.substring(1, r.getUrl().length()))) {
						return 1;
					}
				}
				if (fullRequestUrl.lastIndexOf("?") != -1) {
					if (fullRequestUrl.substring(0,
							fullRequestUrl.lastIndexOf("?")).equals(r.getUrl())) {
						return 1;
					}
				}
			}
		}
		CYFLog.info("用户[" + user.getUsercode() + "]没有权限访问URL[" + requestUrl
				+ "]");
		// 禁止访问
		return -1;
	}
}
