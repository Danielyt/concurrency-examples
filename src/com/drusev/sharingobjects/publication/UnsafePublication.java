/**
 * 
 */
package com.drusev.sharingobjects.publication;

/**
 * Since {@link Holder} is not immutable, a thread can see the initialized
 * object in an inconsistent state, for example, the value of {@code n} could be
 * 0, because that is the first value the {@code Object} constructor writes in
 * that variable.
 * 
 * @author Drusev
 *
 */
public class UnsafePublication {

	private Holder holder;

	public void initialize() {
		holder = new Holder(8);
	}

	public Holder getHolder() {
		return holder;
	}

}
