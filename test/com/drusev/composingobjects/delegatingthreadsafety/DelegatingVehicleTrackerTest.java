/**
 * 
 */
package com.drusev.composingobjects.delegatingthreadsafety;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * @author Drusev
 *
 */
public class DelegatingVehicleTrackerTest {

	@Test
	public void test() {
		Map<String, Point> mapPassedToConstructor = new HashMap<>();
		mapPassedToConstructor.put("id1", new Point(0, 0));
		mapPassedToConstructor.put("id2", new Point(1, 1));

		DelegatingVehicleTracker tracker = new DelegatingVehicleTracker(mapPassedToConstructor);
		mapPassedToConstructor.remove("id1");

		Map<String, Point> locations = tracker.getLocations();
		assertTrue(locations.containsKey("id1"));
		assertTrue(locations.containsKey("id2"));
	}

}
