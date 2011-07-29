package com.tikal.mscontrol.networkconnection;

import com.tikal.mscontrol.MsControlException;
import com.tikal.mscontrol.join.JoinableContainer;
import com.tikal.mscontrol.resource.ResourceContainer;

/**
 * A NetworkConnection is a JoinableContainer that drives network media ports.<br>
 * Usually it does this with a SdpPortManager internal Resource.<br>
 * The SdpPortManager handles multiple streams, if needed: an audio and a video
 * stream, for example.
 * <p>
 * 
 * A NetworkConnection can be created with
 * MediaSession.createNetworkConnection()<br>
 * Example:<br>
 * <code>NetworkConnection myNC = myMediaSession.createNetworkConnection();</code>
 */
public interface NetworkConnection extends JoinableContainer, ResourceContainer {

	public SdpPortManager getSdpPortManager() throws MsControlException;
}
