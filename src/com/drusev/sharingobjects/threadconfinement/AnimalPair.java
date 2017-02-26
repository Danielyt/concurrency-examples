/**
 * 
 */
package com.drusev.sharingobjects.threadconfinement;

/**
 * @author Drusev
 *
 */
public class AnimalPair {

	private final Animal a1;
	private final Animal a2;

	public AnimalPair(final Animal a1, final Animal a2) {
		this.a1 = a1;
		this.a2 = a2;
	}

	public Animal getA1() {
		return a1;
	}

	public Animal getA2() {
		return a2;
	}

}
