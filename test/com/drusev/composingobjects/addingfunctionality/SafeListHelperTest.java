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
public class SafeListHelperTest {

	private SafeListHelper<String> list;

	@ThreadedBefore
	public void before() {
		list = new SafeListHelper<>();
	}

	@ThreadedMain
	public void mainThread() {
		list.putIfAbsent("string");
	}

	@ThreadedSecondary
	public void secondThread() {
		if (!list.list.contains("string")) {
			list.list.add("string");
		}
	}

	@ThreadedAfter
	public void after() {
		assertTrue(list.list.contains("string"));
		list.list.remove("string");
		assertFalse(list.list.contains("string"));
	}

	@Test
	public void testThreading() {
		AnnotatedTestRunner runner = new AnnotatedTestRunner();
		runner.runTests(this.getClass(), SafeListHelper.class);
	}
}
