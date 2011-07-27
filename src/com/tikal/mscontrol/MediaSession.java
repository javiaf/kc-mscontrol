package com.tikal.mscontrol;

import com.tikal.mscontrol.mediacomponent.MediaComponent;
import com.tikal.mscontrol.networkconnection.NetworkConnection;

/**
 * <p>
 * A MediaSession is a container and factory for media objects. It handles the
 * cleanup on <code>release()</code>.
 * 
 * </p>
 * 
 * @author Miguel París Díaz
 * 
 */
public interface MediaSession extends MediaObject {

	/**
	 * </p> Create a NetworkConnection.
	 * 
	 * </p>
	 * 
	 * @return a NetworkConnection
	 * @throws MsControlException
	 */
	public NetworkConnection createNetworkConnection() throws MsControlException;

	/**
	 * </p> Create a MediaComponent.
	 * 
	 * </p>
	 * 
	 * @param predefinedConfig
	 *            Defines the concrete MediaComponent to create. <br>
	 *            The list of predefined Configurations is dependent on the
	 *            implementation.
	 * @param params
	 *            Parameters to create a MediaComponent. The possible parameters
	 *            for each concrete component must be defined on the
	 *            implementation.
	 * @return a MediaComponent
	 * @throws MsControlException
	 */
	public MediaComponent createMediaComponent(Configuration<MediaComponent> predefinedConfig,
			Parameters params) throws MsControlException;

}
