package com.drusev.factorizer;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import com.google.testing.threadtester.SecondaryRunnableImpl;

class SecondaryFactorizerTest<T extends CountingFactorizer> extends SecondaryRunnableImpl<T, MainFactorizerTest<T>> {

	private T factorizer;

	private final BigInteger number;

	private long initialCount;

	SecondaryFactorizerTest(final BigInteger number) {
		this.number = number;
	}

	@Override
	public void initialize(final MainFactorizerTest<T> main) throws Exception {
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