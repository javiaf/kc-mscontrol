package com.tikal.mscontrol;

public interface MediaEventNotifier<T extends MediaEvent<?>> {

	public void addListener(MediaEventListener<T> listener);
	public void removeListener(MediaEventListener<T> listener);
//	public MediaSession getMediaSession();
	
}
