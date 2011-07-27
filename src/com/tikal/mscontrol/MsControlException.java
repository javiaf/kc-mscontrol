package com.tikal.mscontrol;


public class MsControlException extends Exception {
	
	private static final long serialVersionUID = 5114447844981856910L;

	public MsControlException(String message) {
		super(message);
	}
	
	public MsControlException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
