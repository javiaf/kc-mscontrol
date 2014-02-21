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

import com.kurento.mscontrol.commons.networkconnection.SdpPortManagerEvent;

/**
 * An EventType indicates the type of event that a Resource sends.<br>
 * For example, a SDP port manager may signal that
 * {@link SdpPortManagerEvent#OFFER_GENERATED OFFER_GENERATED} , or
 * {@link SdpPortManagerEvent#ANSWER_PROCESSED ANSWER_PROCESSED}.
 */
public interface EventType {

}