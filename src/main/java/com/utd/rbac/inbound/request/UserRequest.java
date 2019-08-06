package com.utd.rbac.inbound.request;

import java.util.List;

/**
 * Represents the Post User Request
 */
public class UserRequest {

	/**
	 * Represents user email
	 */
	private String userEmail;

	/**
	 * Represents user Role IDs
	 */
	private List<String> roleIDs;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<String> getRoleIDs() {
		return roleIDs;
	}

	public void setRoleIDs(List<String> roleIDs) {
		this.roleIDs = roleIDs;
	}

	@Override
	public String toString() {
		return "UserRequest [userEmail=" + userEmail + ", roleIDs=" + roleIDs + "]";
	}
}
