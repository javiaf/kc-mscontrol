package com.kurento.commons.mscontrol.join;

public class TooManyJoineesException extends JoinException {

	private static final long serialVersionUID = -5531183271056157020L;

	public TooManyJoineesException(String message) {
		super(message);
	}

	public TooManyJoineesException(String message, Throwable cause) {
		super(message, cause);
	}
}
