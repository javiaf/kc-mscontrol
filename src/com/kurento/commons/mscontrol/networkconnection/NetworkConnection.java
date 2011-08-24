package com.kurento.commons.mscontrol.networkconnection;

import com.kurento.commons.mscontrol.MsControlException;
import com.kurento.commons.mscontrol.join.JoinableContainer;
import com.kurento.commons.mscontrol.resource.ResourceContainer;

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
