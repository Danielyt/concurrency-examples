package com.drusev.factorizer;

import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import com.google.testing.threadtester.MainRunnableImpl;

abstract class MainFactorizerTest<T extends CountingFactorizer> extends MainRunnableImpl<T> {

	T factorizer;
	
	private final BigInteger number;
	
	private long initialCount;

	MainFactorizerTest(final BigInteger number) {
		this.number = number;
	}

	@Override
	public String getMethodName() {
		return "factorize";
	}
	
	@Override
	public T getMainObject() {
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