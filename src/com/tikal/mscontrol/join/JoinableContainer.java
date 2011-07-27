package com.tikal.mscontrol.join;

import com.tikal.mscontrol.MsControlException;
import com.tikal.mscontrol.join.JoinableStream.StreamType;

public interface JoinableContainer extends Joinable {

	public JoinableStream getJoinableStream(StreamType value) throws MsControlException;
	public JoinableStream[] getJoinableStreams() throws MsControlException;
	
}
