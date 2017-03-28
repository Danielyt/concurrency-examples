/**
 * 
 */
package com.drusev.composingobjects.delegatingthreadsafety;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The class does not use synchronization on its own, because all of its state
 * is already handled by thread-safe collections. In other words, the class
 * delegates its thread safety to the collections.
 * 
 * @author Drusev
 *
 */
public class DelegatingVehicleTracker {

	private final ConcurrentMap<String, Point> locations;
	private final Map<String, Point> unmodifiableMap;

	public DelegatingVehicleTracker(final Map<String, Point> points) {
		this.locations = new ConcurrentHashMap<>(points);
		this.unmodifiableMap = Collections.unmodifiableMap(this.locations);
	}

	public Map<String, Point> getLocations() {
		return unmodifiableMap;
	}

	// a safe copy of Point is not necessary because class Point is immutable
	public Point getLocation(final String id) {
		return locations.get(id);
	}

	public void setLocation(final String id, final int x, final int y) {
		if (null == locations.replace(id, new Point(x, y))) {
			throw new IllegalArgumentException("Invalid vehicle name: " + id);
		}
	}
}
