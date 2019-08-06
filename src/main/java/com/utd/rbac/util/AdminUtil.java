package com.utd.rbac.util;

/**
 * Represents valid Admin Util
 *
 */
public enum AdminUtil {

	ADMIN("ADMIN"),
	BANKER("BANKER"),
	USER("USER");

	/**
	 * Represents role
	 */
	private String role;

	/**
	 * Parameterized Constructor
	 * @param xChannel
	 */
	AdminUtil(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}