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

import com.kurento.mscontrol.commons.mediamixer.MediaMixer;
import com.kurento.mscontrol.commons.networkconnection.SdpPortManager;

/**
 * 
 * An event from a MediaEventNotifier. Characterized by the source of the event,
 * and an EventType indicating its nature.
 * 
 */
public interface MediaEvent<S> {

	/**
	 * Error returned by getError() when there is no error.
	 */
	static final MediaErr NO_ERROR = MediaErr.NO_ERROR;

	/**
	 * Identify the reason or cause of an error or failure.
	 * <p>
	 * If this Event is associated with an error, then <code>getError()</code>
	 * returns a MediaErr that identifies the problem.<br>
	 * If this Event is not associated with an error, then
	 * <code>getError()</code> returns {@link MediaErr#NO_ERROR}.
	 * <p>
	 * The list of generic error is defined in class MediaErr. Resources can
	 * define more specific errors.
	 * 
	 * @return a MediaErr
	 */
	public MediaErr getError();

	/**
	 * Returns a human-readable error cause, if any.
	 * <p>
	 * Enables implementors to provide additional information.
	 */
	public String getErrorText();

	/**
	 * Get the EventType that identifies the event nature.
	 * <p>
	 * For completion events, this identifies the operation that has completed.
	 * It should never return null, even in the case of an error event.
	 * 
	 * @return the EventType identifying the event nature
	 */
	public EventType getEventType();

	/**
	 * Gives access to the source of the Media Event. Can be for example:
	 * {@link SdpPortManager} or {@link MediaMixer}.
	 */
	public S getSource();

	public boolean isSuccessful();

}
