package com.cyf.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CYFAccessDeniedHandler implements AccessDeniedHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException arg2) throws IOException, ServletException {
//		HttpSession session = request.getSession(false);
		/**
		 * ajax请求标识
		 * y：该请求为ajax请求，返回json格式数据
		 * n:该请求为普通请求，转向指定页面
		 */
		String ajax = request.getParameter("pwb_ajax_flag");
		if(!StringUtils.isBlank(ajax) && "y".equals(ajax)){
			response.setCharacterEncoding("UTF-8");
//			if(SecurityContext.isLock(session)){
//				response.getWriter().write("{msg:'帐号已锁定，请先解锁！',success:false}");
//			}else{
//				response.getWriter().write("{msg:'没有权限访问资源！',success:false}");
//			}
			response.flushBuffer();
		}else{
//			if(SecurityContext.isLock(session)){
//				request.setAttribute("msg", "帐号已锁定，请先解锁!");
//			}
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}
	
}
