package com.tikal.mscontrol;

/**
 * 
 * An event from a MediaEventNotifier. Characterized by the source of the event,
 * and an EventType indicating its nature.
 * 
 * @param <S>
 */
public interface MediaEvent<S> {

	/**
	 * Error returned by getError() when there is no error.
	 */
	static final MediaErr NO_ERROR = new MediaErr() {
	};

	/**
	 * Identify the reason or cause of an error or failure.
	 * <p>
	 * If this Event is associated with an error, then getError() returns a
	 * MediaErr that identifies the problem.<br>
	 * If this Event is not associated with an error, then getError() returns
	 * MediaErr.NO_ERROR.
	 * <p>
	 * The list of generic error is defined in class MediaErr. Resources can
	 * define more specific errors.
	 * 
	 * @return a MediaErr
	 */
	public MediaErr getError();

	/**
	 * Returns a human-readable error cause, if any.
	 * <p>
	 * Enables implementors to provide additional information.
	 */
	public String getErrorText();

	/**
	 * Get the EventType that identifies the event nature.
	 * <p>
	 * For completion events, this identifies the operation that has completed.
	 * It should never return null, even in the case of an error event.<br>
	 * Further detail about the reason for this event is available using
	 * ResourceEvent.getQualifier().
	 * 
	 * @return the EventType identifying the event nature
	 */
	public EventType getEventType();

	/**
	 * Gives access to the source of the Media Event. Can be for example: Player
	 * , SdpPortManager, MediaMixer or VxmlDialog
	 */
	public S getSource();

	public boolean isSuccessful();

}
