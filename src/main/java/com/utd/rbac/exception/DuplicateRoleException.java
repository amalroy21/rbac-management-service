package com.utd.rbac.exception;

public class DuplicateRoleException extends RuntimeException {

	private static final long serialVersionUID = -5548163935663730167L;
	
	/**
	 * Represents DuplicateRole message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public DuplicateRoleException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns DuplicateRole message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
