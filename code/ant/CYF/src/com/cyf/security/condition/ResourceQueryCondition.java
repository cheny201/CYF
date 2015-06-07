package com.cyf.security.condition;

/**
 * Copyright FuJian Trust Information Technology CO.,LTD
 * <br/>
 * 资源查询条件
 * @author MaoZhulan(mzl0517@gmail.com)
 * @since 2014-3-5
 */
public class ResourceQueryCondition {

	private String resourceType;
	//资源名称
	private String resourceName;
	//打开资源目标
	private String enabled;
	
	private String parentId;
	
	
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getResourceType() {
		return resourceType;
	}
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}
}
