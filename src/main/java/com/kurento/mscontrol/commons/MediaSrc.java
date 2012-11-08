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
	 * @throws MediaSessionException
	 */
	public void connect(MediaSink sink) throws MediaSessionException;

	/**
	 * Unlinks this element and sink
	 * 
	 * @param sink
	 *            The MediaSink that will stop receiving media from this object
	 * @throws MediaSessionException
	 */
	public void disconnect(MediaSink sink) throws MediaSessionException;
}
