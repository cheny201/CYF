package com.cyf.security.pojo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the T_CYF_USER_ROLE database table.
 * 
 */
@Entity
@Table(name="T_CYF_USER_ROLE")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private UserRolePK id;

	public UserRole() {
	}

	public UserRolePK getId() {
		return this.id;
	}

	public void setId(UserRolePK id) {
		this.id = id;
	}
}