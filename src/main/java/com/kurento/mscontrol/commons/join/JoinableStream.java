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

/**
 * A <code>JoinableStream</code> represents a media stream channel (or port), of
 * a given type: audio, video, etc. It is a <code>Joinable</code> and as such,
 * can be used as an argument to <code>join</code>.<br>
 * A JoinableStream has always one parent JoinableContainer, and that parent
 * cannot change.<br>
 * Operating on JoinableStreams instead of their JoinableContainer, enables
 * stream-specific compositions, like muting only the video, or sending the
 * video to a different object.<br>
 */
public interface JoinableStream extends Joinable {

	/**
	 * Can be audio or video
	 */
	static enum StreamType {
		audio, video;
	}

	/**
	 * @return the parent container of this stream
	 */
	public JoinableContainer getContainer();

	/**
	 * @return the enumerated type of the stream (audio, video, ...).
	 */
	public StreamType getType();

}
