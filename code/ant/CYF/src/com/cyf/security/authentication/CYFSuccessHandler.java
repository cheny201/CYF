package com.cyf.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.cyf.exception.ServiceException;
import com.cyf.log.CYFLog;
import com.cyf.security.context.SecurityContext;
import com.cyf.security.pojo.User;
import com.cyf.security.service.Application;
import com.cyf.security.service.ResourcesService;


/**
 * Copyright FuJian Trust Information Technology CO.,LTD
 * <p>
 * 认证成功处理
 * @since 2013-8-29 
 * @author MaoZhulan
 */
public class CYFSuccessHandler implements AuthenticationSuccessHandler {

	private ResourcesService resourcesService;
	private Application application;
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		/**
		 * ajax请求标识
		 * y：该请求为ajax请求，返回json格式数据
		 * n:该请求为普通请求，转向指定页面
		 */
		String ajax = request.getParameter("cyf_ajax_flag");
		if(!StringUtils.isBlank(ajax) && "y".equals(ajax)){}else{
			HttpSession session = request.getSession(false);
			User user = SecurityContext.getUser();
			//获取用户数据
			if(user == null){
				response.sendRedirect(session.getServletContext().getAttribute("rootPath")+"/tologin.jsp");
			}
			session.setAttribute("SECURITY_LAST_USERNAME", user.getUsername());
			try {
				user.setRoleList(application.queryUserRoles(user.getUsercode()));
				user.setResources(this.resourcesService.findResourcesByUserIdForUser(user.getUsercode(), null, false));
			} catch (ServiceException e) {
				CYFLog.error("获取用户资源异常",e);
			}
			response.sendRedirect(session.getServletContext().getAttribute("rootPath")+"/welcome.jsp");
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	public void setResourcesService(ResourcesService resourcesService) {
		this.resourcesService = resourcesService;
	}

}
