package com.tikal.mscontrol.join;


public interface JoinableStream extends Joinable {

	static enum StreamType {
		audio,
		video;
	}
	
	public JoinableContainer getContainer();
	public StreamType getType();
	
}
