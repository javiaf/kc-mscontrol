package com.kurento.mscontrol.commons.junit.util;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kurento.mscontrol.commons.MediaSession;

public abstract class TestCaseBase extends TestCase {

	protected static final Logger log = LoggerFactory
			.getLogger("KcMsControlTests");

	private static MediaSession mediaSession = null;

	public static MediaSession getMediaSession() {
		return mediaSession;
	}

	public static void setMediaSession(MediaSession mediaSession) {
		TestCaseBase.mediaSession = mediaSession;
	}

	protected void checkMediaSessionIsNotNull(MediaSession mediaSession) {
		Assert.assertNotNull(
				"mediaSession is null, it must be assigned using setMediaSession() static method",
				mediaSession);
	}

}
