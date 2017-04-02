/**
 * 
 */
package com.drusev.composingobjects.addingfunctionality;

import java.util.Vector;

/**
 * @author Drusev
 *
 */
public class BetterVector<E> extends Vector<E> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2286079430727260190L;

	public synchronized boolean putIfAbsent(E x) {
		boolean absent = !contains(x);
		if (absent) {
			add(x);
		}
		return absent;
	}

}
