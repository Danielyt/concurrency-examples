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
public class UnsafeCountingFactorizer extends CountingFactorizer {
	
	private long count = 0;
	
	@Override
	public long getCount() {
		return count;
	}
	
	@Override
	public List<BigInteger> factorize (final BigInteger number) {
		List<BigInteger> factorsList = factor(number);
		long initialValue = count;
		count = initialValue + 1;
		return factorsList;
	}
}
