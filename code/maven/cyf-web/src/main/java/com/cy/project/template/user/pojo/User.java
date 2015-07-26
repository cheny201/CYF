package com.cy.project.template.user.pojo;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import com.cy.cyf.framework.security.pojo.BaseUser;

@Entity
@Table(name="T_CYF_USER")
public class User extends BaseUser {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_CODE")
	private String userCode;
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}






	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
