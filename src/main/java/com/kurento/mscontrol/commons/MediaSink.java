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


/**
 * A MediaSink receives media from a connected MediaSrc (if any)
 * 
 */
public interface MediaSink extends MediaStream {

	/**
	 * Returns the Joined MediaSrc or null if not joined
	 * 
	 * @return The joined MediaSrc or null if not joined
	 */
	public MediaSrc getConnectedSrc();
}
