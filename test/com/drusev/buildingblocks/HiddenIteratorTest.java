/**
 * 
 */
package com.drusev.buildingblocks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

/**
 * @author Drusev
 *
 */
public class HiddenIteratorTest {

	private static final int TASK_NUMBER = 10000;
	private HiddenIterator iterator = new HiddenIterator();

	@Test
	public void testThreading() {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		CountDownLatch startSignal = new CountDownLatch(TASK_NUMBER);
		CountDownLatch endSignal = new CountDownLatch(TASK_NUMBER * 2);
		for (int i = 0; i < TASK_NUMBER; i++) {
			executor.submit(new Runnable() {

				@Override
				public void run() {
					try {
						startSignal.await();
						iterator.addTenThings();
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						endSignal.countDown();
					}
				}
			});
			executor.submit(new Runnable() {

				@Override
				public void run() {
					try {
						startSignal.await();
						iterator.remove(ThreadLocalRandom.current().nextInt(0, 1000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						endSignal.countDown();
					}
				}
			});
			startSignal.countDown();
		}
		try {
			endSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
