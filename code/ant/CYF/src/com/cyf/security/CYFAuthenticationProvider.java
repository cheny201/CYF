package com.cyf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cyf.exception.InvalidPasswordException;
import com.cyf.exception.ServiceException;
import com.cyf.log.CYFLog;
import com.cyf.security.pojo.User;
import com.cyf.security.service.UserService;
import com.cyf.util.Encrypt;

public class CYFAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		String userName = authentication.getPrincipal()==null?null:authentication.getPrincipal().toString();
		if (userName == null) {
			throw new UsernameNotFoundException("帐号不能为空!");
		}
		UsernamePasswordAuthenticationToken token = null;
		
		User user = null;
		user = userService.getUserByUserAccount(userName);
		if(user == null){
			CYFLog.debug("帐号["+userName+"]不存在");
			throw new UsernameNotFoundException("帐号不存在");
		}
		//校验密码
		String encrypt;
		try {
			encrypt = Encrypt.getDigest(authentication.getCredentials().toString());
		} catch (Exception e) {
			throw new ServiceException("密码加密失败");
		}
		
		if(authentication.getCredentials()==null || !user.getPassword().equals(encrypt)){
			throw new InvalidPasswordException("用户\"" + userName + "\"密码校验失败!",null);
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
