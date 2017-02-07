/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

import com.google.testing.threadtester.InterleavedRunner;
import com.google.testing.threadtester.RunResult;
import com.google.testing.threadtester.ThreadedTest;
import com.google.testing.threadtester.ThreadedTestRunner;

/**
 * @author Drusev
 *
 */
public class UnsafeCountingFactorizerTest {
	
	private static class MainTest extends MainFactorizerTest<UnsafeCountingFactorizer> {

		MainTest(final BigInteger number) {
			super(number);
		}

		@Override
		public Class<UnsafeCountingFactorizer> getClassUnderTest() {
			return UnsafeCountingFactorizer.class;
		}

		@Override
		public void initialize() throws Exception {
			factorizer = new UnsafeCountingFactorizer();
		}
	}

	@ThreadedTest
	public void testFactorizeCount() throws Throwable {
		MainTest main = new MainTest(BigInteger.valueOf(52));
		SecondaryFactorizerTest<UnsafeCountingFactorizer> secondary = new SecondaryFactorizerTest<>(
				BigInteger.valueOf(64));

		RunResult result = InterleavedRunner.interleave(main, secondary);

		result.throwExceptionsIfAny();
		assertEquals(2, main.factorizer.getCount());
	}

	@Test
	public void testThreading() {
		ThreadedTestRunner runner = new ThreadedTestRunner();
		runner.runTests(this.getClass(), UnsafeCountingFactorizer.class);
	}
}
