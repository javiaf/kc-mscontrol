package com.tikal.mscontrol;

/**
 * To receive events, an application must register a class extending this interface.
 * 
 * @param <T> the type of event that the listener handles
 */
public interface MediaEventListener<T extends MediaEvent<?>> {

	public void onEvent(T event);
	
}
