package com.cyf.security.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The primary key class for the T_CYF_ROLE_RESOURCE database table.
 * 
 */
@Embeddable
public class RoleResourcePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ROLE_CODE")
	private Long roleCode;//权限代码

	@Column(name="RESOURCE_CODE")
	private Long resourceCode;//资源代码

	public RoleResourcePK() {
	}
	public Long getRoleCode() {
		return this.roleCode;
	}
	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}
	public Long getResourceCode() {
		return this.resourceCode;
	}
	public void setResourceCode(Long resourceCode) {
		this.resourceCode = resourceCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RoleResourcePK)) {
			return false;
		}
		RoleResourcePK castOther = (RoleResourcePK)other;
		return 
			this.roleCode.equals(castOther.roleCode)
			&& this.resourceCode.equals(castOther.resourceCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.roleCode.hashCode();
		hash = hash * prime + this.resourceCode.hashCode();
		
		return hash;
	}
}