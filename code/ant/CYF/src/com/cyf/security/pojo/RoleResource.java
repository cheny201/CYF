package com.cyf.security.pojo;

import java.io.Serializable;
import javax.persistence.*;



/**
 * The persistent class for the T_CYF_ROLE_RESOURCE database table.
 * 
 */
@Entity
@Table(name="T_CYF_ROLE_RESOURCE")
public class RoleResource implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RoleResourcePK id;

	public RoleResource() {
	}

	public RoleResourcePK getId() {
		return this.id;
	}

	public void setId(RoleResourcePK id) {
		this.id = id;
	}

}