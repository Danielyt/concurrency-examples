/**
 * 
 */
package com.drusev.factorizer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Drusev
 *
 */
public class FactorizerTest {

	private Factorizer factorizer;
	private List<BigInteger> expectedFactorList;

	@Before
	public void setUp() {
		factorizer = new Factorizer();
		expectedFactorList = new ArrayList<>();
	}

	@Test
	public void testFactorizationOf2() {
		expectedFactorList.add(BigInteger.valueOf(2));

		List<BigInteger> returnedFactorList = factorizer.factorize(BigInteger.valueOf(2));

		verifyReturnedFactorList(returnedFactorList);
	}

	@Test
	public void testFactorizationOf8() {
		expectedFactorList.add(BigInteger.valueOf(2));
		expectedFactorList.add(BigInteger.valueOf(2));
		expectedFactorList.add(BigInteger.valueOf(2));
		
		List<BigInteger> returnedFactorList = factorizer.factorize(BigInteger.valueOf(8));
		
		verifyReturnedFactorList(returnedFactorList);
	}
	
	@Test
	public void testFactorizationOf50() {
		expectedFactorList.add(BigInteger.valueOf(2));
		expectedFactorList.add(BigInteger.valueOf(5));
		expectedFactorList.add(BigInteger.valueOf(5));
		
		List<BigInteger> returnedFactorList = factorizer.factorize(BigInteger.valueOf(50));
		
		verifyReturnedFactorList(returnedFactorList);
	}
	
	private void verifyReturnedFactorList(List<BigInteger> returnedFactorList) {
		assertNotNull(returnedFactorList);
		assertTrue(returnedFactorList.containsAll(expectedFactorList));
		assertTrue(expectedFactorList.containsAll(returnedFactorList));
	}

}
