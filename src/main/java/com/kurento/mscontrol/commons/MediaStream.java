/*
 * Kurento Commons MSControl: Simplified Media Control API for the Java Platform based on jsr309
 * Copyright (C) 2012  Tikal Technologies
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

import com.kurento.mediaspec.MediaType;


/**
 * <p>
 * A <code>MediaStream</code> receives and/or send media from/to another
 * <code>MediaStream</code> object.<br>
 * Treatment of media is dependent of implementation.
 * </p>
 * 
 */
public interface MediaStream {

	/**
	 * Gets the Joinable that contains this MediaStream
	 * 
	 * @return The parent Joinable
	 */
	public Joinable getJoinable();

	/**
	 * Gets the current MediaType
	 * 
	 * @return a MediaType specification that represents the current MediaType
	 */
	public MediaType getMediaType();

	/**
	 * <p>
	 * Start media flow
	 * </p>
	 * 
	 * @throws MediaSessionException
	 */
	public void start() throws MediaSessionException;

	/**
	 * 
	 * <p>
	 * Stop media flow
	 * </p>
	 */
	public void stop();

	/**
	 * Releases all resources
	 */
	public void release();
}
