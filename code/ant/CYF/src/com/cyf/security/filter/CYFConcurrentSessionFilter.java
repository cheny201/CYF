package com.cyf.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

public class CYFConcurrentSessionFilter extends GenericFilterBean {


    private SessionRegistry sessionRegistry;
    private String expiredUrl;
    private LogoutHandler[] handlers = new LogoutHandler[] {new SecurityContextLogoutHandler()};
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(sessionRegistry, "SessionRegistry required");
        Assert.isTrue(expiredUrl == null || UrlUtils.isValidRedirectUrl(expiredUrl),
                expiredUrl + " isn't a valid redirect URL");
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        HttpSession session = request.getSession(false);

        if (session != null) {
            SessionInformation info = sessionRegistry.getSessionInformation(session.getId());

            if (info != null) {
            	//session已失效
                if (info.isExpired()) {
                    doLogout(request, response);

                    String targetUrl = determineExpiredUrl(request, info);

                    if (targetUrl != null) {
                        redirectStrategy.sendRedirect(request, response, targetUrl);

                        return;
                    } else {
                    	String ajax = request.getParameter("pwb_ajax_flag");
                		if(!StringUtils.isBlank(ajax) && "y".equals(ajax)){
                			response.setCharacterEncoding("UTF-8");
                            response.getWriter().print("{msg:'会话已失效,请重新登陆!',success:false}");
                            response.flushBuffer();
                		}else{
                			String uri = request.getRequestURI();
                			uri = uri.substring(uri.lastIndexOf("/")+1);
                			//注销
                			if("logout".equals(uri)){
//                				response.sendRedirect(ConfigInfo.ROOT_PATH+"/tologin.jsp");
                				//response.sendRedirect("http://online/intranet/FirstPage/Login.asp");
                			}
                			//dorado操作
                			/*else if("dorado/view-service".equals(uri)){
                				chain.doFilter(request, response);
                				return;
                			}*/
                			else{
	                			request.setAttribute("msg", "会话已失效，请重新登陆!");
	                			request.getRequestDispatcher("/view/common/403.jsp").forward(request, response);
                			}
                		}
                    }
                    return;
                } else {
                    info.refreshLastRequest();
                }
            }
        }
        chain.doFilter(request, response);
    }

    protected String determineExpiredUrl(HttpServletRequest request, SessionInformation info) {
        return expiredUrl;
    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        for (int i = 0; i < handlers.length; i++) {
            handlers[i].logout(request, response, auth);
        }
    }

    public void setExpiredUrl(String expiredUrl) {
        this.expiredUrl = expiredUrl;
    }

    public void setSessionRegistry(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public void setLogoutHandlers(LogoutHandler[] handlers) {
        Assert.notNull(handlers);
        this.handlers = handlers;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

}
