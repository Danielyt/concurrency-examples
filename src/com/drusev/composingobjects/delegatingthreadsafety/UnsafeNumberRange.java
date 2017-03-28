/**
 * 
 */
package com.drusev.composingobjects.delegatingthreadsafety;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * There is a constraint on both state variables that is not ensured by the
 * class, so it is not thread safe, even though each individual sate variable
 * is.
 * 
 * @author Drusev
 *
 */
public class UnsafeNumberRange {

	// invariant lower <= upper
	private final AtomicInteger lower = new AtomicInteger(0);
	private final AtomicInteger upper = new AtomicInteger(0);

	public void setLower(final int i) {
		// warning - unsafe check-then-act
		if (i > upper.get()) {
			throw new IllegalArgumentException("can’t set lower to " + i + " > upper");
		}

		lower.set(i);
	}

	public void setUpper(final int i) {
		// warning unsafe check-then-act
		if (i < lower.get()) {
			throw new IllegalArgumentException("can’t set lower to " + i + " > upper");
		}
		upper.set(i);
	}

	public boolean isInRange(final int i) {
		return (i >= lower.get() && i <= upper.get());
	}
}
