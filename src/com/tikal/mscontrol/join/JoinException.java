package com.tikal.mscontrol.join;

import com.tikal.mscontrol.MsControlException;

public class JoinException extends MsControlException {

	private static final long serialVersionUID = 5347011531658378662L;

	public JoinException(String message) {
		super(message);
	}

	public JoinException(String message, Throwable cause) {
		super(message, cause);
	}
}
