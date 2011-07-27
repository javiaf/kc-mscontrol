package com.tikal.mscontrol;

public interface MediaEvent<S> {

	static final MediaErr NO_ERROR = new MediaErr(){};
	
	public MediaErr getError();

	public String getErrorText();

	public EventType getEventType();

	public S getSource();

	public boolean isSuccessful();

}
