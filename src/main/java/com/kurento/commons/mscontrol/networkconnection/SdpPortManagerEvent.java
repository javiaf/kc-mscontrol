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

package com.kurento.commons.mscontrol.networkconnection;

import com.kurento.commons.media.format.SessionSpec;
import com.kurento.commons.mscontrol.EventType;
import com.kurento.commons.mscontrol.MediaErr;
import com.kurento.commons.mscontrol.MediaEvent;

/**
 * An event from a SdpPortManager.
 * <p>
 * 
 * The EventType indicates which operation completed.
 */
public interface SdpPortManagerEvent extends MediaEvent<SdpPortManager> {

	/**
	 * EventType sent by a SdpPortManager when
	 * SdpPortManager.processSdpOffer(SessionSpec) has completed.
	 */
	static final EventType ANSWER_GENERATED = new EventType() {
	};

	/**
	 * EventType sent by a SdpPortManager when
	 * SdpPortManager.processSdpAnswer(SessionSpec) has completed.
	 */
	static final EventType ANSWER_PROCESSED = new EventType() {
	};

	/**
	 * Event sent by the media server to indicate a hard failure on a media
	 * stream.
	 */
	static final EventType NETWORK_STREAM_FAILURE = new EventType() {
	};

	/**
	 * EventType sent by a SdpPortManager when SdpPortManager.generateSdpOffer()
	 * has completed.
	 */
	static final EventType OFFER_GENERATED = new EventType() {
	};

	/**
	 * Error sent by media server in case of resource shortage.
	 */
	static final MediaErr RESOURCE_UNAVAILABLE = new MediaErr() {
	};

	/**
	 * Error reported by the media server when both sides of the media channel
	 * attempted to re-negotiate the SDP at the same time.
	 */
	static final MediaErr SDP_GLARE = new MediaErr() {
	};

	/**
	 * Error sent when the offer/answer procedure does not find any matching
	 * codec for any stream.
	 */
	static final MediaErr SDP_NOT_ACCEPTABLE = new MediaErr() {
	};

	/**
	 * Event sent by the media server to indicate that it requests a change in
	 * the local SDP.
	 */
	static final EventType UNSOLICITED_OFFER_GENERATED = new EventType() {
	};

	/**
	 * Returns the most recent Media Server Session Description.
	 */
	SessionSpec getMediaServerSdp();

}
