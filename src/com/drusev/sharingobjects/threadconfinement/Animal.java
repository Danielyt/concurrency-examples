/**
 * 
 */
package com.drusev.sharingobjects.threadconfinement;

/**
 * @author Drusev
 *
 */
public interface Animal {

	Gender getGender();

	String getSpecies();

	boolean isPotentialMate(Animal animal);
}
