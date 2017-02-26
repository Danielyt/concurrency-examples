/**
 * 
 */
package com.drusev.sharingobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Drusev
 *
 */
public class EventSource {

	private List<EventListener> listeners;

	public EventSource() {
		listeners = Collections.synchronizedList(new ArrayList<>());
	}

	public void registerListener(final EventListener listener) {
		listeners.add(listener);
	}

	public void raiseEvent(final Event event) {
		synchronized (listeners) {
			for (EventListener listener : listeners) {
				listener.onEvent(event);
			}
		}
	}
}
