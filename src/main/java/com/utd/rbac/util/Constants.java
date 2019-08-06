package com.utd.rbac.util;

/**
 * Represents Constants defined all over the Service
 *
 */
public class Constants {

	 private Constants() {
	    }

    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    
	public static final String SUCCESS_RESPONSE_MESSAGE = "Request Completed Successfully";
	public static final String FAILURE_RESPONSE_MESSAGE = "Process Interrupted";
	public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
	
	public static final String POST_USERS = "PostUsers";
	public static final String POST_ROLES = "PostRolesRequest";
	public static final String GET_USERS = "GetUsersRequest";
	public static final String DELETE_USERS = "DeleteUsersRequest";

	public static final String MISSING_ROLE_ID = "Role ID is Missing";
	public static final String INVALID_ROLE = "Invalid Role";
	public static final String USER_EMAIL_MISSING = "Missing User Email";
	public static final String USER_EMAIL_NOT_VALID = "Invalid UserEmail";
	public static final String DUPLICATE_ROLE = "Duplicate Role";
	public static final String DUPLICATE_USER = "Duplicate User";
	public static final String USER_NOT_FOUND = "User Not Found";

	public static final String INVALID_PAYLOAD = "Invalid Payload";

	public static final String HEADER_USER_EMAIL = "user-email";
	
	/**
	 * URL's
	 */
	public static final String POST_ROLE_URL = "/v1/role";
	public static final String GET_USERS_URL = "/v1/users";
	public static final String POST_USER_URL = "/v1/user";
	public static final String DELETE_USER_URL = "/v1/user";

}
