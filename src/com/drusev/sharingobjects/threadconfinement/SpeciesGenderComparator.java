/**
 * 
 */
package com.drusev.sharingobjects.threadconfinement;

import java.util.Comparator;

/**
 * @author Drusev
 *
 */
public class SpeciesGenderComparator implements Comparator<Animal> {

	@Override
	public int compare(final Animal animal1, final Animal animal2) {
		return animal1.getSpecies().compareTo(animal2.getSpecies());
	}
}
