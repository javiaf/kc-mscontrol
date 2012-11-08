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

package com.kurento.mscontrol.commons;

import java.util.Collection;

import com.kurento.mediaspec.Direction;

/**
 * A Joinable is suitable for media composition.<br>
 * Joinables can be joined/unjoined, to make the media stream flow between them.
 * Join operations can be limited both to a stream (i.e audio,video, see
 * {@link MediaType}) and direction (i.e. SEND, RECV, DUPLEX, see
 * {@link Direction}).<br>
 * The join direction can be changed by calling join again, with a different
 * <code>Direction</code>.
 */
public abstract class Joinable {

	Joinable() {

	}

	public abstract Collection<MediaSrc> getMediaSrcs();

	public abstract Collection<MediaSink> getMediaSinks();

	/**
	 * Establish a media stream between this object and <code>other</code>.
	 * <p>
	 * 
	 * This method does not return error if at least one connection of any
	 * MediaType and/or Direction can be established. <br/>
	 * This method is offered for simplify applications, but the control of the
	 * links is lost by the application because no notification of what links
	 * succeed or failed is done.
	 * 
	 * <h3><b>Joining an object to multiple other objects</b></h3>
	 * <p>
	 * The application is allowed to join objectA to objectB, objectC, etc. The
	 * resulting relationship is:
	 * <ul>
	 * <li>objectA will send data to all others (broadcast to objectB, objectC,
	 * etc.)
	 * <li>objectA will only receive from the last joined object
	 * </ul>
	 * 
	 * For example:<br>
	 * <code>ObjectA.join(ObjectB)</code><br>
	 * <code>ObjectA.join(ObjectC)</code><br>
	 * <code>ObjectA.join(ObjectD)</code><br>
	 * ObjectA sends the same stream(s) to ObjectB, ObjectC and ObjectD.<br>
	 * ObjectA only receives from ObjectD.
	 * <p>
	 * 
	 * @param other
	 *            Joinable object to connect
	 * @throws MediaException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void join(Joinable other) throws MediaException {
		boolean joined;

		joined = joinSend(other);

		joined |= other.joinSend(this);

		if (!joined) {
			throw new MediaException(
					"Unable to stablish at least one connection");
		}
	}

	private boolean joinSend(Joinable other) throws MediaException {
		boolean joined = false;
		Collection<MediaSrc> srcs = getMediaSrcs();
		Collection<MediaSink> sinks = other.getMediaSinks();

		for (MediaSrc src : srcs) {
			for (MediaSink sink : sinks) {
				if (src.getMediaType().equals(sink.getMediaType())) {
					src.connect(sink);
					joined = true;
				}
			}
		}

		return joined;
	}

	/**
	 * Disconnect any media streams flowing between this object and other's.
	 * <p>
	 * 
	 * <b>Note:</b> Changing the direction (e.g. changing from DUPLEX to RECV),
	 * is obtained by calling join again, with the desired direction.
	 * 
	 * @param other
	 *            Joinable object to disconnect
	 * @throws MediaException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void unjoin(Joinable other) throws MediaException {
		other.unjoinRecv(other);
		unjoinRecv(this);
	}

	private void unjoinRecv(Joinable other) throws MediaException {
		Collection<MediaSrc> srcs = other.getMediaSrcs();
		Collection<MediaSink> sinks = getMediaSinks();

		for (MediaSink sink : sinks) {
			MediaSrc src = sink.getConnectedSrc();
			if (src != null && srcs.contains(src)) {
				src.disconnect(sink);
			}
		}
	}

}
