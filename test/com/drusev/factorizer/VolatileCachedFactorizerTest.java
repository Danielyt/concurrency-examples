/**
 * 
 */
package com.drusev.factorizer;

import org.junit.Test;

/**
 * @author Drusev
 *
 */
public class VolatileCachedFactorizerTest {

	@Test
	public void testPerformance() {
		CachedFactorizerPerformanceTest.testPerformance(new VolatileCachedFactorizer(), 10000000, 20, 1000000, 1500000);
	}
}
