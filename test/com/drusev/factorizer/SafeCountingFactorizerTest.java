/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

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
public class SafeCountingFactorizerTest {

	private SafeCountingFactorizer factorizer;

	@ThreadedBefore
	public void before() {
		factorizer = new SafeCountingFactorizer();
	}

	@ThreadedMain
	public void mainThread() {
		factorizer.factorize(BigInteger.valueOf(125));
	}

	@ThreadedSecondary
	public void secondaryThread() {
		factorizer.factorize(BigInteger.valueOf(133));
	}

	@ThreadedAfter
	public void after() {
		assertEquals(2, factorizer.getCount());
	}

	@Test
	public void testThreading() {
		AnnotatedTestRunner runner = new AnnotatedTestRunner();
		runner.runTests(this.getClass(), SafeCountingFactorizer.class);
	}
}
