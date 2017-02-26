/**
 * 
 */
package com.drusev.sharingobjects;

/**
 * @author Drusev
 *
 */
public class SafeMutableInteger implements MutableInteger {

	private int value;

	@Override
	public synchronized void set(final int value) {
		this.value = value;
	}

	@Override
	public synchronized int get() {
		return this.value;
	}
}
