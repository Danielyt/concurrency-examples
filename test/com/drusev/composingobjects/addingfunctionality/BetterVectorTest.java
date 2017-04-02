/**
 * 
 */
package com.drusev.composingobjects.addingfunctionality;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class BetterVectorTest {

	private BetterVector<String> vector;

	@ThreadedBefore
	public void before() {
		vector = new BetterVector<>();
	}

	@ThreadedMain
	public void mainThread() {
		vector.putIfAbsent("string");
	}

	@ThreadedSecondary
	public void secondThread() {
		vector.putIfAbsent("string");
	}

	@ThreadedAfter
	public void after() {
		assertTrue(vector.contains("string"));
		vector.remove("string");
		assertFalse(vector.contains("string"));
	}

	@Test
	public void testThreading() {
		AnnotatedTestRunner testRunner = new AnnotatedTestRunner();
		testRunner.runTests(this.getClass(), BetterVector.class);
	}
}
