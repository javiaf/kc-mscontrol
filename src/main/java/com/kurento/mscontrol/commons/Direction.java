/*
 * Kurento Commons MSControl: Simplified Media Control API for the Java Platform based on jsr309
 * Copyright (C) 2012  Tikal Technologies
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
 * Indicates a direction for the media flow.
 */
public enum Direction {
	/**
	 * The media flows in both directions between the objects
	 */
	DUPLEX,

	/**
	 * The media flows into the object, see
	 * {@link Joinable#join(Direction, Joinable)}.
	 */
	RECV,

	/**
	 * The media flows out of the object, see
	 * {@link Joinable#join(Direction, Joinable)}.
	 */
	SEND;
}
