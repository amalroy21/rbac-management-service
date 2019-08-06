package com.utd.rbac.exception;

/**
 * This class is to handle the X-API-KEY not found exception
 */
public class ApiKeyNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -9208147626115644266L;
	
	/**
	 * Represents ApiKeyNotFoundException Message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public ApiKeyNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns ApiKeyNotFoundException message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
