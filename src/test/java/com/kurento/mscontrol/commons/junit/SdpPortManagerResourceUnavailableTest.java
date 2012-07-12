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

public class SdpPortManagerResourceUnavailableTest extends TestCaseBase {

	private static final int WAIT_TIME = 5;

	/**
	 * Test to check that {@link SdpPortManager#generateSdpOffer()} generate a
	 * {@link SdpPortManagerEvent#OFFER_GENERATED} when it generates a correct
	 * SessionSpec.
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
	 * NO RELEASE NetworkConnection
	 * 
	 * ------------------->generateSDPOffer()
	 *                                       ...................>
	 *                                                           (media resources)
	 *                                       <...................
	 * <-------- RESOURCE_UNAVAILABLE ----------
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testGenerateOffer() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		// ///////////////////////////
		// Generate offer OK
		sdpManager.generateSdpOffer();
		SdpPortManagerEvent event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.NO_ERROR, event.getError());
		assertEquals(SdpPortManagerEvent.OFFER_GENERATED, event.getEventType());

		SessionSpec ss = event.getMediaServerSdp();
		assertNotNull(ss);

		List<MediaSpec> mediaList = ss.getMediaSpecs();
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
		// Generate offer fail
		sdpManager.generateSdpOffer();
		event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.RESOURCE_UNAVAILABLE, event.getError());
		assertNull(event.getEventType());

		assertNull(event.getMediaServerSdp());
		// ///////////////////////////

		nc.release();
	}

	/**
	 * Test to check that {@link SdpPortManager#generateSdpOffer()} generate a
	 * {@link SdpPortManagerEvent#OFFER_GENERATED} when it generates a correct
	 * SessionSpec.
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
	 * NO RELEASE NetworkConnection
	 * 
	 * ------------------->processSdpOffer(userAgentSDP)
	 *                                       ...................>
	 *                                                           (media resources)
	 *                                       <...................
	 * <-------- RESOURCE_UNAVAILABLE ----------
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testProcessOffer() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		// ///////////////////////////
		// Generate offer OK
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
		// ///////////////////////////

		// ///////////////////////////
		// Process offer fail
		sdpManager.processSdpOffer(sessionSpecOfferToProcess);
		event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.RESOURCE_UNAVAILABLE, event.getError());
		assertNull(event.getEventType());

		assertNull(event.getMediaServerSdp());
		// ///////////////////////////

		nc.release();
	}

}
