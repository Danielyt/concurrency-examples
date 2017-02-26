/**
 * 
 */
package com.drusev.sharingobjects.threadconfinement;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * An example of confining state to the thread's stack by using local variables.
 * 
 * @author Drusev
 *
 */
public class StackConfinement {

	private Ark ark;

	/**
	 * The caller of this method must ensure that the passed collection of
	 * animals is not modified while this method executes.
	 * 
	 * @param candidates
	 *            potential candidates for loading
	 * @return the number of animals loaded
	 */
	public int loadTheArk(final Collection<Animal> candidates) {
		SortedSet<Animal> animals;
		int numPairs = 0;
		Animal candidate = null;

		// animals confined to method, don't let them escape
		animals = new TreeSet<>(new SpeciesGenderComparator());
		animals.addAll(candidates);
		for (Animal a : animals) {
			if (candidate == null || !candidate.isPotentialMate(a)) {
				candidate = a;
			} else {
				ark.load(new AnimalPair(candidate, a));
				++numPairs;
				candidate = null;
			}
		}
		return numPairs;
	}
}
