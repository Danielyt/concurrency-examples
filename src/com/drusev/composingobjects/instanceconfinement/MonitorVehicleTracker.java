/**
 * 
 */
package com.drusev.composingobjects.instanceconfinement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Drusev
 *
 */
public class MonitorVehicleTracker {

	// Guarded by this
	private final Map<String, MutablePoint> locations;

	public MonitorVehicleTracker(final Map<String, MutablePoint> locations) {
		// deep copy is necessary because the contents of the collection is
		// mutable, which means that it can be modified from outside
		this.locations = deepCopy(locations);
	}

	public synchronized Map<String, MutablePoint> getLocations() {
		return deepCopy(locations);
	}

	public synchronized MutablePoint getLocation(final String id) {
		MutablePoint location = locations.get(id);
		return null != location ? new MutablePoint(location) : null;
	}

	public synchronized void setLocation(final String id, final int x, final int y) {
		MutablePoint location = locations.get(id);
		if (null == location) {
			throw new IllegalArgumentException("No such ID: " + id);
		}
		// this is why it's not a problem that the internal state is held within
		// an unmodifiable map, if MutablePoint were immutable then we would
		// have to put a new value for the given id in the map.
		location.x = x;
		location.y = y;
	}

	private static Map<String, MutablePoint> deepCopy(final Map<String, MutablePoint> locations) {
		Map<String, MutablePoint> result = new HashMap<>();
		for (String id : locations.keySet()) {
			result.put(id, new MutablePoint(locations.get(id)));
		}
		// an unmodifiable map is returned because new locations will not be
		// added and since MutablePoint is mutable, we won't have to association
		// new values with a given key when changing a vehicle location
		return Collections.unmodifiableMap(result);
	}

}
