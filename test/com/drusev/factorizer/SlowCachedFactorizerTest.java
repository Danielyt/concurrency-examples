/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.testing.threadtester.ClassInstrumentation;
import com.google.testing.threadtester.CodePosition;
import com.google.testing.threadtester.Instrumentation;
import com.google.testing.threadtester.InterleavedRunner;
import com.google.testing.threadtester.MainRunnableImpl;
import com.google.testing.threadtester.RunResult;
import com.google.testing.threadtester.SecondaryRunnableImpl;
import com.google.testing.threadtester.ThreadedTest;
import com.google.testing.threadtester.ThreadedTestRunner;

/**
 * @author Drusev
 *
 */
public class SlowCachedFactorizerTest {

	private static final BigInteger number = BigInteger.valueOf(25);

	private static final List<BigInteger> expectedFactors = Arrays.asList(BigInteger.valueOf(5), BigInteger.valueOf(5));

	private static class MainTest extends MainRunnableImpl<SlowCachedFactorizer> {

		private SlowCachedFactorizer factorizer;

		private List<BigInteger> actualFactors;

		@Override
		public Class<SlowCachedFactorizer> getClassUnderTest() {
			return SlowCachedFactorizer.class;
		}

		@Override
		public String getMethodName() {
			return "factorize";
		}

		@Override
		public SlowCachedFactorizer getMainObject() {
			return factorizer;
		}

		@Override
		public void initialize() {
			factorizer = new SlowCachedFactorizer();
		}

		@Override
		public void run() throws Exception {
			actualFactors = factorizer.factorize(number);
		}

		@Override
		public void terminate() throws Exception {
			assertEquals(expectedFactors, actualFactors);
		}
	}

	private static class SecondaryTest extends SecondaryRunnableImpl<SlowCachedFactorizer, MainTest> {

		private SlowCachedFactorizer factorizer;

		private List<BigInteger> actualFactors;

		@Override
		public void initialize(final MainTest main) throws Exception {
			factorizer = main.factorizer;
		}

		@Override
		public void run() throws Exception {
			actualFactors = factorizer.factorize(number);
		}

		@Override
		public void terminate() throws Exception {
			assertEquals(expectedFactors, actualFactors);
		}
	}

	@ThreadedTest
	public void testFactorizing() {
		MainTest main = new MainTest();
		SecondaryTest secondary = new SecondaryTest();

		ClassInstrumentation clazz = Instrumentation.getClassInstrumentation(SlowCachedFactorizer.class);
		CodePosition position = clazz.afterCall("factorize", "set");

		RunResult result = InterleavedRunner.interleaveAfter(main, secondary, position, 1);
		result.throwExceptionsIfAny();
	}

	@Test
	public void testCaching() {
		ThreadedTestRunner runner = new ThreadedTestRunner();
		runner.runTests(this.getClass(), SlowCachedFactorizer.class);
	}

	@Test
	public void testPerformance() {
		CachedFactorizerPerformanceTest.testPerformance(new SlowCachedFactorizer(), 10000000, 20, 1000000, 10000000);
	}
}
