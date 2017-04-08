/**
 * 
 */
package com.drusev.buildingblocks;

import java.util.Vector;

/**
 * @author Drusev
 *
 */
public class ClientSideLocking {

	public static Object getLast(final Vector<?> vector) {
		synchronized (vector) {
			int lastIndex = vector.size() - 1;
			return vector.get(lastIndex);
		}
	}

	public static void deleteLast(final Vector<?> vector) {
		synchronized (vector) {
			int lastIndex = vector.size() - 1;
			vector.remove(lastIndex);
		}
	}

	public static void iterate(final Vector<?> vector, final Operation operation) {
		synchronized (vector) {
			for (int i = 0; i < vector.size(); i++) {
				operation.doSomething(vector.get(i));
			}
		}
	}

	public static interface Operation {
		void doSomething(Object o);
	}

}
