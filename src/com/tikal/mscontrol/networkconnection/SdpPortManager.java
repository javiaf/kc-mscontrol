package com.tikal.mscontrol.networkconnection;

import com.tikal.mscontrol.MediaEventNotifier;
import com.tikal.mscontrol.resource.Resource;

/**
 * Handle a set of media ports, defined by a pair of Session Descriptions.
 * <p>
 * The SdpPortManager is usually contained in a NetworkConnection.<br>
 * SDP session descriptions (rfc4566) are used to setup a SdpPortManager.<br>
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
 * <p>
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
 * NetworkConnection.addListener.
 * </ul>
 */
public interface SdpPortManager extends Resource<NetworkConnection>,
		MediaEventNotifier<SdpPortManagerEvent> {

	/**
	 * Request a SDP offer from the Media Server.
	 * <p>
	 * 
	 * When complete, sends a SdpPortManagerEvent with an EventType of
	 * SdpPortManagerEvent.OFFER_GENERATED.<br>
	 * The resulting offer is available with
	 * SdpPortManagerEvent.getMediaServerSdp()
	 * <p>
	 * This can be used to initiate a connection, or to increase/augment the
	 * capabilities of an established connection, like for example adding a
	 * video stream to an audio-only connection.
	 * 
	 * @throws SdpPortManagerException
	 */
	void generateSdpOffer() throws SdpPortManagerException;

	/**
	 * This method gives access to the Media Server session description for
	 * media streams.
	 * <p>
	 * This session description describes what the media server is willing to
	 * accept, according to the offer/answer algorithm.<br>
	 * 
	 * <b>Note:</b> This method returns the media previously agreed after a
	 * complete offer-answer exchange. If no media has been agreed yet, it
	 * returns null. If an offer is in progress from either side, that offer's
	 * session description is not returned here.<br>
	 * As a result, the offer generated by generateSdpOffer() must be retrieved
	 * from the completion event, see SdpPortManagerEvent.getMediaServerSdp().
	 * 
	 * @return The last agreed Media Server session description
	 * @throws SdpPortManagerException
	 */
	byte[] getMediaServerSessionDescription() throws SdpPortManagerException;

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
	byte[] getUserAgentSessionDescription() throws SdpPortManagerException;

	/**
	 * Request the Media Server to process the given SDP answer (from the remote
	 * User Agent).
	 * <p>
	 * When complete, sends a SdpPortManagerEvent with an EventType of
	 * SdpPortManagerEvent.ANSWER_PROCESSED;<br>
	 * 
	 * @param answer
	 *            SDP answer from the remote User Agent
	 * @throws SdpPortManagerException
	 */
	void processSdpAnswer(byte[] answer) throws SdpPortManagerException;

	/**
	 * Request the MediaServer to process the given SDP offer (from the remote
	 * User Agent).
	 * <p>
	 * When complete, sends a SdpPortManagerEvent with an EventType of
	 * SdpPortManagerEvent.ANSWER_GENERATED.<br>
	 * The resulting answer is available with
	 * SdpPortManagerEvent.getMediaServerSdp()
	 * 
	 * @param offer
	 *            SDP offer from the remote User Agent
	 * @throws SdpPortManagerException
	 */
	void processSdpOffer(byte[] offer) throws SdpPortManagerException;

	/**
	 * Cancel the SDP offer that previously was requested with
	 * generateSdpOffer().
	 * <p>
	 * The media server is allowed to free the resources associated to the offer
	 * (including RTP ports).<br>
	 * Usages of this method include a SDP "glare" case, see RFC 3261.
	 * <p>
	 * 
	 * Note that in usual operation, the media server resources are released
	 * when the containing NetworkConnection is released, and the application is
	 * not required to call this method.
	 * 
	 * @throws SdpPortManagerException
	 */
	void rejectSdpOffer() throws SdpPortManagerException;

}
