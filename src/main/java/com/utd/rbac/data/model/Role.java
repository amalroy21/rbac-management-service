package com.utd.rbac.data.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents the Roles
 */
@Document(collection = "#{rolesCollectionName}")
public class Role {

	/**
	 * Represents Role id
	 */
	private String roleID;

	/**
	 * Represents Role name
	 */
	private String roleName;

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Role() {
		super();
	}

	@Override
	public String toString() {
		return "Roles [roleID=" + roleID + ", roleName=" + roleName + "]";
	}
}
