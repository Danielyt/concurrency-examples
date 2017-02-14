/**
 * 
 */
package com.drusev.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Drusev
 *
 */
public class ConcurrentAccessToUnsynchronizedList {

	public static void main(String[] args) {
		List<Integer> list = Collections.synchronizedList(new ArrayList<>());
		ExecutorService executor = Executors.newFixedThreadPool(2);
		CountDownLatch startSignal = new CountDownLatch(1);
		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					startSignal.await();
					for (int i = 0; i < 1000000; i++) {
						list.add(ThreadLocalRandom.current().nextInt(0, 100));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end of filling");
			}
		});

		executor.execute(new Runnable() {

			@Override
			public void run() {
				try {
					startSignal.await();
					for (int i = 0; i < 10000; i++) {
						int total = 0;
						synchronized (list) {
						//synchronized (this) {
							for (Integer number : list) {
								total += number;
							}
						}
						System.out.println(total);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end of printing");
			}
		});

		startSignal.countDown();
		System.out.println("end of main");
		executor.shutdown();
	}
}
