package com.cyf.security.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * The persistent class for the T_CYF_USER database table.
 * 
 */
@SuppressWarnings("deprecation")
@Entity
@Table(name="T_CYF_USER")
public class User implements UserDetails,Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="USER_CODE")
	private String usercode;
	
	@Column(name="USER_NAME")
	private String username;
	
	@Column(name="USER_TYPE")
	private String usertype;
	
	@Column(name="COM_CODE")
	private String comcode;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="ENABLED")
	private boolean enabled;
	
	@Column(name="INSERT_TIME")
	private Date insertTime;
	
	@Column(name="UPDATE_TIME")
	private Date updatedate;

	public User() {
		
	}
	
	public User( String usercode,
			String username, String comcode,String comcname ,boolean enabled) {
		super();
		this.usercode = usercode;
		this.username = username;
		this.comcode = comcode;
		this.comcname = comcname;
		this.enabled = enabled;
	}

	@Transient
	protected boolean accountNonLocked = true;

	@Transient
	protected boolean accountNonExpired = true;

	@Transient
	protected boolean credentialsNonExpired = true;
	
	@Transient
	protected List<Role> roleList = new ArrayList<Role>();
	
	@Transient
	protected List<GrantedAuthority> grantedAuthorities;
	
	@Transient
	protected List<Resources> resources;
	
	@Transient
	protected Map<String, Object> userProperties = new HashMap<String, Object>();

	@Transient
	private String comcname;
	
	/**
	 * @return 用户属性
	 */
	public Map<String, Object> getUserProperties() {
		return this.userProperties;
	}

	/**
	 * @param newUserProperties
	 *            设置用户属性
	 */
	public void setUserProperties(final Map<String, Object> newUserProperties) {
		this.userProperties = newUserProperties;
	}
	/**
	 * @return 获取授权
	 */
	public List<GrantedAuthority> getAuthorities() {
		if (!this.roleList.isEmpty()) {
			this.grantedAuthorities = new ArrayList<GrantedAuthority>();
			for (Role role : this.roleList) {
				this.grantedAuthorities.add(new GrantedAuthorityImpl(role.getRoleCode().toString()));
			}
		}
		return this.grantedAuthorities;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(final List<Role> newRoleList) {
		this.roleList = newRoleList;
	}
	
	/**
	 * 用户是否有某个角色
	 * @param roleCode 角色代码
	 * @return
	 * @since 2013-11-20
	 */
	public boolean hasRole(String roleCode){
		if(StringUtils.isBlank(roleCode))
			return false;
		if(this.roleList != null && this.roleList.size() > 0){
			for(Role role : this.roleList){
				if(role.getRoleCode().equals(roleCode))
					return true;
			}
		}
		return false;
	}

	public List<Resources> getResources() {
		return resources;
	}

	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}

	
	public String getPassword() {
		return this.password;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public boolean isEnabled() {
		return this.enabled;	
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getComcode() {
		return comcode;
	}

	public void setComcode(String comcode) {
		this.comcode = comcode;
	}


	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getComcname() {
		return comcname;
	}

	public void setComcname(String comcname) {
		this.comcname = comcname;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}