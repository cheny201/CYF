package com.cyf.security.pojo;

import java.io.Serializable;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the T_CYF_ROLE database table.
 * 
 */
@Entity
@Table(name="T_CYF_ROLE")
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	public static final Boolean ENABLED_TRUE = Boolean.TRUE;
	public static final Boolean ENABLED_FALSE = Boolean.FALSE;
	
	@Id
	@Column(name="ROLE_CODE")
	private Long roleCode;//权限代码
	
	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="DESCRIPTION")
	private String description;//权限描述

	@Column(name="ORG_CODE")
	private String orgCode;//所属机构
	
	@Transient
	private String orgName;
	
	@Column(name="ENABLED")
	private String enabled;//有效状态:0无效 1有效

	@Column(name="INSERT_USER")
	private String insertUser;//初次插入人
	
	@Column(name="UPDATE_USER")
	private String updateUser;//更新人

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INSERT_TIME")
	private Date insertTime;//首次插入时间

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPDATE_TIME")
	private Date updateTime;//更新时间

	@Transient
	private List<User> userList = new ArrayList<User>();
	
	@Transient
	private List<Resources> roleResourcesList = new ArrayList<Resources>();


	
	public Role() {
	}
	
	

	public Role(Long roleCode, String roleName, String orgCode,
			String orgName, String enabled) {
		super();
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.enabled = enabled;
	}



	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	/**
	 * @param resourceType
	 *            资源类型
	 * @return List<Resources> 返回当前角色resourceType类型的Resource集合
	 */
	public List<Resources> getResourcesList(final String resourceType) {
		List<Resources> returnList = new ArrayList<Resources>();
		for (int i = 0; i < this.roleResourcesList.size(); i++) {
			Resources resource = this.roleResourcesList.get(i);
			// 菜单类型
			if (resourceType.equalsIgnoreCase(Resources.MENU)
					&& resource.getResourceType().equalsIgnoreCase(
							Resources.MENU)) {
				returnList.add(resource);
			} else if (resourceType.equalsIgnoreCase(Resources.BUTTON)
					&& resource.getResourceType().equalsIgnoreCase(
							Resources.BUTTON)) {
				returnList.add(resource);
			} 
		}
		return returnList;
	}
	
	public List<Resources> getRoleResourcesList() {
		return roleResourcesList;
	}

	public void setRoleResourcesList(List<Resources> roleResourcesList) {
		this.roleResourcesList = roleResourcesList;
	}

	public Long getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
}