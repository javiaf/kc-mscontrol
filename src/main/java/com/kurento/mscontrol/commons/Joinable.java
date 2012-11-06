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

import com.kurento.mscontrol.commons.internal.MediaSink;
import com.kurento.mscontrol.commons.internal.MediaSrc;

/**
 * A Joinable is suitable for media composition.<br>
 * A Joinable object is either a NetworkConnection or a MediaComponent.<br>
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

	abstract Collection<? extends MediaType> getMediaTypes();

	abstract <T extends MediaType> Collection<MediaSrc<T>> getMediaSrcs(T type);

	abstract <T extends MediaType> Collection<MediaSink<T>> getMediaSinks(T type);

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
	 * @throws MediaSessionException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void join(Joinable other) throws MediaSessionException {
		boolean joined = false;

		Collection<? extends MediaType> types = getMediaTypes();
		for (MediaType type : types) {
			Collection<MediaSrc<MediaType>> srcs = getMediaSrcs(type);
			Collection<MediaSink<MediaType>> sinks = other.getMediaSinks(type);

			for (MediaSrc<MediaType> src : srcs) {
				for (MediaSink<MediaType> sink : sinks) {
					try {
						src.join(sink);
						joined = true;
					} catch (MediaSessionException e) {
						// No action needed
					}
				}
			}

			srcs = other.getMediaSrcs(type);
			sinks = getMediaSinks(type);

			for (MediaSrc<MediaType> src : srcs) {
				for (MediaSink<MediaType> sink : sinks) {
					try {
						src.join(sink);
						joined = true;
					} catch (MediaSessionException e) {
						// No action needed
					}
				}
			}
		}

		if (!joined) {
			throw new MediaSessionException(
					"Unable to stablish at least one connection");
		}
	}

	/**
	 * Establish a media stream between this object and <code>other</code>.
	 * <p>
	 * The Direction argument indicates a direction; The order of the arguments
	 * helps to remember which is the origin and which is the destination. For
	 * example:<br>
	 * <code><b>objectA</b>.join(Direction.<b>SEND</b>, <b>objectB</b>);</code><br>
	 * means that <br>
	 * <code><b>objectA sends to objectB</b></code><br>
	 * The result is strictly equivalent <code>to objectB.join(Direction.RECV,
	 * objectA).</code>
	 * <p>
	 * 
	 * <h3><b>Joining again the same pair of objects ("re-joining")</b></h3>
	 * <p>
	 * The given direction <b>replaces</b> a possibly existing relationship
	 * between the objects.<br>
	 * For example:<br>
	 * <code>ObjectA.join(REVC, ObjectB)</code><br>
	 * followed by<br>
	 * <code>ObjectA.join(SEND, ObjectB)</code><br>
	 * results in ObjectA sending to ObjectB (not duplex, the <code>SEND</code>
	 * direction is <b>not</b> "added" to the <code>RECV</code> direction).
	 * <p>
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
	 * <code>ObjectA.join(DUPLEX, ObjectB)</code><br>
	 * <code>ObjectA.join(DUPLEX, ObjectC)</code><br>
	 * <code>ObjectA.join(DUPLEX, ObjectD)</code><br>
	 * ObjectA sends the same stream(s) to ObjectB, ObjectC and ObjectD.<br>
	 * ObjectA only receives from ObjectD.
	 * <p>
	 * 
	 * @param direction
	 *            indicates direction (DUPLEX, SEND, RECV)
	 * @param other
	 *            Joinable object to connect
	 * @throws MediaSessionException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void join(Direction direction, Joinable other)
			throws MediaSessionException {
		Collection<? extends MediaType> types = getMediaTypes();
		for (MediaType type : types) {
			Collection<MediaSrc<MediaType>> srcs = getMediaSrcs(type);
			Collection<MediaSink<MediaType>> sinks = other.getMediaSinks(type);
			// TODO: Continue implementing this
		}
	}

	/**
	 * Establish a media stream between this object and <code>other</code>.
	 * <p>
	 * The Direction argument indicates a direction; The order of the arguments
	 * helps to remember which is the origin and which is the destination. For
	 * example:<br>
	 * <code><b>objectA</b>.join(Direction.<b>SEND</b>, <b>objectB</b>);</code><br>
	 * means that <br>
	 * <code><b>objectA sends to objectB</b></code><br>
	 * The result is strictly equivalent <code>to objectB.join(Direction.RECV,
	 * objectA).</code>
	 * <p>
	 * 
	 * <h3><b>Joining again the same pair of objects ("re-joining")</b></h3>
	 * <p>
	 * The given direction <b>replaces</b> a possibly existing relationship
	 * between the objects.<br>
	 * For example:<br>
	 * <code>ObjectA.join(REVC, ObjectB)</code><br>
	 * followed by<br>
	 * <code>ObjectA.join(SEND, ObjectB)</code><br>
	 * results in ObjectA sending to ObjectB (not duplex, the <code>SEND</code>
	 * direction is <b>not</b> "added" to the <code>RECV</code> direction).
	 * <p>
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
	 * <code>ObjectA.join(DUPLEX, type, ObjectB)</code><br>
	 * <code>ObjectA.join(DUPLEX, type, ObjectC)</code><br>
	 * <code>ObjectA.join(DUPLEX, type, ObjectD)</code><br>
	 * ObjectA sends the same stream(s) to ObjectB, ObjectC and ObjectD.<br>
	 * ObjectA only receives from ObjectD.
	 * <p>
	 * 
	 * @param direction
	 *            indicates direction (DUPLEX, SEND, RECV)
	 * @param type
	 *            indicates the type of the stream to connect (for example audio
	 *            or video only)
	 * @param other
	 *            Joinable object to connect
	 * @throws MediaSessionException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void join(Direction direction, MediaType type, Joinable other)
			throws MediaSessionException {
		// TODO: Implement this.
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
	 * @throws MediaSessionException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void unjoin(Joinable other) throws MediaSessionException {
		// TODO: Implement this
	}

	/**
	 * Disconnect any media streams flowing in the selected direction between
	 * this object and other's.
	 * <p>
	 * 
	 * @param direction
	 *            The direction of the streams to disconnect
	 * @param other
	 *            Joinable object to disconnect
	 * 
	 * @throws MediaSessionException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void unjoin(Direction direction, Joinable other)
			throws MediaSessionException {
		// TODO: Implement this
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
	 * @throws MediaSessionException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void unjoin(Direction direction, MediaType type, Joinable other)
			throws MediaSessionException {
		// TODO: Implement this
	}

}
