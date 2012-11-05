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
 * This class is required when the application needs to mix (or add, or sum)
 * multiple media streams into a single one. Other <code>.Joinable</code> media
 * objects, like <code>NetworkConnections</code>, can be joined/unjoined to a
 * <code>MediaMixer</code>. The <code>MediaMixer</code> computes the sum of all
 * ingressing streams, and sends it out to every joined object.<br>
 * On the audio stream, the contribution of a given object is actually not
 * re-sent to this particular object (this would add a disturbing echo).<br>
 * A MediaMixer is the base for a conference service. Its usage extends to many
 * other cases, including extended bridge, coaching, background music, etc...<br>
 * A MediaMixer can send events to the application.
 */
public interface Mixer {

	/**
	 * This method provides a joinable suitable to send and receive media from
	 * this mixer. This Joinable has a related MediaSink and MediaSrc, the
	 * MediaSrc emmits all streams except the one from associated MediaSink.
	 * 
	 * @return a media joinable.
	 */
	public Joinable getJoinable();

	/**
	 * <p>
	 * This method releases the previously created joinable with
	 * {@link Mixer#getJoinable()}
	 * </p>
	 * <p>
	 * Before calling this method the joinable can not be used
	 * </p>
	 * 
	 * @param joinable
	 *            The joinable to release
	 */
	public void releaseJoinable(Joinable joinable);

	/**
	 * Releases the resources associated to this MediaMixer.
	 * <p>
	 * The call is cascaded to the children of this object (the objects created
	 * by it).
	 * </p>
	 */
	public void release();

	/**
	 * Sets current element configuration. If there is a previous configuration
	 * it modifies this configuration.
	 * 
	 * @param params
	 *            The configuration to update
	 */
	public void setConfig(Parameters params);

}
