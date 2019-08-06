package com.utd.rbac.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.utd.rbac.inbound.request.RoleRequest;
import com.utd.rbac.inbound.request.UserRequest;
import com.utd.rbac.outbound.response.ApiResponse;

/**
 * This interface provides methods to deal with User-Roles.
 *
 */
public interface IUserRolesManagementService {

	/**
	 * This method provides Implementation to Post Roles
	 * @param httpHeaders {@link HttpHeaders}
	 * @param roleRequest {@link RoleRequest}
	 * @return ResponseEntity<ApiResponse>
	 */
	public ResponseEntity<ApiResponse> postRoles(HttpHeaders httpHeaders, RoleRequest roleRequest);

	/**
	 * This method provides Implementation to get Users and Roles based on User-Email
	 * @param httpHeaders {@link HttpHeaders}
	 * @return ResponseEntity<ApiResponse>
	 */
	public ResponseEntity<ApiResponse> getUserRoles(HttpHeaders httpHeaders);
	
	/**
	 * This method provides Implementation to Post Users
	 * @param httpHeaders {@link HttpHeaders}
	 * @param userRequest {@link UserRequest}
	 * @return ResponseEntity<ApiResponse>
	 */
	public ResponseEntity<ApiResponse> postUsers(HttpHeaders httpHeaders, UserRequest userRequest);
	
	/**
	 * This method provides Implementation to Delete Users
	 * @param httpHeaders {@link HttpHeaders}
	 * @param userRequest {@link UserRequest}
	 * @return ResponseEntity<ApiResponse>
	 */
	public ResponseEntity<ApiResponse> deleteUsers(HttpHeaders httpHeaders, UserRequest userRequest);

}
