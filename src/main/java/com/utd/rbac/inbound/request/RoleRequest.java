package com.utd.rbac.inbound.request;

/**
 * Represents the Post Role Request
 */
public class RoleRequest {

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

	public RoleRequest() {
		super();
	}

	@Override
	public String toString() {
		return "Roles [roleID=" + roleID + ", roleName=" + roleName + "]";
	}
}
