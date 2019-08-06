package com.utd.rbac.exception;

public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = -5548163935663730167L;
	
	/**
	 * Represents DuplicateUser message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public DuplicateUserException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns DuplicateUser message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
