/**
 * 
 */
package com.drusev.composingobjects.addingfunctionality;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class is not thread safe because it synchronized on the wrong lock.
 * PutIfAbsent synchronizes on the intrinsic lock of UnsafeListHelper whereas
 * the synchronized list synchronizes on its own intrinsic lock.
 * 
 * @author Drusev
 *
 */
public class UnsafeListHelper<E> {

	public List<E> list = Collections.synchronizedList(new ArrayList<>());

	public synchronized boolean putIfAbsent(final E x) {
		boolean absent = !list.contains(x);
		if (absent) {
			list.add(x);
		}
		return absent;
	}
}
