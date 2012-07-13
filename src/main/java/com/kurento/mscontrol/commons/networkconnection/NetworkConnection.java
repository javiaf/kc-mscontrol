/*
 * Kurento Commons MSControl: Simplified Media Control API for the Java Platform based on jsr309
 * Copyright (C) 2011  Tikal Technologies
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kurento.mscontrol.commons.networkconnection;

import com.kurento.mscontrol.commons.MediaSession;
import com.kurento.mscontrol.commons.MsControlException;
import com.kurento.mscontrol.commons.join.JoinableContainer;
import com.kurento.mscontrol.commons.join.JoinableStream.StreamType;
import com.kurento.mscontrol.commons.resource.ResourceContainer;

/**
 * A NetworkConnection is a {@link JoinableContainer} that drives network media
 * ports.<br>
 * Usually it does this with a {@link SdpPortManager} internal Resource.<br>
 * The SdpPortManager handles multiple streams, if needed: an audio and a video
 * stream, for example.
 * <p>
 * A NetworkConnection can be created with
 * {@link MediaSession#createNetworkConnection()}<br>
 * Example:<br>
 * <code>NetworkConnection myNC = myMediaSession.createNetworkConnection();</code>
 */
public interface NetworkConnection extends JoinableContainer, ResourceContainer {

	/**
	 * 
	 * @return the SdpPortManager object of this NetworkConnection.
	 * @throws MsControlException
	 *             if the NetworkConnection contains no SdpPortManager.
	 */
	public SdpPortManager getSdpPortManager() throws MsControlException;

	/**
	 * 
	 * @param streamType
	 *            type of the stream.
	 * @param direction
	 *            of the media.
	 * @return the bitrate associated to the stream of type
	 *         <code>streamType</code> where the media flow in direction
	 *         <code>direction</code>.
	 */
	public long getBitrate(StreamType streamType, Direction direction);

}
