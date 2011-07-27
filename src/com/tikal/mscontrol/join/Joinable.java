package com.tikal.mscontrol.join;

import com.tikal.mscontrol.MsControlException;

public interface Joinable {
	
	static enum Direction {
		DUPLEX,
		RECV,
		SEND;
	}
	
	public Joinable[] getJoinees() throws MsControlException;
	public Joinable[] getJoinees(Direction direction) throws MsControlException;
	public void join(Direction direction, Joinable other) throws MsControlException;
	public void unjoin(Joinable other) throws MsControlException;
	
}
