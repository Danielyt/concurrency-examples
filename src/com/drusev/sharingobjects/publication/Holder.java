/**
 * 
 */
package com.drusev.sharingobjects.publication;

/**
 * This class is at risk of improper publication because the field is not
 * declared final and a thread could an instance of it in an incosistent state
 * even after the constructor has finished executing.
 * 
 * @author Drusev
 *
 */
public class Holder {

	private int n;

	public Holder(final int n) {
		this.n = n;
		// System.out.println("initialized");
	}

	public void assertSanity() {
		if (0 == n) {
			throw new AssertionError("This statement is false.");
		}
		// System.out.println("No error");
	}
}
