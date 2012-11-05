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

import com.kurento.commons.config.Parameters;
import com.kurento.mediaspec.SessionSpec;

/**
 * A NetworkConnection is a {@link JoinableContainer} that drives network media
 * ports.<br>
 * <p>
 * A NetworkConnection can be created with
 * {@link MediaSession#createNetworkConnection()}<br>
 * Example:<br>
 * <code>NetworkConnection myNC = myMediaSession.createNetworkConnection();</code>
 * 
 * It handles a set of media ports, defined by a pair of Session Descriptions.
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
public abstract class NetworkConnection extends Joinable {

	/**
	 * 
	 * @param type
	 *            MediaType of the stream.
	 * @param direction
	 *            of the media.
	 * @return the bitrate associated to the stream of type
	 *         <code>streamType</code> where the media flow in direction
	 *         <code>direction</code>.
	 */
	public abstract long getBitrate(MediaType type, Direction direction);

	/**
	 * This method allows to get information about the network connection, see
	 * each platform documentation to know which information can be get.
	 * 
	 * @param params
	 *            The parameters with the information to get
	 */
	public abstract void getInfo(Parameters params);

	/**
	 * Releases the resources associated to this NetworkConnection.
	 * <p>
	 * The call is cascaded to the children of this object (the objects created
	 * by it).
	 * </p>
	 * Calling this methods also releases all SessionSpecs that have been
	 * negotiated before this point.
	 */
	public abstract void release();

	/**
	 * Request that all pending allocations/initializations are completed.
	 * 
	 * @throws java.lang.IllegalStateException
	 *             if the container has been released
	 * @throws MediaSessionException
	 */
	public abstract void confirm() throws MediaSessionException;

	/**
	 * Request a SessionSpec offer.
	 * 
	 * <p>
	 * The resulting offer is available with
	 * {@link NetworkConnection#getSessionSpec()}
	 * </p>
	 * 
	 * <p>
	 * This can be used to initiate a connection, or to increase/augment the
	 * capabilities of an established connection, like for example adding a
	 * video stream to an audio-only connection.
	 * </p>
	 * 
	 * @param cont
	 *            Continuation object to notify when operation completes
	 */
	public abstract void generateSessionSpecOffer(Continuation cont);

	/**
	 * Request the NetworkConnection to process the given SessionSpec offer
	 * (from the remote User Agent).<br>
	 * The resulting answer is available with
	 * {@link NetworkConnection#getSessionSpec()} and the remote offer will be
	 * returned by {@link NetworkConnection#getRemoteSessionSpec()}
	 * 
	 * @param offer
	 *            SessionSpec offer from the remote User Agent
	 * @param cont
	 *            Continuation object to notify when operation completes
	 */
	public abstract void processSessionSpecOffer(SessionSpec offer,
			Continuation cont);

	/**
	 * Request the NetworkConnection to process the given SessionSpec answer
	 * (from the remote User Agent).<br>
	 * The answer become available on method
	 * {@link NetworkConnection#getRemoteSessionSpec()}
	 * 
	 * @param answer
	 *            SessionSpec answer from the remote User Agent
	 * @param cont
	 *            Continuation object to notify when operation completes
	 */
	public abstract void processSessionSpecAnswer(SessionSpec answer,
			Continuation cont);

	/**
	 * This method gives access to the SessionSpec offered by this
	 * NetworkConnection
	 * 
	 * <p>
	 * <b>Note:</b> This method returns the media previously agreed after a
	 * complete offer-answer exchange. If no offer has been generated yet, it
	 * returns null.
	 * </p>
	 * 
	 * @return The last agreed SessionSpec
	 */
	public abstract SessionSpec getSessionSpec();

	/**
	 * This method gives access to the User Agent session description for media
	 * streams.
	 * <p>
	 * The User Agent is the "remote" end (for example a SipPhone), by
	 * opposition to the Media Server end.
	 * </p>
	 * 
	 * <p>
	 * <b>Note:</b> This method returns the media previously agreed after a
	 * complete offer-answer exchange. If no media has been agreed yet, it
	 * returns null.
	 * </p>
	 * 
	 * @return The last agreed User Agent session description
	 */
	public abstract SessionSpec getRemoteSessionSpec();

	/**
	 * Used as a callback for some NetworkConnection actions
	 * 
	 */
	public interface Continuation {

		/**
		 * This method is called when the operation sucess
		 * 
		 * @param session
		 *            The generated session spec
		 */
		public void onSucess(SessionSpec spec);

		/**
		 * This method gets called when the operation fails
		 * 
		 * @param cause
		 *            The cause of the failure
		 */
		public void onError(Throwable cause);
	}
}
