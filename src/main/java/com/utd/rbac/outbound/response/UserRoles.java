package com.utd.rbac.outbound.response;

import java.util.List;

import com.utd.rbac.data.model.Role;


/**
 * Represents the Get User-Roles Response
 */
public class UserRoles {

	/**
	 * Represents user id
	 */
	private String userID;

	/**
	 * Represents user email
	 */
	private String userEmail;

	/**
	 * Represents user roles
	 */
	private List<Role> roles;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String id) {
		this.userID = id;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "UserRoles [userID=" + userID + ", userEmail=" + userEmail + ", roles=" + roles + "]";
	}
}
