/*
 * (C) Copyright 2013 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */
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

public class NetworkConnectionResourceUnavailableTest extends TestCaseBase {

	private static final int WAIT_TIME = 5;
	private final static TimeUnit WAIT_UNIT = TimeUnit.SECONDS;
	private SessionSpec ss;

	/**
	 * Test to check that
	 * {@link NetworkConnection#generateSessionSpecOffer(Continuation)} raises
	 * an error when it is called twice
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
		final Semaphore sem = new Semaphore(0);

		// ///////////////////////////
		// Generate offer OK
		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				ss = spec;
				sem.release();
			}

			@Override
			public void onError(Throwable cause) {
				fail("Erorr generating offer: " + cause.getMessage());
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
		// Generate offer fail
		nc.generateSessionSpecOffer(new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				fail("Second offer should raise an errror");
			}

			@Override
			public void onError(Throwable cause) {
				sem.release();
			}
		});

		assertTrue(sem.tryAcquire(WAIT_TIME, WAIT_UNIT));
		// ///////////////////////////
	}

	/**
	 * Test to check that
	 * {@link NetworkConnection#processSessionSpecOffer(SessionSpec, Continuation)}
	 * raises an error when it is called after
	 * {@link NetworkConnection#generateSessionSpecOffer(Continuation)}
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
		ss = null;
		nc.release();
		nc = factory.getNetworkConnection();

		final Semaphore sem = new Semaphore(0);

		// ///////////////////////////
		// Generate offer OK
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
		// Process offer fail
		nc.processSessionSpecOffer(ss, new Continuation() {

			@Override
			public void onSucess(SessionSpec spec) {
				fail("Process an offer after generated an offer should fail");
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
