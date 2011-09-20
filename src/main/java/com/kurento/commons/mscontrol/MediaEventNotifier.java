package com.kurento.commons.mscontrol;

/**
 * The class of objects that can send MediaEvent to listeners
 */
public interface MediaEventNotifier<T extends MediaEvent<?>> {

	/**
	 * Add a listener class. The listener will be called when the operation
	 * completes.
	 */
	public void addListener(MediaEventListener<T> listener);

	/**
	 * Remove a listener that was previously added
	 */
	public void removeListener(MediaEventListener<T> listener);

}
