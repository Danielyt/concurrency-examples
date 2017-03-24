/**
 * 
 */
package com.drusev.composingobjects.instanceconfinement;

import java.util.HashSet;
import java.util.Set;

/**
 * All code paths to the HashSet are the methods of the enclosing class, so even
 * though HashSet is not thread safe, PersonSet is. Notice that the field is
 * final to ensure safe publication.
 * 
 * @author Drusev
 *
 */
public class PersonSet {

	// Guarded by this
	private final Set<Person> mySet = new HashSet<>();

	public synchronized void addPerson(final Person person) {
		mySet.add(person);
	}

	public synchronized boolean containsPerson(final Person person) {
		return mySet.contains(person);
	}
}
