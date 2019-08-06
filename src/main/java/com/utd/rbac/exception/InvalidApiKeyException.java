package com.utd.rbac.exception;
/**
 * This class is to handle the invalid X-API-Key exception
 */
public class InvalidApiKeyException extends RuntimeException{

	private static final long serialVersionUID = -9208147626115644266L;
	
	/**
	 * Represents InvalidApiKeyException Message
	 */
	private final String message;

	/**
	 * Parameterized Constructor
	 * @param message
	 */
	public InvalidApiKeyException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Returns InvalidApiKeyException message
	 * @return message {@link String}
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
