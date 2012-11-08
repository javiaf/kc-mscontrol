package com.kurento.mscontrol.commons.junit;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.kurento.mediaspec.MediaSpec;
import com.kurento.mediaspec.Payload;
import com.kurento.mediaspec.SessionSpec;
import com.kurento.mscontrol.commons.NetworkConnection;
import com.kurento.mscontrol.commons.NetworkConnection.Continuation;
import com.kurento.mscontrol.commons.junit.util.TestCaseBase;

public class NetworkConnectionAnswerGeneratedTest extends TestCaseBase {

	private final static int WAIT_TIME = 5;
	private final static TimeUnit WAIT_UNIT = TimeUnit.SECONDS;

	private SessionSpec sessionSpecOfferToProcess = null;
	private SessionSpec sessionSpecAnswer = null;

	/**
	 * Test to check that
	 * {@link NetworkConnection#processSessionSpecOffer(SessionSpec, Continuation)}
	 * generate an answer when it process a correct SessionSpec.
	 * <p>
	 * 
	 * Before run this test a correct {@link MediaSession} object must be set
	 * using {@link #setMediaSession(MediaSession)} method.
	 * 
	 * <pre>
	 * ------------------->processSdpOffer(userAgentSDP)
	 *                                       ...................>
	 *                                                           (media resources)
	 *                                       <...................
	 * <-------- ANSWER_GENERATED ----------
	 * </pre>
	 * 
	 * @throws Exception
	 */
	public void testAnswerGenerated() throws Exception {
		// ///////////////////////////
		// Generate a SessionSpec as offer.

		final Semaphore sem = new Semaphore(0);

		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				sessionSpecOfferToProcess = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable t) {
				fail("Error: " + t.getMessage());
			}
		});
		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));

		assertNotNull(sessionSpecOfferToProcess);

		List<MediaSpec> mediaList = sessionSpecOfferToProcess.getMedias();
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

		nc.processSessionSpecOffer(sessionSpecOfferToProcess,
				new Continuation() {

					@Override
					public void onSucess(SessionSpec spec) {
						sessionSpecAnswer = spec;
						sem.release();
					}

					@Override
					public void onError(Throwable t) {
						fail("Error: " + t.getMessage());
					}
				});

		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));

		assertNotNull(sessionSpecAnswer);

		mediaList = sessionSpecAnswer.getMedias();
		assertNotNull(mediaList);
		assertTrue("Answered SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		for (MediaSpec ms : mediaList) {
			List<Payload> payloadList = ms.getPayloads();
			assertNotNull(mediaList);
			assertTrue("Each MediaSpec must have at least one PayloadSpec.",
					payloadList.size() > 0);
		}
	}

}
