package com.kurento.commons.mscontrol.join;

import com.kurento.commons.mscontrol.MsControlException;

public class JoinException extends MsControlException {

	private static final long serialVersionUID = 5347011531658378662L;

	public JoinException(String message) {
		super(message);
	}

	public JoinException(String message, Throwable cause) {
		super(message, cause);
	}
}
