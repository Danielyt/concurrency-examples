/**
 * 
 */
package com.drusev.factorizer;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Drusev
 *
 */
public class OptimizedCachedFactorizer extends Factorizer {

	// guarded by this
	private BigInteger lastNumber;
	// guarded by this
	private List<BigInteger> lastFactors;
	// guarded by this
	private long hits;
	// guared by this
	private long cacheHits;

	public synchronized long getHits() {
		return hits;
	}

	public synchronized double getCacheHitRatio() {
		return (double) cacheHits / (double) hits;
	}

	@Override
	public List<BigInteger> factorize(BigInteger number) {
		List<BigInteger> factors = null;
		synchronized (this) {
			++hits;
			if (number.equals(lastNumber)) {
				++cacheHits;
				factors = new ArrayList<>(lastFactors);
			}
		}
		if (null == factors) {
			factors = factor(number);
			synchronized (this) {
				lastNumber = number;
				lastFactors = new ArrayList<>(factors);
			}
		}
		return factors;
	}
}
