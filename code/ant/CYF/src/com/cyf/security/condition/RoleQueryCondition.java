package com.cyf.security.condition;

/**
 * Copyright FuJian Trust Information Technology CO.,LTD
 * <br/>
 * 资源查询条件
 * @author MaoZhulan(mzl0517@gmail.com)
 * @since 2014-3-5
 */
public class RoleQueryCondition {

	private String orgCode;//所属机构
	private String roleCode;
	private String roleName;
	private String enabled;
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
