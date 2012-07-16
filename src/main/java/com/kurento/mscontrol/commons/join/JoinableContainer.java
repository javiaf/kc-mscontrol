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

package com.kurento.mscontrol.commons.join;

import com.kurento.mscontrol.commons.MsControlException;
import com.kurento.mscontrol.commons.join.JoinableStream.StreamType;
import com.kurento.mscontrol.commons.mediacomponent.MediaComponent;
import com.kurento.mscontrol.commons.networkconnection.NetworkConnection;

/**
 * A Joinable object that contains multiple streams.<br>
 * Provides accessors to objects representing those streams:
 * {@link JoinableStream}'s.<br>
 * A <code>JoinableStream</code> is also a <code>Joinable</code>, and can be
 * used in place of its container, to restrict a join operation for example.<br>
 * A JoinableContainer has a non-mutable number of streams, determined when the
 * object is built.<br>
 * When a JoinableContainer A is joined to another JoinableContainer B, each
 * stream of A is joined to the corresponding stream of B: audio to audio, video
 * to video, etc.
 * <p>
 * Examples of JoinableContainer are {@link MediaComponent} or
 * {@link NetworkConnection}.
 */
public interface JoinableContainer extends Joinable {

	/**
	 * 
	 * @param value
	 *            Identifies a type of media, like audio, video ... see
	 *            {@link JoinableStream.StreamType}.
	 * @return a <code>JoinableStream</code>, referencing the media of the given
	 *         type;<br>
	 *         It can be used to restrict a <code>join</code> to this specific
	 *         stream.<br>
	 *         null if the container does not support this type of media.
	 * @throws MsControlException
	 */
	public JoinableStream getJoinableStream(StreamType value) throws MsControlException;

	/**
	 * 
	 * @return an array of all existing streams.<br>
	 *         The array may contain more than one stream of each type (e.g. two
	 *         audio, one video).
	 * @throws MsControlException
	 */
	public JoinableStream[] getJoinableStreams() throws MsControlException;

}
