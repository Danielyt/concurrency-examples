/**
 * 
 */
package com.drusev.composingobjects.instanceconfinement;

/**
 * @author Drusev
 *
 */
public class MutablePoint {

	public int x;
	public int y;

	public MutablePoint() {
		this.x = 0;
		this.y = 0;
	}

	public MutablePoint(MutablePoint p) {
		this.x = p.x;
		this.y = p.y;
	}
}
