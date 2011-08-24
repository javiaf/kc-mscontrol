package com.kurento.commons.mscontrol.join;

import com.kurento.commons.mscontrol.MsControlException;

/**
 * A Joinable is suitable for media composition.<br>
 * A Joinable object is either a NetworkConnection or a MediaComponent.<br>
 * It can also be a Stream (audio, video, ...) of any of those objects.<br>
 * Joinables can be joined/unjoined, to make the media stream flow between them.
 * Join operations can be limited both to a stream (i.e audio,video, see
 * {@link JoinableStream.StreamType}) and direction (i.e. SEND, RECV, DUPLEX, see
 * {@link Joinable.Direction}).<br>
 * The join direction can be changed by calling join again, with a different
 * Direction.
 */
public interface Joinable {

	/**
	 * Indicates a direction for the media flow.
	 */
	static enum Direction {
		DUPLEX, RECV, SEND;
	}

	/**
	 * 
	 * @return Other Joinables to which we are connected, on at least one stream
	 *         (possibly more), and at least in one direction (Inclusive list).<br>
	 *         The result includes only the Joinables directly connected to us.
	 * @throws MsControlException
	 */
	public Joinable[] getJoinees() throws MsControlException;

	/**
	 * 
	 * @param direction
	 *            filter on the connection direction
	 * @return an array of other Joinables to which we are joined,
	 *         exclusive/restrictive list: Only the Joinables with at least the
	 *         same streams are listed. (e.g. if this Joinable has both audio
	 *         and video stream, only Joinables connected both with audio and
	 *         video are listed).<br>
	 *         If direction is SEND, then all objects joined in SEND or DUPLEX
	 *         are included.<br>
	 *         If direction is RECV, then only the objects joined in RECV or
	 *         DUPLEX are included.<br>
	 *         If direction is DUPLEX, then only the objects joined in DUPLEX
	 *         are included.<br>
	 *         The result includes only the Joinables directly connected to us.
	 * @throws MsControlException
	 */
	public Joinable[] getJoinees(Direction direction) throws MsControlException;

	/**
	 * Establish a media stream between this object and other.<p>
	 * The Direction argument indicates a direction; The order of the arguments
	 * helps to remember which is the origin and which is the destination. For
	 * example:<br>
	 * <code><b>objectA</b>.join(Direction.<b>SEND</b>, <b>objectB</b>);</code><br>
	 * means that <br>
	 * <code><b>objectA sends to objectB</b></code><br>
	 * The result is strictly equivalent <code>to objectB.join(Direction.RECV,
	 * objectA).</code>
	 * <p>
	 * 
	 * <h3><b>Joining again the same pair of objects ("re-joining")</b></h3>
	 * <p>
	 * The given direction <b>replaces</b> a possibly existing relationship
	 * between the objects.<br>
	 * For example:<br>
	 * <code>ObjectA.join(REVC, ObjectB)</code><br>
	 * followed by<br>
	 * <code>ObjectA.join(SEND, ObjectB)</code><br>
	 * results in ObjectA sending to ObjectB (not duplex, the SEND direction is
	 * <b>not</b> "added" to the RECV direction).
	 * <p>
	 * 
	 * <h3><b>Joining an object to multiple other objects</b></h3>
	 * <p>
	 * The application is allowed to join objectA to objectB, objectC, etc. The
	 * resulting relationship is:
	 * <ul>
	 * <li>objectA will send data to all others (broadcast to objectB, objectC,
	 * etc.)
	 * <li>objectA will only receive from the last joined object
	 * </ul>
	 * 
	 * For example:<br>
	 * <code>ObjectA.join(DUPLEX, ObjectB)</code><br>
	 * <code>ObjectA.join(DUPLEX, ObjectC)</code><br>
	 * <code>ObjectA.join(DUPLEX, ObjectD)</code><br>
	 * ObjectA sends the same stream(s) to ObjectB, ObjectC and ObjectD.<br>
	 * ObjectA only receives from ObjectD.
	 * <p>
	 * 
	 * @param direction
	 *            indicates direction (DUPLEX, SEND, RECV)
	 * @param other
	 *            Joinable object to connect
	 * @throws MsControlException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 * @throws TooManyJoineesException
	 *             if the number of joined objects is too high (value is
	 *             implementation dependent)
	 */
	public void join(Direction direction, Joinable other) throws MsControlException;

	/**
	 * Disconnect any media streams flowing between this object and other's.<p>
	 * 
	 * <b>Note:</b> Changing the direction (e.g. changing from DUPLEX to RECV),
	 * is obtained by calling join again, with the desired direction.
	 * 
	 * @param other
	 *            Joinable object to disconnect
	 * @throws MsControlException
	 * @throws java.lang.IllegalStateException
	 *             if the object has been released
	 */
	public void unjoin(Joinable other) throws MsControlException;

}
