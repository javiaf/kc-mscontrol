package com.tikal.mscontrol.networkconnection;

import com.tikal.mscontrol.MsControlException;
import com.tikal.mscontrol.join.JoinableContainer;
import com.tikal.mscontrol.resource.ResourceContainer;

public interface NetworkConnection extends JoinableContainer, ResourceContainer {

	public SdpPortManager getSdpPortManager() throws MsControlException;
}
