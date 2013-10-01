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
 * MediaSrc sends media to one of more MediaSink if linked
 * 
 */
public interface MediaSrc extends MediaStream {

	/**
	 * Creates a link between this object and the given sink
	 * 
	 * @param sink
	 *            The MediaSink that will accept this object media
	 * @throws MediaException
	 */
	public void connect(MediaSink sink) throws MediaException;

	/**
	 * Unlinks this element and sink
	 * 
	 * @param sink
	 *            The MediaSink that will stop receiving media from this object
	 * @throws MediaException
	 */
	public void disconnect(MediaSink sink) throws MediaException;
}
