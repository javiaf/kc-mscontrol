package com.tikal.mscontrol.networkconnection;

import com.tikal.mscontrol.MsControlException;

public class SdpPortManagerException extends MsControlException {

	private static final long serialVersionUID = -4630071192467596504L;

	public SdpPortManagerException(String message) {
		super(message);
	}

	public SdpPortManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
