package com.kurento.commons.mscontrol;

/**
 * General purpose exception
 */
public class MsControlException extends Exception {

	private static final long serialVersionUID = 5114447844981856910L;

	/**
	 * Constructs a MsControlException with the specified detail message
	 * 
	 * @param message
	 *            the detail message
	 */
	public MsControlException(String message) {
		super(message);
	}

	/**
	 * Constructs a MsControlException with its origin and the specified detail
	 * message
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 */
	public MsControlException(String message, Throwable cause) {
		super(message, cause);
	}

}
