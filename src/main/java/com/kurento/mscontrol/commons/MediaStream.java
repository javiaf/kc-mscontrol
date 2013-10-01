/*
 * (C) Copyright 2013 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
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
	 * @throws MediaException
	 */
	public void start() throws MediaException;

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
