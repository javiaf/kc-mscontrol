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
