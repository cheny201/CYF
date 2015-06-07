package com.cyf.security.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * The persistent class for the T_CYF_RESOURCES database table.
 * 
 */
@Entity
@Table(name="T_CYF_RESOURCE")
public class Resources implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final String MENU = "1";
	public static final String BUTTON = "2";
	public static final String LINK="3";
	
	public static final String RELATIVE_PATH_MENU = "RELATIVE_PATH_MENU";
	public static final String ABSOULTE_PATH_MENU = "ABSOULTE_PATH_MENU";
	public static final  String MENU_ROOT = "MENU_ROOT";
	public static final  String MENU_GROUP = "MENU_GROUP";
	public static final  String MENU_ITEM = "MENU_ITEM";
	public static final  String MENU_TARGET_SELF = "SELF";
	public static final  String MENU_TARGET_BLANK = "BLANK";
	public static final  String MENU_TARGET_PARENT = "PARENT";
	public static final  String MENU_TARGET_TOP = "TOP";
	public static final Boolean ENABLED_TRUE = Boolean.TRUE;
	public static final Boolean ENABLED_FALSE = Boolean.FALSE;

	
	@Id
	@Column(name="RESOURCE_CODE")
	private Long resourceCode;//资源代码
	
	@Column(name="RESOURCE_NAME")
	private String resourceName;//资源名称
	
	@Column(name="RESOURCE_TYPE")
	private String resourceType;//资源类型：1菜单2按钮 3链接

	@Column(name="URL")
	private String url;//资源url
	
	@Column(name="ENABLED")
	private String enabled;//有效状态:0无效 1有效
	
	@Column(name="SORT_ORDER")
	private Integer sortOrder;//资源排序号
	
	@Temporal(TemporalType.DATE)
	@Column(name="INSERT_TIME",updatable=false)
	private Date insertTime;//插入时间

	@Temporal(TemporalType.DATE)
	@Column(name="UPDATE_TIME")
	private Date updateTime;//更新时间
	
	@Column(name="INSERT_USER",updatable=false)
	private String insertUser;//首次插入者
	
	@Column(name="UPDATE_USER")
	private String updateUser;//更新者
	
	@Column(name="PARENT_ID")
	private String parentId;//父资源编号
	
	@Column(name="ICON1")
	private String icon1;//资源图标1

	@Column(name="ICON2")
	private String icon2;//资源图标2
	
	@Column(name="TARGET")
	private String target;//资源目标

	@Column(name="DESCRIPTION")
	private String description;//资源描述
	
	@Transient
	private Resources parent;//父资源
	
	@Transient
	private String parentName;
	
	@Transient
	private List<Resources> children = new ArrayList<Resources>();
	
	public Long getResourceCode() {
		return resourceCode;
	}

	public void setResourceCode(Long resourceCode) {
		this.resourceCode = resourceCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getIcon1() {
		return icon1;
	}

	public void setIcon1(String icon1) {
		this.icon1 = icon1;
	}

	public String getIcon2() {
		return icon2;
	}

	public void setIcon2(String icon2) {
		this.icon2 = icon2;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
	
	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Resources> getChildren() {
		return children;
	}

	public void setChildren(List<Resources> children) {
		this.children = children;
	}

	public Resources() {
	}

	public Resources getParent() {
		return parent;
	}

	public void setParent(Resources parent) {
		this.parent = parent;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}