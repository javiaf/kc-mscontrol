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

package com.kurento.mscontrol.commons.networkconnection;

import com.kurento.mediaspec.SessionSpec;
import com.kurento.mscontrol.commons.MediaEventNotifier;
import com.kurento.mscontrol.commons.resource.Resource;

/**
 * Handle a set of media ports, defined by a pair of Session Descriptions.
 * <p>
 * The SdpPortManager is usually contained in a {@link NetworkConnection}.<br>
 * <a href="http://www.ietf.org/rfc/rfc4566.txt">SDP session descriptions
 * (rfc4566)</a> are used to setup a SdpPortManager.<br>
 * A SdpPortManager can handle multiple streams (audio, video), each of them
 * described by an SDP media description.
 * <p>
 * 
 * <pre>
 *  --------------------------------------------------------------------------------
 *  -                                           stream(media description) <-rtp--->-
 *  -   SdpPortManager(session description)     stream(media description) <-rtp--->-
 *  -                                           ....                               -
 *  --------------------------------------------------------------------------------
 * </pre>
 * 
 * Actually two session descriptions are needed:
 * <ul>
 * <li>A <b>Media</b> Server session description describes the mediaserver-local
 * side of a media connection (what the media server accepts to receive)
 * <li>A <b>User Agent</b> session description, describing the remote side (for
 * example a SIPPhone)
 * 
 * 
 * <pre>
 * UserAgent                             Media Server
 *    ----                                 ---------
 *    -  -                                 -       -
 *    -  -        <-------RTP------>       -       -
 *    ----                                 ---------
 * </pre>
 * 
 * The SdpPortManager is compatible with the offer/answer model.
 * <p>
 * The Relationship with SIP signaling messages is described below:
 * 
 * <pre>
 *  A) incoming INVITE with SDP offer:
 *   UserAgent                    JSR 309 Application         SdpPortManager
 *   =============================================================================
 *       ------INVITE----------------->
 *          +userAgentSDP
 *                                     ---------------------->processSdpOffer(userAgentSDP)
 *                                                                     ...................>(media server)
 *                                                                     <...................
 *                                     <-------- Event ----------------
 *                                         +getMediaServerSdp()
 *       <-------- 200 OK -------------
 *              +mediaServerSDP
 *                 
 *       --------- ACK -------------->
 *            
 * 
 *  B) incoming INVITE without SDP:
 *   UserAgent                    JSR 309 Application         SdpPortManager
 *   =============================================================================
 *       ------INVITE----------------->
 *                                     ---------------------->generateSDPOffer()
 *                                                                     ...................>(media server)
 *                                                                     <...................
 *                                     <-------- Event ----------------
 *                                         +getMediaServerSdp()
 *       <-------- 200 OK -------------
 *              +mediaServerSDP
 *                 
 *       --------- ACK -------------->
 *              +userAgentSDP
 *                                    ------------------------>processSdpAnswer(userAgentSDP)
 *            
 * 
 *  C) outgoing INVITE with SDP offer
 *   UserAgent                    JSR 309 Application         SdpPortManager
 *   =============================================================================
 *                                     ---------------------->generateSDPOffer()
 *                                                                     ...................>(media server)
 *                                                                     <...................
 *                                     <-------- Event ----------------
 *                                         +getMediaServerSdp()
 *       <------INVITE-----------------
 *          +mediaServerSDP
 * 
 *       --------- 200 OK ------------>
 *              +userAgentSDP
 *                                    ------------------------>processSdpAnswer(userAgentSDP)
 *                                                                     ...................>(media server)
 *                                                                     <...................
 *                                     <-------- Event ----------------
 *       <--------- ACK --------------
 *  
 *  D) outgoing INVITE without SDP
 *   UserAgent                    JSR 309 Application         SdpPortManager
 *   =============================================================================
 *       <------INVITE-----------------
 * 
 *       --------- 200 OK ------------>
 *              +userAgentSDP
 *                                     ---------------------->processSdpOffer(userAgentSDP)
 *                                                                     ...................>(media server)
 *                                                                     <...................
 *                                     <-------- Event ----------------
 *                                         +getMediaServerSdp()
 *       <--------- ACK --------------
 *            +mediaServerSDP
 * </pre>
 * 
 * <p>
 * (this is provided as a help in understanding, but the SdpPortManager has no
 * dependency on any signaling protocol, including SIP)
 * <p>
 * To receive the events, the Application must add a MediaEventListener, using
 * {@link NetworkConnection}.addListener.
 * </ul>
 */
public interface SdpPortManager extends Resource<NetworkConnection>,
		MediaEventNotifier<SdpPortManagerEvent> {

	/**
	 * Request a SDP offer from the Media Server.
	 * <p>
	 * 
	 * When complete, sends a {@link SdpPortManagerEvent} with an EventType of
	 * {@link SdpPortManagerEvent#OFFER_GENERATED}.<br>
	 * The resulting offer is available with
	 * {@link SdpPortManagerEvent#getMediaServerSdp()}
	 * <p>
	 * This can be used to initiate a connection, or to increase/augment the
	 * capabilities of an established connection, like for example adding a
	 * video stream to an audio-only connection.
	 * 
	 * @throws SdpPortManagerException
	 */
	void generateSdpOffer() throws SdpPortManagerException;

	/**
	 * Request the MediaServer to process the given SDP offer (from the remote
	 * User Agent).<br>
	 * When complete, sends a SdpPortManagerEvent with an EventType of
	 * {@link SdpPortManagerEvent#ANSWER_GENERATED}.<br>
	 * The resulting answer is available with
	 * {@link SdpPortManagerEvent#getMediaServerSdp()}
	 * 
	 * @param offer
	 *            SDP offer from the remote User Agent
	 * @throws SdpPortManagerException
	 */
	void processSdpOffer(SessionSpec offer) throws SdpPortManagerException;

	/**
	 * Request the Media Server to process the given SDP answer (from the remote
	 * User Agent).<br>
	 * When complete, sends a {@link SdpPortManagerEvent} with an EventType of
	 * {@link SdpPortManagerEvent#ANSWER_PROCESSED}.<br>
	 * 
	 * @param answer
	 *            SDP answer from the remote User Agent
	 * @throws SdpPortManagerException
	 */
	void processSdpAnswer(SessionSpec answer) throws SdpPortManagerException;

	/**
	 * Cancel the SDP offer that previously was requested with {@link
	 * generateSdpOffer()}.
	 * <p>
	 * The media server is allowed to free the resources associated to the offer
	 * (including RTP ports).<br>
	 * Usages of this method include a SDP "glare" case, see <a
	 * href="http://www.ietf.org/rfc/rfc3261.txt">RFC 3261</a>.
	 * <p>
	 * 
	 * Note that in usual operation, the media server resources are released
	 * when the containing NetworkConnection is released, and the application is
	 * not required to call this method.
	 * 
	 * @throws SdpPortManagerException
	 */
	void rejectSdpOffer() throws SdpPortManagerException;

	/**
	 * This method gives access to the Media Server session description for
	 * media streams.
	 * <p>
	 * This session description describes what the media server is willing to
	 * accept, according to the
	 * 
	 * <a href="http://www.ietf.org/rfc/rfc3264.txt">offer/answer algorithm</a><br>
	 * 
	 * <b>Note:</b> This method returns the media previously agreed after a
	 * complete offer-answer exchange. If no media has been agreed yet, it
	 * returns null. If an offer is in progress from either side, that offer's
	 * session description is not returned here.<br>
	 * As a result, the offer generated by {@link generateSdpOffer()} must be
	 * retrieved from the completion event, see
	 * {@link SdpPortManagerEvent#getMediaServerSdp()}.
	 * 
	 * @return The last agreed Media Server session description
	 * @throws SdpPortManagerException
	 */
	SessionSpec getMediaServerSessionDescription()
			throws SdpPortManagerException;

	/**
	 * This method gives access to the User Agent session description for media
	 * streams.
	 * <p>
	 * The User Agent is the "remote" end (for example a SipPhone), by
	 * opposition to the Media Server end.
	 * <p>
	 * 
	 * <b>Note:</b> This method returns the media previously agreed after a
	 * complete offer-answer exchange. If no media has been agreed yet, it
	 * returns null.
	 * 
	 * @return The last agreed User Agent session description
	 * @throws SdpPortManagerException
	 */
	SessionSpec getUserAgentSessionDescription() throws SdpPortManagerException;

}