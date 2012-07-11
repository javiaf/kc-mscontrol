package com.kurento.mscontrol.commons.junit.util;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener<T> {
	private static final Logger log = LoggerFactory
			.getLogger(EventListener.class);

	private BlockingQueue<T> eventQueue = new LinkedBlockingQueue<T>();

	public void onEvent(T event) {
		try {
			eventQueue.put(event);
		} catch (InterruptedException e) {
			log.error("Unable to insert event into FIFO", e);
		}
	}

	public T poll(int timeoutSec) throws InterruptedException {
		return eventQueue.poll(timeoutSec, TimeUnit.SECONDS);
	}

}
