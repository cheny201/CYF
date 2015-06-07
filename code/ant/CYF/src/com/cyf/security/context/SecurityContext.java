package com.cyf.security.context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cyf.security.pojo.User;
public class SecurityContext {
	
	public static User getUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication == null ? null : authentication
				.getPrincipal();
		if ((principal == null) || (!(principal instanceof User))) {
			return null;
		}
		return (User) principal;
	}

	public static List<Long> getUserRolesId() {
		List<GrantedAuthority> authorities = getUser().getAuthorities();
		if (authorities != null) {
			List<Long> roleIds = new ArrayList<Long>();
			for (GrantedAuthority authority : authorities) {
				roleIds.add(Long.valueOf(Long.parseLong(authority.getAuthority())));
			}
			return roleIds;
		}
		return Collections.emptyList();
	}

	public static boolean isAnonymousUser() {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		Object principal = authentication == null ? null : authentication
				.getPrincipal();
		return (principal == null) || (!(principal instanceof User));
	}

//	public static Object getUserSessionProperty(String key) {
//		Authentication authentication = SecurityContextHolder.getContext()
//				.getAuthentication();
//		Object principal = authentication == null ? null : authentication
//				.getPrincipal();
//		if ((principal == null) || (!(principal instanceof User))) {
//			throw new AccessDeniedException("用户没有登录!");
//		}
//		User u = (User) principal;
//		if (u.getUserProperties() != null) {
//			return u.getUserProperties().get(key);
//		}
//		return null;
//	}

//	public static void putUserSessionProperty(String key, Object value) {
//		if ((key == null) || ("".equals(key))) {
//			return;
//		}
//		Authentication authentication = SecurityContextHolder.getContext()
//				.getAuthentication();
//		Object principal = authentication == null ? null : authentication
//				.getPrincipal();
//		if ((principal == null) || (!(principal instanceof User))) {
//			throw new AccessDeniedException("用户没有登录!");
//		}
//		User u = (User) principal;
//		Map<String, Object> newMap = new HashMap<String, Object>();
//		Map<String, Object> oldMap = u.getUserProperties();
//		if (oldMap != null) {
//			oldMap.put(key, value);
//			u.setUserProperties(oldMap);
//		} else {
//			newMap.put(key, value);
//			u.setUserProperties(newMap);
//		}
//	}

//	public static void removeUserSessionProperty(String key) {
//		if ((key == null) || ("".equals(key))) {
//			return;
//		}
//		Authentication authentication = SecurityContextHolder.getContext()
//				.getAuthentication();
//		Object principal = authentication == null ? null : authentication
//				.getPrincipal();
//		if ((principal == null) || (!(principal instanceof User))) {
//			throw new AccessDeniedException("用户没有登录!");
//		}
//		User u = (User) principal;
//		Map<String, Object> map = u.getUserProperties();
//		if ((map != null) && (map.get(key) != null)) {
//			map.remove(key);
//			u.setUserProperties(map);
//		}
//	}

//	public static void removeAllUserSessionProperties() {
//		Authentication authentication = SecurityContextHolder.getContext()
//				.getAuthentication();
//		Object principal = authentication == null ? null : authentication
//				.getPrincipal();
//		if ((principal == null) || (!(principal instanceof User))) {
//			throw new AccessDeniedException("用户没有登录!");
//		}
//		User u = (User) principal;
//		u.setUserProperties(null);
//	}

	/**
	 * 判断是否已锁定
	 * @param
	 * @return
	 * @since 2013-9-26
	 */
//	public static boolean isLock(HttpSession session){
//		if(session == null)
//			return false;
//		Object obj = session.getAttribute(SessionKey.PWB_USER_LOCK);
//		if(obj == null)
//			return false;
//		return (Boolean)obj;
//	}
}
