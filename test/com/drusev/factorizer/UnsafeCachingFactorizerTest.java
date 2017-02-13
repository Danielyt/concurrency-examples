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
public class UnsafeCachingFactorizerTest {

	private UnsafeCachingFactorizer factorizer;

	private BigInteger number = BigInteger.valueOf(25);

	private List<BigInteger> expectedFactors = Arrays.asList(BigInteger.valueOf(5), BigInteger.valueOf(5));

	private List<BigInteger> actualFactorsFromMain;

	private List<BigInteger> actualFactorsFromSecond;

	@ThreadedBefore
	public void before() {
		factorizer = new UnsafeCachingFactorizer();
	}

	@ThreadedMain
	public void mainThread() {
		actualFactorsFromMain = factorizer.factorize(number);
	}

	@ThreadedSecondary
	public void secondThread() {
		actualFactorsFromSecond = factorizer.factorize(number);
	}

	@ThreadedAfter
	public void after() {
		assertEquals(expectedFactors, actualFactorsFromMain);
		assertEquals(expectedFactors, actualFactorsFromSecond);
	}

	@Test
	public void testThreading() {
		AnnotatedTestRunner runner = new AnnotatedTestRunner();
		runner.runTests(this.getClass(), UnsafeCachingFactorizer.class);
	}

	@Test
	public void testPerformance() {
		CachedFactorizerPerformanceTest.testPerformance(new UnsafeCachingFactorizer(), 10000000, 20, 1000000, 1500000);
	}
}
