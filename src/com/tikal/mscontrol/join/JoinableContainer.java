package com.tikal.mscontrol.join;

import com.tikal.mscontrol.MsControlException;
import com.tikal.mscontrol.join.JoinableStream.StreamType;

/**
 * A Joinable object that contains multiple streams.<br>
 * Provides accessors to objects representing those streams: JoinableStream's.<br>
 * A JoinableStream is also a Joinable, and can be used in place of its
 * container, to restrict a join operation for example. A JoinableContainer has
 * a non-mutable number of streams, determined when the object is built.<br>
 * When a JoinableContainer A is joined to another JoinableContainer B, each
 * stream of A is joined to the corresponding stream of B: audio to audio, video
 * to video, etc.
 * 
 * Examples of JoinableContainer are MediaComponent or NetworkConnection.
 */
public interface JoinableContainer extends Joinable {

	/**
	 * 
	 * @param value
	 *            Identifies a type of media, like audio, video ... see
	 *            JoinableStream.StreamType.
	 * @return a JoinableStream, referencing the media of the given type;<br>
	 *         It can be used to restrict a join to this specific stream.<br>
	 *         null if the container does not support this type of media.
	 * @throws MsControlException
	 */
	public JoinableStream getJoinableStream(StreamType value) throws MsControlException;

	/**
	 * 
	 * @return an array of all existing streams.<br>
	 *         The array may contain more than one stream of each type (e.g. two
	 *         audio, one video).
	 * @throws MsControlException
	 */
	public JoinableStream[] getJoinableStreams() throws MsControlException;

}
