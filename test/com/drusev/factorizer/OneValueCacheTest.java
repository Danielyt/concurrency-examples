/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Drusev
 *
 */
public class OneValueCacheTest {

	@Test
	public void test() {
		BigInteger lastNumber = BigInteger.valueOf(4);
		List<BigInteger> lastFactors = new ArrayList<>();
		lastFactors.add(BigInteger.valueOf(2));
		lastFactors.add(BigInteger.valueOf(2));

		OneValueCache cache = new OneValueCache(lastNumber, lastFactors);

		lastFactors.add(BigInteger.valueOf(3));

		List<BigInteger> returnedLastFactors = cache.getFactors(lastNumber);

		BigInteger productOfLastFactors = BigInteger.ONE;
		for (BigInteger factor : returnedLastFactors) {
			productOfLastFactors = productOfLastFactors.multiply(factor);
		}
		assertEquals(lastNumber, productOfLastFactors);
	}
}
