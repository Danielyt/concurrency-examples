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
public class SynchronizedFactorizerTest {

	private static final BigInteger number = BigInteger.valueOf(25);

	private static final List<BigInteger> expectedFactors = Arrays.asList(BigInteger.valueOf(5), BigInteger.valueOf(5));

	private static class MainTest extends MainRunnableImpl<SynchronizedFactorizer> {

		private SynchronizedFactorizer factorizer;

		private List<BigInteger> actualFactors;

		@Override
		public Class<SynchronizedFactorizer> getClassUnderTest() {
			return SynchronizedFactorizer.class;
		}

		@Override
		public String getMethodName() {
			return "factorize";
		}

		@Override
		public SynchronizedFactorizer getMainObject() {
			return factorizer;
		}

		@Override
		public void initialize() {
			factorizer = new SynchronizedFactorizer();
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

	private static class SecondaryTest extends SecondaryRunnableImpl<SynchronizedFactorizer, MainTest> {

		private SynchronizedFactorizer factorizer;

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

		ClassInstrumentation clazz = Instrumentation.getClassInstrumentation(SynchronizedFactorizer.class);
		CodePosition position = clazz.afterCall("factorize", "set");

		RunResult result = InterleavedRunner.interleaveAfter(main, secondary, position, 1);
		result.throwExceptionsIfAny();
	}

	@Test
	public void testCaching() {
		ThreadedTestRunner runner = new ThreadedTestRunner();
		runner.runTests(this.getClass(), SynchronizedFactorizer.class);
	}
}
