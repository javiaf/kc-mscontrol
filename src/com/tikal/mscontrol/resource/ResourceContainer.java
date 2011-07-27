package com.tikal.mscontrol.resource;

import com.tikal.mscontrol.MsControlException;
import com.tikal.mscontrol.MediaObject;

public interface ResourceContainer extends MediaObject {

	public void confirm() throws MsControlException;
	
}
