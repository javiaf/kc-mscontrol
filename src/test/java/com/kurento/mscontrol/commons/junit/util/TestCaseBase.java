package com.kurento.mscontrol.commons.junit.util;

import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kurento.mscontrol.commons.NetworkConnection;

public class TestCaseBase extends TestCase {

	protected static final Logger log = LoggerFactory
			.getLogger("KcMsControlTests");

	protected static NetworkConnectionFactory factory;

	protected static NetworkConnection nc = null;

	public static void setFactory(NetworkConnectionFactory factory) {
		TestCaseBase.factory = factory;
	}

	@Override
	protected void setUp() throws Exception {
		assertNotNull(
				"Before starting the test you should set a NetworkConnectionFactory",
				factory);
		nc = factory.getNetworkConnection();
		assertNotNull(nc);
		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		nc.release();
		super.tearDown();
	}

}
