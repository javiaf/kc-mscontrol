package com.kurento.mscontrol.commons.junit;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import com.kurento.commons.config.Parameters;
import com.kurento.mediaspec.MediaSpec;
import com.kurento.mediaspec.Payload;
import com.kurento.mediaspec.SessionSpec;
import com.kurento.mscontrol.commons.MediaSession;
import com.kurento.mscontrol.commons.NetworkConnection.Continuation;
import com.kurento.mscontrol.commons.junit.util.TestCaseBase;

public class NetworkConnectionNotAcceptableTest extends TestCaseBase {

	private static final int WAIT_TIME = 5;
	private final static TimeUnit WAIT_UNIT = TimeUnit.SECONDS;
	private SessionSpec ss;

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
		final Semaphore sem = new Semaphore(0);

		// ///////////////////////////
		// Generate a SessionSpec as offer.

		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				ss = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable cause) {
				fail("Error generating offer: " + cause.getMessage());
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
		nc = getMediaSession().createNetworkConnection(new Parameters());
		// ///////////////////////////

		mediaList = ss.getMedias();
		assertNotNull(mediaList);
		assertTrue("Answered SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		// Delete all payloads
		for (MediaSpec ms : mediaList)
			if (ms.getPayloads() != null)
				ms.setPayloadsIsSet(false);

		nc.processSessionSpecOffer(ss, new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				fail("This operation should fail");
			}

			@Override
			public void onError(Throwable cause) {
				sem.release();
			}
		});
		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
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
		final Semaphore sem = new Semaphore(0);

		ss = null;
		nc.release();
		nc = getMediaSession().createNetworkConnection(new Parameters());

		nc.processSessionSpecOffer(null, new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				fail("This operation should fail");
			}

			@Override
			public void onError(Throwable cause) {
				sem.release();
			}
		});

		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
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
		final Semaphore sem = new Semaphore(0);

		ss = null;
		nc.release();
		nc = getMediaSession().createNetworkConnection(new Parameters());

		// ///////////////////////////
		// Generate a SessionSpec as offer.

		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				ss = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable cause) {
				fail("Error generating offer: " + cause.getMessage());
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

		// ///////////////////////////

		mediaList = ss.getMedias();
		assertNotNull(mediaList);
		assertTrue("Answered SessionSpec must have at least one MediaSpec.",
				mediaList.size() > 0);

		// Delete all payloads
		for (MediaSpec ms : mediaList)
			if (ms.getPayloads() != null)
				ms.setPayloadsIsSet(false);

		nc.processSessionSpecOffer(ss, new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				fail("This operation should fail");
			}

			@Override
			public void onError(Throwable cause) {
				sem.release();
			}
		});
		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
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
		final Semaphore sem = new Semaphore(0);

		ss = null;
		nc.release();
		nc = getMediaSession().createNetworkConnection(new Parameters());

		// ///////////////////////////
		// Generate offer
		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				ss = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable cause) {
				fail("Error generating offer: " + cause.getMessage());
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
		// ///////////////////////////

		// ///////////////////////////
		// Process answer
		nc.processSessionSpecAnswer(null, new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				fail("This process should fail");
			}

			@Override
			public void onError(Throwable cause) {
				sem.release();
			}
		});
		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
		// ///////////////////////////
	}

}
