/**
 * 
 */
package com.drusev.sharingobjects.visibility;

/**
 * @author Drusev
 *
 */
public class UnsafeMutableInteger implements MutableInteger {

	private int value;

	@Override
	public int get() {
		return value;
	}

	@Override
	public void set(final int value) {
		this.value = value;
	}
}
