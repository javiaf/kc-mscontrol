package com.tikal.mscontrol;

public interface MediaErr {

	static final MediaErr BAD_ARG = new MediaErr(){};
	static final MediaErr BAD_SERVER = new MediaErr(){};
	static final MediaErr BUSY = new MediaErr(){};
	static final MediaErr CALL_DROPPED = new MediaErr(){};
	static final MediaErr INFINITE_LOOP = new MediaErr(){};
	static final MediaErr NO_ERROR = new MediaErr(){};
	static final MediaErr NO_TERMINATION = new MediaErr(){};
	static final MediaErr NOT_FOUND = new MediaErr(){};
	static final MediaErr NOT_SUPPORTED = new MediaErr(){};
	static final MediaErr REFUSED = new MediaErr(){};
	static final MediaErr RESOURCE_UNAVAILABLE = new MediaErr(){};
	static final MediaErr SERVICE_NOT_DEFINED = new MediaErr(){};
	static final MediaErr TIMEOUT = new MediaErr(){};
	static final MediaErr UNKNOWN_ERROR = new MediaErr(){};

}
