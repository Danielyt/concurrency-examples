/**
 * 
 */
package com.drusev.buildingblocks;

import java.util.HashSet;
import java.util.Set;

/**
 * Examples of hidden iterators are calls to a collection's toString, equals and
 * hashCode methods, additionally a constructor which iterates over a passed
 * collection could also throw ConcurrentModificationException.
 * 
 * @author Drusev
 *
 */
public class HiddenIterator {

	private final Set<Integer> set = new HashSet<>();

	public synchronized void add(final Integer i) {
		set.add(i);
	}

	public synchronized void remove(final Integer i) {
		set.remove(i);
	}

	public void addTenThings() {
		for (int i = 0; i < 10; i++) {
			add(i);
		}
		// set.toString() calls the set's iterator and if another thread
		// modifies the set while toString iterates over it, a
		// ConcurrentModificationException is thrown.
		System.out.println("DEBUG: added ten elements to " + set);
	}
}
