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

package com.kurento.mscontrol.commons.mediacomponent;

import com.kurento.mscontrol.commons.MsControlException;
import com.kurento.mscontrol.commons.join.JoinableContainer;
import com.kurento.mscontrol.commons.resource.ResourceContainer;

/**
 * <p>
 * A MediaComponent receive and/or send media streams from/to another Joinable
 * object.<br>
 * Treatment of media, in case of receive media, and getting of media, in case
 * of send media, is dependent of implementation.
 * </p>
 * A MediaComponent is initially unrelated to any call. Then, a MediaComponent
 * can be joined/unjoined to another Joinable, normaly to a NetworkConnection.
 * 
 * </p>
 * 
 * @author mparis
 * 
 */
public interface MediaComponent extends JoinableContainer, ResourceContainer {

	/**
	 * <p>
	 * Start treatment or getting of media.
	 * 
	 * @throws MsControlException
	 */
	public void start() throws MsControlException;

	/**
	 * <p>
	 * Stop treatment or getting of media.
	 */
	public void stop();

}
