package com.kurento.commons.mscontrol.resource;

/**
 * Interface for things common to Resources. Every Resource interface (like
 * SdpPortManager) extends this interface.
 */
public interface Resource<T extends ResourceContainer> {

	public T getContainer();

}
