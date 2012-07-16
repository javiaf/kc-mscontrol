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

public class SdpPortManagerAnswerProcessedTest extends TestCaseBase {

	private final static int WAIT_TIME = 5;

	/**
	 * Test to check that {@link SdpPortManager#processSdpAnswer()} generate a
	 * {@link SdpPortManagerEvent#ANSWER_PROCESSED} when it process a correct
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
	 * ------------------->processSdpAnswer(userAgentSDP)
	 * 
	 * <-------- ANSWER_PROCESSED ----------
	 * 
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testAnswerProcessed() throws Exception {
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
		sdpManager.processSdpAnswer(sessionSpecAnswerToProcess);
		event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.NO_ERROR, event.getError());
		assertEquals(SdpPortManagerEvent.ANSWER_PROCESSED, event.getEventType());
		// ///////////////////////////
	}

}
