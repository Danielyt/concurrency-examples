/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Drusev
 *
 */
public class CachedFactorizerPerformanceTest {

	/**
	 * Tests the performance of the specified factorizer by running it the
	 * specified number of times with the specified number of threads. Each run
	 * a randomly generated number within the given lower and upper bounds is
	 * factorized.
	 * 
	 * @param factorizer
	 * @param numberOfRuns
	 * @param numberOfThreads
	 * @param lowerBound
	 * @param upperBound
	 */
	public static void testPerformance(final Factorizer factorizer, final int numberOfRuns, final int numberOfThreads,
			final int lowerBound,
			final int upperBound) {
		ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
		CountDownLatch startSignal = new CountDownLatch(numberOfRuns);
		CountDownLatch endSignal = new CountDownLatch(numberOfRuns);

		executeTasks(factorizer, numberOfRuns, executor, startSignal, endSignal, lowerBound, upperBound);
		awaitExecutorToFinish(endSignal);
	}

	private static void executeTasks(final Factorizer factorizer, final int numberOfRuns,
			final ExecutorService executor, final CountDownLatch startSignal, final CountDownLatch endSignal,
			int lowerBound, int upperBound) {
		for (int i = 0; i < numberOfRuns; i++) {
			BigInteger randomNumber = BigInteger.valueOf(ThreadLocalRandom.current().nextLong(lowerBound, upperBound));
			executor.execute(new FactorizingRunnable(factorizer, startSignal, endSignal, randomNumber));
			startSignal.countDown();
		}
	}

	private static void awaitExecutorToFinish(final CountDownLatch endSignal) {
		try {
			endSignal.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class FactorizingRunnable implements Runnable {

		private final Factorizer factorizer;
		private final CountDownLatch startSignal;
		private final CountDownLatch endSignal;
		private final BigInteger number;

		private FactorizingRunnable(final Factorizer factorizer, final CountDownLatch startSignal,
				final CountDownLatch endSignal, final BigInteger number) {
			this.factorizer = factorizer;
			this.startSignal = startSignal;
			this.endSignal = endSignal;
			this.number = number;
		}

		@Override
		public void run() {
			try {
				startSignal.await();
				verifyFactors(factorizer.factorize(number));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				endSignal.countDown();
			}
		}

		private final void verifyFactors(final List<BigInteger> factors) {
			BigInteger expectedNumber = BigInteger.ONE;
			for (BigInteger factor : factors) {
				expectedNumber = expectedNumber.multiply(factor);
			}
			assertEquals(expectedNumber, number);
		}
	}
}
