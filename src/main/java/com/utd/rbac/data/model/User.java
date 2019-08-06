package com.utd.rbac.data.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Represents the User 
 */
@Document(collection = "#{usersCollectionName}")
public class User {

	/**
	 * Represents user id
	 */
	private String userID;

	/**
	 * Represents user email
	 */
	private String userEmail;

	/**
	 * Represents user Role IDs
	 */
	private List<String> roleIDs;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

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
		return "User [userID=" + userID + ", userEmail=" + userEmail + ", roleIDs=" + roleIDs + "]";
	}
}
