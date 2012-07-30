/*
 * Kurento Commons MSControl: Simplified Media Control API for the Java Platform based on jsr309
 * Copyright (C) 2011  Tikal Technologies
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.kurento.mscontrol.commons;

/**
 * Definition of the most common errors, as MediaErr instances.
 */
public interface MediaErr {

	static final MediaErr BAD_ARG = new MediaErr() {
		@Override
		public String toString() {
			return "BAD_ARG";
		}
	};
	static final MediaErr BAD_SERVER = new MediaErr() {
		@Override
		public String toString() {
			return "BAD_SERVER";
		}
	};
	static final MediaErr BUSY = new MediaErr() {
		@Override
		public String toString() {
			return "BUSY";
		}
	};
	static final MediaErr CALL_DROPPED = new MediaErr() {
		@Override
		public String toString() {
			return "CALL_DROPPED";
		}
	};
	static final MediaErr INFINITE_LOOP = new MediaErr() {
		@Override
		public String toString() {
			return "INFINITE_LOOP";
		}
	};
	
	/**
	 * Indicates success: there was no error.
	 */
	static final MediaErr NO_ERROR = new MediaErr() {
		@Override
		public String toString() {
			return "NO_ERROR";
		}
	};
	static final MediaErr NO_TERMINATION = new MediaErr() {
		@Override
		public String toString() {
			return "NO_TERMINATION";
		}
	};
	static final MediaErr NOT_FOUND = new MediaErr() {
		@Override
		public String toString() {
			return "NOT_FOUND";
		}
	};
	static final MediaErr NOT_SUPPORTED = new MediaErr() {
		@Override
		public String toString() {
			return "NOT_SUPPORTED";
		}
	};
	static final MediaErr REFUSED = new MediaErr() {
		@Override
		public String toString() {
			return "REFUSED";
		}
	};
	static final MediaErr RESOURCE_UNAVAILABLE = new MediaErr() {
		@Override
		public String toString() {
			return "RESOURCE_UNAVAILABLE";
		}
	};
	static final MediaErr SERVICE_NOT_DEFINED = new MediaErr() {
		@Override
		public String toString() {
			return "SERVICE_NOT_DEFINED";
		}
	};
	static final MediaErr TIMEOUT = new MediaErr() {
		@Override
		public String toString() {
			return "TIMEOUT";
		}
	};
	static final MediaErr UNKNOWN_ERROR = new MediaErr() {
		@Override
		public String toString() {
			return "UNKNOWN_ERROR";
		}
	};

}
