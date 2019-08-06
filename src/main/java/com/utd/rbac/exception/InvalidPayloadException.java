package com.utd.rbac.exception;

/**
 * This Exception is thrown when the payload is Invalid 
 */
public class InvalidPayloadException extends RuntimeException {

	private static final long serialVersionUID = 6789209817509393712L;

	/**
	 * Represents InvalidPayloadException Message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public InvalidPayloadException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns InvalidPayloadException message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
