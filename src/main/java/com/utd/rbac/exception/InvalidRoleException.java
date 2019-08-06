package com.utd.rbac.exception;

/**
 * This class is to handle the X-CorrelationId not found exception
 */

public class InvalidRoleException extends RuntimeException {

	private static final long serialVersionUID = 6789208917509393712L;

	/**
	 * Represents InvalidCorrelationID message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public InvalidRoleException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns InvalidCorrelationID message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
	
}


