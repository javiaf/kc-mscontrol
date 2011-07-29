package com.tikal.mscontrol.join;

/**
 * A JoinableStream represents a media stream channel (or port), of a given
 * type: audio, video, etc. It is a Joinable and as such, can be used as an
 * argument to join.<br>
 * A JoinableStream has always one parent JoinableContainer, and that parent
 * cannot change.<br>
 * Operating on JoinableStreams instead of their JoinableContainer, enables
 * stream-specific compositions, like muting only the video, or sending the
 * video to a different object.<br>
 */
public interface JoinableStream extends Joinable {

	/**
	 *  Can be audio or video
	 */
	static enum StreamType {
		audio, video;
	}

	/**
	 * @return the parent container of this stream
	 */
	public JoinableContainer getContainer();

	/**
	 * @return the enumerated type of the stream (audio, video, ...).
	 */
	public StreamType getType();

}
