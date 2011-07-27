package com.tikal.mscontrol;

public interface MediaEventListener<T extends MediaEvent<?>> {

	public void onEvent(T event);
	
}
