/**
 * 
 */
package com.drusev.composingobjects.delegatingthreadsafety;

/**
 * A thread safe mutable class.
 * 
 * @author Drusev
 *
 */
public class SafePoint {

	private int x;
	private int y;

	private SafePoint(final int[] a) {
		this(a[0], a[1]);
	}

	public SafePoint(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	// if the copy constructor were implemented as this(p.x, p.y) there would be
	// a race condition
	public SafePoint(final SafePoint p) {
		this(p.get());
	}

	// the setter and getter return both variables at once to avoid the case
	// where a client could receive an inconsistent point
	public synchronized int[] get() {
		return new int[] { x, y };
	}

	public synchronized void set(final int x, final int y) {
		this.x = x;
		this.y = y;
	}
}
