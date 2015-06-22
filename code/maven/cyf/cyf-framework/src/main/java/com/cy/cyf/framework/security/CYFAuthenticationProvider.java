package com.cy.cyf.framework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.cy.cyf.framework.security.pojo.User;
import com.cy.cyf.framework.security.service.SecurityService;
import com.cy.cyf.log.CYFLog;
import com.cy.cyf.core.Constant;

public class CYFAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private SecurityService securityService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws com.cy.cyf.core.exception.SecurityException {
		String userName = authentication.getPrincipal()==null?null:authentication.getPrincipal().toString();
		if (userName == null) {
			throw new com.cy.cyf.core.exception.SecurityException("帐号不能为空");
		}
		UsernamePasswordAuthenticationToken token = null;
		
		User user = null;
		user = securityService.queryByUserName(userName);
		if(user == null){
			CYFLog.debug(Constant.ERROR_CODE.NO_USER.getLabel()+"["+userName+"]");
			throw new com.cy.cyf.core.exception.SecurityException().setCode(Constant.ERROR_CODE.NO_USER.getCode());
		}
		//校验密码
		if(authentication.getCredentials()==null || !securityService.validatePassword(authentication.getCredentials().toString(), user)){
			CYFLog.debug(Constant.ERROR_CODE.BAD_PASSWORD.getLabel()+"["+userName+","+authentication.getCredentials()+"]");
			throw new com.cy.cyf.core.exception.SecurityException().setCode(Constant.ERROR_CODE.BAD_PASSWORD.getCode());
		}
		
		token = new UsernamePasswordAuthenticationToken(user,
                authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
		return token;
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
	}
}
