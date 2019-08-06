package com.utd.rbac.util;

/**
 * Represents valid Banker Util
 *
 */
public enum BankerUtil {

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
	BankerUtil(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
