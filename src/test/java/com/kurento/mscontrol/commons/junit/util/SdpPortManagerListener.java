package com.kurento.mscontrol.commons.junit.util;

import com.kurento.mscontrol.commons.MediaEventListener;
import com.kurento.mscontrol.commons.networkconnection.SdpPortManagerEvent;

public class SdpPortManagerListener extends EventListener<SdpPortManagerEvent>
		implements MediaEventListener<SdpPortManagerEvent> {

	@Override
	public void onEvent(SdpPortManagerEvent arg0) {
		super.onEvent(arg0);
	}

}
