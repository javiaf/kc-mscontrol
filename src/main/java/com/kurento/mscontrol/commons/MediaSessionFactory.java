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

import com.kurento.commons.config.Parameters;

/**
 * This class is the entry point where a MediaSession can be obtained
 * 
 */
public interface MediaSessionFactory {

	/**
	 * Creates a new mediaSession object using the given configuration
	 * parameters. See platform documentation for further details about the
	 * parameters.
	 * 
	 * @param parameters
	 *            The configuration of the MediaSession
	 * @return New media session for a platform
	 * @throws MediaSessionException
	 */
	public MediaSession createMediaSession(Parameters parameters)
			throws MediaSessionException;

}
