package com.utd.rbac.outbound.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.utd.rbac.data.model.Role;

/**
 * This represents the response wrapper
 * @param <T>
 */
@JsonInclude(Include.NON_NULL)
public class Payload<T> {

	/**
	 * This represents the API response for roles
	 */
	@JsonInclude(Include.NON_NULL)
	private List<Role> roles;
	
	/**
	 * This represents the API response for user-roles
	 */
	@JsonInclude(Include.NON_NULL)
	private UserRoles userRoles;
	
	/**
	 * Default Constructor
	 */
	public Payload() {
		super();
	}

	public UserRoles getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
