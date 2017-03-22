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
public class OneValueCache {

	private final BigInteger lastNumber;
	private final List<BigInteger> lastFactors;

	public OneValueCache(final BigInteger lastNumber, final List<BigInteger> lastFactors) {
		this.lastNumber = lastNumber;
		this.lastFactors = null != lastFactors ? new ArrayList<>(lastFactors) : null;
	}

	public List<BigInteger> getFactors(final BigInteger number) {
		if (null == lastNumber || !lastNumber.equals(number)) {
			return null;
		} else {
			return new ArrayList<>(lastFactors);
		}
	}
}
