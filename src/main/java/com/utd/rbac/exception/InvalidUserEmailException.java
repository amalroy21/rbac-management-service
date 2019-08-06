package com.utd.rbac.exception;

/**
 * This class is to handle InvalidUserEmailException
 *
 */
public class InvalidUserEmailException extends RuntimeException {
	
	private static final long serialVersionUID = 6789209817509394322L;

	/**
	 * Represents InvalidUserEmailException Message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public InvalidUserEmailException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns InvalidUserEmailException Message
	 * 
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
