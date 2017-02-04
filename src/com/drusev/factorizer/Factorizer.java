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
public class Factorizer {
	
	public List<BigInteger> factorize (final BigInteger number) {
		return factor(number);
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
