/**
 * 
 */
package com.drusev.factorizer;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Factorizer that counts hits using {@link AtomicLong}.
 * 
 * @author Drusev
 *
 */
public class SafeCountingFactorizer extends CountingFactorizer {

	private final AtomicLong count = new AtomicLong(0);

	@Override
	public long getCount() {
		return count.get();
	}

	@Override
	public List<BigInteger> factorize(final BigInteger number) {
		List<BigInteger> factorsList = factor(number);
		count.incrementAndGet();
		return factorsList;
	}
}
