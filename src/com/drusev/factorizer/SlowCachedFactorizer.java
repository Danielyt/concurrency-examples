/**
 * 
 */
package com.drusev.factorizer;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Drusev
 *
 */
public class SlowCachedFactorizer extends Factorizer {

	private final AtomicReference<BigInteger> lastNumber = new AtomicReference<>();

	private final AtomicReference<List<BigInteger>> lastFactors = new AtomicReference<>();

	@Override
	public synchronized List<BigInteger> factorize(final BigInteger number) {
		if (number.equals(lastNumber.get())) {
			return lastFactors.get();
		} else {
			List<BigInteger> factors = factor(number);
			lastNumber.set(number);
			lastFactors.set(factors);
			return factors;
		}
	}
}
