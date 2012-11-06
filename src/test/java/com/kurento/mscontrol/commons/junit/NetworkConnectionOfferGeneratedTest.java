package com.kurento.mscontrol.commons.junit;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.kurento.mediaspec.MediaSpec;
import com.kurento.mediaspec.Payload;
import com.kurento.mediaspec.SessionSpec;
import com.kurento.mscontrol.commons.MediaSession;
import com.kurento.mscontrol.commons.NetworkConnection;
import com.kurento.mscontrol.commons.NetworkConnection.Continuation;
import com.kurento.mscontrol.commons.junit.util.TestCaseBase;

public class NetworkConnectionOfferGeneratedTest extends TestCaseBase {

	private static final int WAIT_TIME = 5;
	private final static TimeUnit WAIT_UNIT = TimeUnit.SECONDS;
	private SessionSpec ss;

	/**
	 * Test to check that
	 * {@link NetworkConnection#generateSessionSpecOffer(Continuation)}
	 * generates a correct offer
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
		final Semaphore sem = new Semaphore(0);
		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				ss = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable cause) {
				fail("Error gerating offer: " + cause.getMessage());
			}
		});

		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
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
