/**
 * 
 */
package com.drusev.composingobjects.delegatingthreadsafety;

/**
 * Immutable point
 * 
 * @author Drusev
 *
 */
public class Point {

	public final int x;
	public final int y;

	public Point(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

}
