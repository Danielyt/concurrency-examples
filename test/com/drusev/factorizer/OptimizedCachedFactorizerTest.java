/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.testing.threadtester.AnnotatedTestRunner;
import com.google.testing.threadtester.ThreadedAfter;
import com.google.testing.threadtester.ThreadedBefore;
import com.google.testing.threadtester.ThreadedMain;
import com.google.testing.threadtester.ThreadedSecondary;

/**
 * @author Drusev
 *
 */
public class OptimizedCachedFactorizerTest {

	private OptimizedCachedFactorizer factorizer;

	private final BigInteger number = BigInteger.valueOf(45);

	private final List<BigInteger> expectedFactors = Arrays.asList(BigInteger.valueOf(3), BigInteger.valueOf(3),
			BigInteger.valueOf(5));

	private List<BigInteger> actualFactorsFromMain;

	private List<BigInteger> actualFactorsFromSecondary;

	@ThreadedBefore
	public void before() {
		factorizer = new OptimizedCachedFactorizer();
	}

	@ThreadedMain
	public void mainThread() {
		actualFactorsFromMain = factorizer.factorize(number);
	}

	@ThreadedSecondary
	public void secondaryThread() {
		actualFactorsFromSecondary = factorizer.factorize(number);
	}

	@ThreadedAfter
	public void after() {
		assertEquals(expectedFactors, actualFactorsFromMain);
		assertEquals(expectedFactors, actualFactorsFromSecondary);
	}

	@Test
	public void testCaching() {
		AnnotatedTestRunner runner = new AnnotatedTestRunner();
		runner.runTests(this.getClass(), OptimizedCachedFactorizer.class);
	}

	@Test
	public void testPerformance() {
		final OptimizedCachedFactorizer factorizer = new OptimizedCachedFactorizer();
		CachedFactorizerPerformanceTest.testPerformance(factorizer, 10000000, 20, 1000000, 1500000);
		System.out.println("Cache hits ratio: " + factorizer.getCacheHitRatio());
	}
}
