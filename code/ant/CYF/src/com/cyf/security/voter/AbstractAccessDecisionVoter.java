package com.cyf.security.voter;

import org.springframework.security.access.AccessDecisionVoter;

/**
 * Copyright FuJian Trust Information Technology CO.,LTD
 * <p>
 * 
 * @since 2013-8-30 
 * @author MaoZhulan
 */
@SuppressWarnings("rawtypes")
public interface AbstractAccessDecisionVoter extends AccessDecisionVoter{

	public final static String ROLE_VALID = "ROLE_VALID";//登陆并且拥有权限才能访问
	public final static String ROLE_NONE = "ROLE_NONE";//登录才能访问
	public final static String NOFILTER = "NOFILTER";//不验证
	
}
