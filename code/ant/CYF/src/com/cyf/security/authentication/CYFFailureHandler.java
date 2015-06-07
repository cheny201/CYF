package com.cyf.security.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * Copyright FuJian Trust Information Technology CO.,LTD
 * <p>
 * 认证失败处理
 * @since 2013-8-29 
 * @author MaoZhulan
 */
public class CYFFailureHandler implements AuthenticationFailureHandler {

	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		String responseMsg = null;
		/**
		 * ajax请求标识
		 * y：该请求为ajax请求，返回json格式数据
		 * n:该请求为普通请求，转向指定页面
		 */
		String ajax = request.getParameter("pwb_ajax_flag");
		if(!StringUtils.isBlank(ajax) && "y".equals(ajax)){
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("{msg:'账户认证失败!',success:false}");
		}else{
			if(exception != null){
				responseMsg = "账户不可用";
			}else if(exception instanceof UsernameNotFoundException){
				responseMsg = "用户名或密码错误";
			}else{
				responseMsg = "账户认证失败";
			}
			request.setAttribute("login_error", responseMsg);
			request.getRequestDispatcher("/tologin.jsp").forward(request, response);
			//response.sendRedirect(request.getServletContext().getAttribute("rootPath")+"/tologin.jsp");
		}
	}

}
