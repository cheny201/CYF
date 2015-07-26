package com.cy.cyf.framework.security.pojo;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class BaseUser implements UserDetails,Serializable {
	
	private static final long serialVersionUID = 1L;
	@Transient
	protected String userName;
	@Transient
	protected String password;
	@Transient
	protected boolean accountNonLocked = true;
	@Transient
	protected boolean accountNonExpired = true;
	@Transient
	protected boolean credentialsNonExpired = true;
	@Transient
	protected boolean enabled = true;
	
	//获取授权GrantedAuthorityImpl
	@Override
	public abstract Collection<? extends GrantedAuthority> getAuthorities();
	
	@Override
	public String getPassword() {
		return this.password;
	}
	@Override
	public String getUsername() {
		return this.userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}
	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}