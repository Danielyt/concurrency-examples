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
public class UnsafeCountingFactorizer {
	
	private long count = 0;
	
	public long getCount() {
		return count;
	}
	
	public List<BigInteger> factorize (final BigInteger number) {
		List<BigInteger> factorsList = factor(number);
		long initialValue = count;
		count = initialValue + 1;
		return factorsList;
	}
	
	private List<BigInteger> factor(final BigInteger number) {
		int n = number.intValue();
		List<BigInteger> factorList = new ArrayList<>();
		for (int i = 2; i <= n / i; i++) {
			while (n % i == 0) {
				factorList.add(BigInteger.valueOf(i));
				n /= i;				
			}
		}
		
		if (n > 1) {
			factorList.add(BigInteger.valueOf(n));
		}
		return factorList;
	}

}
