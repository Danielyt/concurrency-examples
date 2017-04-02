/**
 * 
 */
package com.drusev.composingobjects.addingfunctionality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Example of client-side locking, the client of the synchronized list (this
 * class is the client) uses the same lock. This is a fragile approach because
 * the client class depends on the internal implementation of the class it is a
 * client of.
 * 
 * @author Drusev
 *
 */
public class SafeListHelper<E> {

	public List<E> list = Collections.synchronizedList(new ArrayList<>());

	public boolean putIfAbsent(final E x) {
		synchronized (list) {
			boolean absent = !list.contains(x);
			if (absent) {
				list.add(x);
			}
			return absent;
		}
	}

}
