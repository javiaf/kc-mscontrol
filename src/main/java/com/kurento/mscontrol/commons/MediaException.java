/*
 * (C) Copyright 2013 Kurento (http://kurento.org/)
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 */

package com.kurento.mscontrol.commons;

/**
 * General purpose exception.
 */
public class MediaException extends Exception {

	private static final long serialVersionUID = 5114447844981856910L;

	/**
	 * Constructs a MsControlException with the specified detail message
	 * 
	 * @param message
	 *            the detail message
	 */
	public MediaException(String message) {
		super(message);
	}

	/**
	 * Constructs a MsControlException with its origin and the specified detail
	 * message
	 * 
	 * @param message
	 *            the detail message
	 * @param cause
	 */
	public MediaException(String message, Throwable cause) {
		super(message, cause);
	}

}
