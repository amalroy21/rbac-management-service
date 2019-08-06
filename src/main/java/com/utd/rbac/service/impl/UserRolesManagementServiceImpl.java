package com.utd.rbac.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.utd.rbac.data.dao.IRolesDao;
import com.utd.rbac.data.dao.IUsersDao;
import com.utd.rbac.data.model.Role;
import com.utd.rbac.data.model.User;
import com.utd.rbac.exception.DuplicateRoleException;
import com.utd.rbac.exception.DuplicateUserException;
import com.utd.rbac.exception.InvalidPayloadException;
import com.utd.rbac.exception.InvalidRoleException;
import com.utd.rbac.exception.UserNotFoundException;
import com.utd.rbac.inbound.request.RoleRequest;
import com.utd.rbac.inbound.request.UserRequest;
import com.utd.rbac.outbound.response.ApiResponse;
import com.utd.rbac.outbound.response.IResponse;
import com.utd.rbac.outbound.response.Payload;
import com.utd.rbac.outbound.response.UserRoles;
import com.utd.rbac.service.IUserRolesManagementService;
import com.utd.rbac.util.Constants;
import com.utd.rbac.util.HttpUtil;

/**
 * This class implements the methods related to User-Roles
 */
@Service
public class UserRolesManagementServiceImpl implements IUserRolesManagementService {

	/**
	 * Represents logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserRolesManagementServiceImpl.class);
	
	@Autowired
	private IRolesDao rolesDao;
	
	@Autowired
	private IUsersDao usersDao;
	
	/**
	 * This method performs logic to validate and persist role
	 * @param httpHeaders {@link HttpHeaders}
	 * @param roleRequest {@link RoleRequest}
	 * @return {@link ResponseEntity}
	 */
	@Override
	public ResponseEntity<ApiResponse> postRoles(HttpHeaders httpHeaders, RoleRequest roleRequest) {
		logger.trace("Entering UserRolesManagementServiceImpl.postRoles() method");
		ResponseEntity<ApiResponse> responseEntity = null;
		validateRoleRequest(roleRequest);
		persistRoles(roleRequest);
		ApiResponse apiResponse = HttpUtil.buildSuccessApiResponse(null); 
		responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
		logger.trace("Exiting UserRolesManagementServiceImpl.postRoles() method");
		return responseEntity;
	}

	/**
	 * This method is to validate roles in the Request
	 * @param roleRequest {@link Role}
	 */
	private void validateRoleRequest(RoleRequest roleRequest) {

		if (null != roleRequest && null != roleRequest.getRoleName()) {
			Role role = rolesDao.findByRoleName(roleRequest.getRoleName());
			if (null != role) {
				logger.error(Constants.DUPLICATE_ROLE);
				throw new DuplicateRoleException(Constants.DUPLICATE_ROLE);
			}
			else return;
		}
		logger.error(Constants.INVALID_PAYLOAD);
		throw new InvalidPayloadException(Constants.INVALID_PAYLOAD);
	}
	
	/**
	 * This method persists Roles
	 * @param roleRequest {@link RoleRequest}
	 */
	private void persistRoles(RoleRequest roleRequest) {
		logger.trace("Entering UserRolesManagementServiceImpl.persistRoles() method");
		Role role = new Role();
		role.setRoleID(getUniqueIdentifier());
		role.setRoleName(roleRequest.getRoleName());
		rolesDao.save(role);
		logger.trace("Exiting UserRolesManagementServiceImpl.persistRoles() method");
	}

	/**
	 * This method is used to process getUserRoles for given user-email
	 * @param httpHeaders {@link HttpHeaders}
	 * @return {@link ResponseEntity}
	 */
	@Override
	public ResponseEntity<ApiResponse> getUserRoles(HttpHeaders httpHeaders) {
		logger.trace("Entering UserRolesManagementServiceImpl.getUserRoles() method");
		Payload<IResponse> payload = new Payload<>();
		ResponseEntity<ApiResponse> responseEntity = null;
		String userEmail = httpHeaders.getFirst(Constants.HEADER_USER_EMAIL);
		User user = usersDao.findByUserEmail(userEmail);	
		if(null == user) {
			logger.error(Constants.USER_NOT_FOUND);
			throw new UserNotFoundException(Constants.USER_NOT_FOUND);
		}
		UserRoles userRoles = new UserRoles();
		userRoles.setUserID(user.getUserID());
		userRoles.setUserEmail(user.getUserEmail());
		List<Role> roles = new ArrayList<>();
		Role role; 
		for(String roleID : user.getRoleIDs()) {
			role = rolesDao.findByRoleID(roleID); 
			if(null != role)
				roles.add(role);
		}
		userRoles.setRoles(roles);
		payload.setUserRoles(userRoles);
		ApiResponse apiResponse = HttpUtil.buildSuccessApiResponse(payload);
		responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
		logger.trace("Exiting UserRolesManagementServiceImpl.getUserRoles() method");
		return responseEntity;
	}
	
	/**
	 * This method performs logic to persist users
	 * @param httpHeaders {@link HttpHeaders}
	 * @param userRequest {@link UserRequest}
	 * @return {@link ResponseEntity}
	 */
	@Override
	public ResponseEntity<ApiResponse> postUsers(HttpHeaders httpHeaders, UserRequest userRequest) {
		logger.trace("Entering UserRolesManagementServiceImpl.postUsers() method");
		ResponseEntity<ApiResponse> responseEntity = null;
		validateUserRequest(userRequest);
		persistUsers(userRequest);
		ApiResponse apiResponse = HttpUtil.buildSuccessApiResponse(null); 
		responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
		logger.trace("Exiting UserRolesManagementServiceImpl.postUsers() method");
		return responseEntity;
	}
	
	/**
	 * This method to validate user Request
	 * @param userRequest {@link UserRequest}
	 */
	private void validateUserRequest(UserRequest userRequest) {

		if (null != userRequest && null != userRequest.getUserEmail()) {
			User user = usersDao.findByUserEmail(userRequest.getUserEmail());	
			if (null != user) {
				logger.error(Constants.DUPLICATE_USER);
				throw new DuplicateUserException(Constants.DUPLICATE_USER);
			}else {
				List<Role> rolesFromDB = rolesDao.findAll();
				List<String> roleIDsFromDB = new ArrayList<>();
				for(Role role : rolesFromDB) {
					roleIDsFromDB.add(role.getRoleID());
				}
				List<String> roleIDs = userRequest.getRoleIDs();
				if(null == roleIDs) {
					logger.error(Constants.MISSING_ROLE_ID);
					throw new InvalidRoleException(Constants.INVALID_ROLE);
				}
				for(String roleID : roleIDs) {
					if(!roleIDsFromDB.contains(roleID)) {
						logger.error(Constants.INVALID_ROLE);
						throw new InvalidRoleException(Constants.INVALID_ROLE);
					}
				}
			}
		}else {
			logger.error(Constants.INVALID_PAYLOAD);
			throw new InvalidPayloadException(Constants.INVALID_PAYLOAD);
		}
	}
	
	/**
	 * This method persist Users
	 * @param userRequest {@link UserRequest}
	 */
	private void persistUsers(UserRequest userRequest) {
		logger.trace("Entering UserRolesManagementServiceImpl.persistUsers() method");
		User user = new User();
		user.setUserID(getUniqueIdentifier());
		user.setUserEmail(userRequest.getUserEmail());
		user.setRoleIDs(userRequest.getRoleIDs());
		usersDao.save(user);
		logger.trace("Exiting UserRolesManagementServiceImpl.persistUsers() method");
	}
	
	/**
	 * This method will return a Unique ID
	 */
	private String getUniqueIdentifier() {
		UUID uuid = UUID.randomUUID();
		String numericUUID = Long.toString(uuid.getMostSignificantBits())
                + Long.toString(uuid.getLeastSignificantBits());
		return numericUUID.trim().replace("-","").substring(0, 8);
	}

	@Override
	public ResponseEntity<ApiResponse> deleteUsers(HttpHeaders httpHeaders, UserRequest userRequest) {
		logger.trace("Entering UserRolesManagementServiceImpl.deleteUsers() method");
		ResponseEntity<ApiResponse> responseEntity = null;
		validateUserRequest(userRequest);
		User user = usersDao.findByUserEmail(userRequest.getUserEmail());
		usersDao.delete(user);
		ApiResponse apiResponse = HttpUtil.buildSuccessApiResponse(null); 
		responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.OK);
		logger.trace("Exiting UserRolesManagementServiceImpl.deleteUsers() method");
		return responseEntity;
	}
}