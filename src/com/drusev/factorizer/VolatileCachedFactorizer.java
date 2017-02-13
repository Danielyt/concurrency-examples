/**
 * 
 */
package com.drusev.factorizer;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Drusev
 *
 */
public class VolatileCachedFactorizer extends Factorizer {

	private volatile OneValueCache cache = new OneValueCache(null, null);

	@Override
	public List<BigInteger> factorize(final BigInteger number) {
		List<BigInteger> factors = cache.getFactors(number);
		if (null == factors) {
			factors = factor(number);
			cache = new OneValueCache(number, factors);
		}
		return factors;
	}
}
