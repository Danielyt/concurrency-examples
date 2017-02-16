/**
 * 
 */
package com.drusev.sharingobjects;

/**
 * @author Drusev
 *
 */
public class UnsafeMutableInteger {

	private int value;

	public int get() {
		return value;
	}

	public void set(final int value) {
		this.value = value;
	}
}
