package com.kurento.commons.mscontrol.networkconnection;

import com.kurento.commons.mscontrol.MsControlException;

/**
 * General purpose exception for the SdpPortManager.
 */
public class SdpPortManagerException extends MsControlException {

	private static final long serialVersionUID = -4630071192467596504L;

	public SdpPortManagerException(String message) {
		super(message);
	}

	public SdpPortManagerException(String message, Throwable cause) {
		super(message, cause);
	}
}
