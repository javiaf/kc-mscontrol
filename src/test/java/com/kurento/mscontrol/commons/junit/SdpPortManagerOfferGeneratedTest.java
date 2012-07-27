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

public class SdpPortManagerOfferGeneratedTest extends TestCaseBase {

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
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testOfferGenerated() throws Exception {
		SdpPortManager sdpManager = nc.getSdpPortManager();
		SdpPortManagerListener listener = new SdpPortManagerListener();
		sdpManager.addListener(listener);

		sdpManager.generateSdpOffer();
		SdpPortManagerEvent event = listener.poll(WAIT_TIME);
		assertNotNull(event);
		assertEquals(SdpPortManagerEvent.NO_ERROR, event.getError());
		assertEquals(SdpPortManagerEvent.OFFER_GENERATED,
				event.getEventType());

		SessionSpec ss = event.getMediaServerSdp();
		assertNotNull(ss);

		List<MediaSpec> mediaList = ss.getMedias();
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
	}

}
