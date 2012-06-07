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

package com.kurento.commons.mscontrol.networkconnection;

import com.kurento.commons.mscontrol.MsControlException;
import com.kurento.commons.mscontrol.join.JoinableContainer;
import com.kurento.commons.mscontrol.join.JoinableStream.StreamType;
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

	public long getBitrate(StreamType streamType, Direction direction);

}
