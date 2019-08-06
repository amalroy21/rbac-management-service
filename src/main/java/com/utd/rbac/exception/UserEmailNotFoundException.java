package com.utd.rbac.exception;

/**
 * This class is to handle UserEmailNotFoundException
 *
 */
public class UserEmailNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 6789209817509394322L;

	/**
	 * Represents UserEmailNotFoundException Message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public UserEmailNotFoundException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns UserEmailNotFoundException Message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
