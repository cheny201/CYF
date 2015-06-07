package com.cyf.security.pojo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The primary key class for the T_CYF_USER_ROLE database table.
 * 
 */
@Embeddable
public class UserRolePK implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name="USER_CODE")
	private String userCode;//员工代码

	@Column(name="ROLE_CODE")
	private Long roleCode;//权限代码

	public UserRolePK() {
	}
	public String getUserCode() {
		return this.userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Long getRoleCode() {
		return this.roleCode;
	}
	public void setRoleCode(Long roleCode) {
		this.roleCode = roleCode;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRolePK)) {
			return false;
		}
		UserRolePK castOther = (UserRolePK)other;
		return 
			this.userCode.equals(castOther.userCode)
			&& this.roleCode.equals(castOther.roleCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userCode.hashCode();
		hash = hash * prime + this.roleCode.hashCode();
		
		return hash;
	}
}