/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Ignore;
import org.junit.Test;

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
public class UnsafeCountingFactorizerTest {
	
	private static class MainTest extends MainRunnableImpl<UnsafeCountingFactorizer> {

		private UnsafeCountingFactorizer factorizer;
		
		private final BigInteger number;
		
		private long initialCount;

		private MainTest(final BigInteger number) {
			this.number = number;
		}

		@Override
		public Class<UnsafeCountingFactorizer> getClassUnderTest() {
			return UnsafeCountingFactorizer.class;
		}

		@Override
		public String getMethodName() {
			return "factorize";
		}

		@Override
		public void initialize() throws Exception {
			factorizer = new UnsafeCountingFactorizer();
		}
		
		@Override
		public UnsafeCountingFactorizer getMainObject() {
			return factorizer;
		}

		@Override
		public void run() throws Exception {
			initialCount = factorizer.getCount();
			factorizer.factorize(number);			
		}

		@Override
		public void terminate() throws Exception {
			long endCount = factorizer.getCount();
			assertTrue(endCount > initialCount && endCount <= initialCount + 2);
		}		
	}

	private static class SecondaryTest extends SecondaryRunnableImpl<UnsafeCountingFactorizer, MainTest> {

		private UnsafeCountingFactorizer factorizer;

		private final BigInteger number;

		private long initialCount;

		private SecondaryTest(final BigInteger number) {
			this.number = number;
		}

		@Override
		public void initialize(final MainTest main) throws Exception {
			factorizer = main.factorizer;
		}

		@Override
		public void run() throws Exception {
			initialCount = factorizer.getCount();
			factorizer.factorize(number);
		}

		@Override
		public void terminate() throws Exception {
			long endCount = factorizer.getCount();
			assertEquals(initialCount + 2, endCount);
		}
	}

	@ThreadedTest
	public void testFactorizeCount() throws Throwable {
		MainTest main = new MainTest(BigInteger.valueOf(52));
		SecondaryTest secondary = new SecondaryTest(BigInteger.valueOf(64));

		RunResult result = InterleavedRunner.interleave(main, secondary);

		result.throwExceptionsIfAny();
		assertEquals(2, main.factorizer.getCount());
	}

	@Test
	@Ignore
	public void testThreading() {
		ThreadedTestRunner runner = new ThreadedTestRunner();
		runner.runTests(this.getClass(), UnsafeCountingFactorizer.class);
	}
}
