/**
 * 
 */
package com.drusev.sharingobjects;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * @author Drusev
 *
 */
public class MutableIntegerTest {

	@Test
	public void test() throws InterruptedException {
		ExecutorService executor = Executors.newFixedThreadPool(20);
		UnsafeMutableInteger integer = new UnsafeMutableInteger();
		List<Integer> expectedValues = Collections.synchronizedList(new ArrayList<>());
		List<Integer> returnedValues = Collections.synchronizedList(new ArrayList<>());
		for (int i = 0; i < 100; i++) {
			int value = ThreadLocalRandom.current().nextInt(1, 10);
			expectedValues.add(value);
			CountDownLatch signal = new CountDownLatch(1);
			executor.execute(new Runnable() {
				@Override
				public void run() {
					integer.set(value);
					signal.countDown();
				}
			});

			executor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						signal.await();
						returnedValues.add(integer.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executor.awaitTermination(2, TimeUnit.SECONDS);
		assertEquals(expectedValues.size(), returnedValues.size());
		assertEquals(expectedValues, returnedValues);
	}
}
