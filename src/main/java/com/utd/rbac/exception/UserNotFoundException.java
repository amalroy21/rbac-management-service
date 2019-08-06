package com.utd.rbac.exception;

/**
 * This class is to handle UserNotFoundException
 *
 */
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6789209817509394322L;

	/**
	 * Represents UserNotFoundException Message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns UserNotFoundException Message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
