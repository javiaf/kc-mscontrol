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

public class NetworkConnectionAnswerProcessedTest extends TestCaseBase {

	private final static int WAIT_TIME = 5;
	private final static TimeUnit WAIT_UNIT = TimeUnit.SECONDS;
	private SessionSpec sessionSpecAnswerToProcess;

	/**
	 * Test to check that
	 * {@link NetworkConnection#processSessionSpecAnswer(SessionSpec, Continuation)}
	 * executes correctly when it process a correct SessionSpec.
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
		final Semaphore sem = new Semaphore(0);

		// ///////////////////////////
		// Generate offer
		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				sessionSpecAnswerToProcess = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable cause) {
				fail("Error: " + cause.getMessage());
			}
		});

		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
		assertNotNull(sessionSpecAnswerToProcess);

		List<MediaSpec> mediaList = sessionSpecAnswerToProcess.getMedias();
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
		nc.processSessionSpecAnswer(sessionSpecAnswerToProcess,
				new Continuation() {

					@Override
					public void onSucess(SessionSpec spec) {
						sem.release();
					}

					@Override
					public void onError(Throwable cause) {
						fail("Error processing answer: " + cause.getMessage());
					}
				});
		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
		// ///////////////////////////
	}

}
