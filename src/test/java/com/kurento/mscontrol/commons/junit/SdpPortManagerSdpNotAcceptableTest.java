package com.kurento.mscontrol.commons.junit;

import java.util.List;

import com.kurento.mediaspec.MediaSpec;
import com.kurento.mediaspec.Payload;
import com.kurento.mediaspec.SessionSpec;
import com.kurento.mscontrol.commons.MediaSession;
import com.kurento.mscontrol.commons.junit.util.SdpPortManagerListener;
import com.kurento.mscontrol.commons.junit.util.TestCaseBase;
import com.kurento.mscontrol.commons.networkconnection.SdpPortManager;
import com.kurento.mscontrol.commons.networkconnection.SdpPortManagerEvent;

public class SdpPortManagerSdpNotAcceptableTest extends TestCaseBase {

	private static final int WAIT_TIME = 5;

	/**
	 * Test to check that {@link SdpPortManager#processSdpOffer()} generate a
	 * {@link SdpPortManagerEvent#SDP_NOT_ACCEPTABLE} when it process a
	 * incorrect SessionSpec.
	 * <p>
	 * 
	 * Before run this test a correct {@link MediaSession} object must be set
	 * using {@link #setMediaSession(MediaSession)} method.
	 * 
	 * <pre>
	 * --------->processSdpOffer(userAgentSDP without payloads)
	 *                                                          ...................>
	 *                                                                              (media resources)
	 *                                                          <...................
	 * <----------------- SDP_NOT_ACCEPTABLE -------------------
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testProcessSdpOfferWithoutPayloads() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		// ///////////////////////////
		// Generate a SessionSpec as offer.

		sdpManager.generateSdpOffer();
		SdpPortManagerEvent event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.NO_ERROR, event.getError());
		assertEquals(SdpPortManagerEvent.OFFER_GENERATED, event.getEventType());

		SessionSpec sessionSpecOfferToProcess = event.getMediaServerSdp();
		assertNotNull(sessionSpecOfferToProcess);

		List<MediaSpec> mediaList = sessionSpecOfferToProcess.getMediaSpecs();
		assertNotNull(mediaList);
		assertTrue("Generated SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		for (MediaSpec ms : mediaList) {
			List<Payload> payloadList = ms.getPayloads();
			assertNotNull(mediaList);
			assertTrue("Each MediaSpec must have at least one PayloadSpec.",
					payloadList.size() > 0);
		}

		nc.release();
		// ///////////////////////////

		mediaList = sessionSpecOfferToProcess.getMediaSpecs();
		assertNotNull(mediaList);
		assertTrue("Answered SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		// Delete all payloads
		for (MediaSpec ms : mediaList)
			if (ms.getPayloads() != null)
				ms.deleteAllPayloads();

		sdpManager.processSdpOffer(sessionSpecOfferToProcess);
		event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.SDP_NOT_ACCEPTABLE, event.getError());
		assertNull(event.getEventType());
	}

	/**
	 * Test to check that {@link SdpPortManager#processSdpOffer()} generate a
	 * {@link SdpPortManagerEvent#SDP_NOT_ACCEPTABLE} when it process a null
	 * SessionSpec.
	 * <p>
	 * 
	 * Before run this test a correct {@link MediaSession} object must be set
	 * using {@link #setMediaSession(MediaSession)} method.
	 * 
	 * <pre>
	 * ------------------->processSdpOffer(null)
	 *                                          ...................>
	 *                                                              (media resources)
	 *                                          <...................
	 * <--------- SDP_NOT_ACCEPTABLE -----------
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testProcessSdpNull() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		sdpManager.processSdpOffer(null);
		SdpPortManagerEvent event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.SDP_NOT_ACCEPTABLE, event.getError());
		assertNull(event.getEventType());
	}

	/**
	 * Test to check that {@link SdpPortManager#processSdpAnswer()} generate a
	 * {@link SdpPortManagerEvent#SDP_NOT_ACCEPTABLE} when it process a
	 * incorrect SessionSpec.
	 * <p>
	 * 
	 * Before run this test a correct {@link MediaSession} object must be set
	 * using {@link #setMediaSession(MediaSession)} method.
	 * 
	 * <pre>
	 * ------------------->generateSDPOffer()
	 *                                       ...................>
	 *                                                           (media resources)
	 *                                       <...................
	 * <-------- OFFER_GENERATED ----------
	 * 
	 * ------------------->processSdpAnswer(userAgentSDP without payloads)
	 * 
	 * <-------- SDP_NOT_ACCEPTABLE ----------
	 * 
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testProcessSdpAnswerWithoutPayloads() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		// ///////////////////////////
		// Generate offer
		sdpManager.generateSdpOffer();
		SdpPortManagerEvent event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.NO_ERROR, event.getError());
		assertEquals(SdpPortManagerEvent.OFFER_GENERATED, event.getEventType());

		SessionSpec sessionSpecAnswerToProcess = event.getMediaServerSdp();
		assertNotNull(sessionSpecAnswerToProcess);

		List<MediaSpec> mediaList = sessionSpecAnswerToProcess.getMediaSpecs();
		assertNotNull(mediaList);
		assertTrue("Generated SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		for (MediaSpec ms : mediaList) {
			List<Payload> payloadList = ms.getPayloads();
			assertNotNull(mediaList);
			assertTrue("Each MediaSpec must have at least one PayloadSpec.",
					payloadList.size() > 0);
		}
		// ///////////////////////////

		// Delete all payloads
		for (MediaSpec ms : mediaList)
			if (ms.getPayloads() != null)
				ms.deleteAllPayloads();

		// ///////////////////////////
		// Process answer
		sdpManager.processSdpAnswer(sessionSpecAnswerToProcess);
		event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.SDP_NOT_ACCEPTABLE, event.getError());
		assertNull(event.getEventType());
		// ///////////////////////////
	}

	/**
	 * Test to check that {@link SdpPortManager#processSdpAnswer()} generate a
	 * {@link SdpPortManagerEvent#SDP_NOT_ACCEPTABLE} when it process a
	 * incorrect SessionSpec.
	 * <p>
	 * 
	 * Before run this test a correct {@link MediaSession} object must be set
	 * using {@link #setMediaSession(MediaSession)} method.
	 * 
	 * <pre>
	 * ------------------->generateSDPOffer()
	 *                                       ...................>
	 *                                                           (media resources)
	 *                                       <...................
	 * <-------- OFFER_GENERATED ----------
	 * 
	 * ------------------->processSdpAnswer(null)
	 * 
	 * <-------- SDP_NOT_ACCEPTABLE ----------
	 * 
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testProcessSdpAnswerNull() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		// ///////////////////////////
		// Generate offer
		sdpManager.generateSdpOffer();
		SdpPortManagerEvent event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.NO_ERROR, event.getError());
		assertEquals(SdpPortManagerEvent.OFFER_GENERATED, event.getEventType());

		SessionSpec sessionSpecAnswerToProcess = event.getMediaServerSdp();
		assertNotNull(sessionSpecAnswerToProcess);

		List<MediaSpec> mediaList = sessionSpecAnswerToProcess.getMediaSpecs();
		assertNotNull(mediaList);
		assertTrue("Generated SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		for (MediaSpec ms : mediaList) {
			List<Payload> payloadList = ms.getPayloads();
			assertNotNull(mediaList);
			assertTrue("Each MediaSpec must have at least one PayloadSpec.",
					payloadList.size() > 0);
		}
		// ///////////////////////////

		// ///////////////////////////
		// Process answer
		sdpManager.processSdpAnswer(null);
		event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.SDP_NOT_ACCEPTABLE, event.getError());
		assertNull(event.getEventType());
		// ///////////////////////////
	}

}
