package com.kurento.commons.mscontrol.networkconnection;

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
	 * SdpPortManager.processSdpOffer(byte[]) has completed.
	 */
	static final EventType ANSWER_GENERATED = new EventType() {
	};

	/**
	 * EventType sent by a SdpPortManager when
	 * SdpPortManager.processSdpAnswer(byte[]) has completed.
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
	byte[] getMediaServerSdp();

}
