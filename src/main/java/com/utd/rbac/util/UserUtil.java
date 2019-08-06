package com.utd.rbac.util;

/**
 * Represents valid User Util
 *
 */
public enum UserUtil {

	USER("USER");

	/**
	 * Represents role
	 */
	private String role;

	/**
	 * Parameterized Constructor
	 * @param xChannel
	 */
	UserUtil(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}