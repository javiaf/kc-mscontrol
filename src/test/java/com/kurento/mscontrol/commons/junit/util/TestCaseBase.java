package com.kurento.mscontrol.commons.junit.util;

import junit.framework.TestCase;

import com.kurento.mscontrol.commons.MediaSession;

public abstract class TestCaseBase extends TestCase {

	private static MediaSession mediaSession = null;

	public static MediaSession getMediaSession() {
		return mediaSession;
	}

	public static void setMediaSession(MediaSession mediaSession) {
		TestCaseBase.mediaSession = mediaSession;
	}

}
