package com.utd.rbac.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.utd.rbac.controller.UserRolesManagementController;
import com.utd.rbac.exception.DuplicateRoleException;
import com.utd.rbac.exception.DuplicateUserException;
import com.utd.rbac.exception.InvalidPayloadException;
import com.utd.rbac.exception.InvalidRoleException;
import com.utd.rbac.exception.InvalidUserEmailException;
import com.utd.rbac.exception.UserEmailNotFoundException;
import com.utd.rbac.exception.UserNotFoundException;
import com.utd.rbac.outbound.response.ApiResponse;
import com.utd.rbac.outbound.response.Message;
import com.utd.rbac.outbound.response.Status;
import com.utd.rbac.util.Constants;
import com.utd.rbac.util.HttpUtil;


/**
 * This class represents the controller advice for
 * {@link UserRolesManagementController}
 *
 */
@RestControllerAdvice(assignableTypes = {UserRolesManagementController.class })
public class UserRolesManagementControllerAdvice {

	/**
	 * Represents logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserRolesManagementControllerAdvice.class);

	/**
	 * This method handles {@link InvalidRoleException}
	 * 
	 * @param InvalidRoleException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(InvalidRoleException.class)
	public ResponseEntity<ApiResponse> handleException(InvalidRoleException invalidRoleException) {
		List<Message> messages = new ArrayList<>();
		String errorDescription = Constants.INVALID_ROLE;
		messages.add(new Message(errorDescription, Constants.INVALID_ROLE, errorDescription));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(invalidRoleException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method handles {@link InvalidPayloadException}
	 * 
	 * @param InvalidPayloadException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(InvalidPayloadException.class)
	public ResponseEntity<ApiResponse> handleException(InvalidPayloadException invalidPayloadException) {
		List<Message> messages = new ArrayList<>();
		String errorDescription = Constants.INVALID_PAYLOAD;
		messages.add(new Message(errorDescription, Constants.INVALID_PAYLOAD, errorDescription));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(invalidPayloadException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}

	/**
	 * This method handles {@link DuplicateRoleException}
	 * 
	 * @param DuplicateRoleException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(DuplicateRoleException.class)
	public ResponseEntity<ApiResponse> handleException(DuplicateRoleException duplicateRoleException) {
		List<Message> messages = new ArrayList<>();
		String errorDescription =  Constants.DUPLICATE_ROLE;
		messages.add(new Message(errorDescription, Constants.DUPLICATE_ROLE, errorDescription));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(duplicateRoleException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method handles {@link DuplicateUserException}
	 * 
	 * @param DuplicateUserException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<ApiResponse> handleException(DuplicateUserException duplicateUserException) {
		List<Message> messages = new ArrayList<>();
		String errorDescription = Constants.DUPLICATE_USER;
		messages.add(new Message(errorDescription, Constants.DUPLICATE_USER, errorDescription));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(duplicateUserException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method handles {@link UserNotFoundException}
	 * 
	 * @param UserNotFoundException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ApiResponse> handleException(UserNotFoundException userNotFoundException) {
		List<Message> messages = new ArrayList<>();
		messages.add(new Message(Constants.USER_NOT_FOUND, Constants.USER_NOT_FOUND, Constants.USER_NOT_FOUND));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(userNotFoundException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method handles {@link InvalidUserEmailException}
	 * 
	 * @param InvalidUserEmailException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(InvalidUserEmailException.class)
	public ResponseEntity<ApiResponse> handleException(InvalidUserEmailException invalidUserEmailException) {
		List<Message> messages = new ArrayList<>();
		messages.add(new Message(Constants.USER_EMAIL_NOT_VALID, Constants.USER_EMAIL_NOT_VALID, Constants.USER_NOT_FOUND));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(invalidUserEmailException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * This method handles {@link UserEmailNotFoundException}
	 * 
	 * @param userEmailNotFoundException
	 * @return {@link ResponseEntity}
	 */
	@ExceptionHandler(UserEmailNotFoundException.class)
	public ResponseEntity<ApiResponse> handleException(UserEmailNotFoundException userEmailNotFoundException) {
		List<Message> messages = new ArrayList<>();
		String errorDescription = Constants.USER_EMAIL_MISSING;
		messages.add(new Message(errorDescription, Constants.USER_EMAIL_MISSING, errorDescription));
		Status errorResponseStatus = new Status(messages);
		errorResponseStatus.setMessages(messages);
		logger.error(userEmailNotFoundException.getMessage());
		return new ResponseEntity<>(new ApiResponse(errorResponseStatus, null), HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * Exception handler to handle {@link Exception}
	 * 
	 * @param Exception
	 * @return {@link ResponseEntity<Response>}
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse> handleException(Exception ex) {
		logger.error(ex.getMessage());
		return HttpUtil.buildErrorResponse();
	}

}
