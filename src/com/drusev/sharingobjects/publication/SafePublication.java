/**
 * 
 */
package com.drusev.sharingobjects.publication;

import java.util.concurrent.atomic.AtomicReference;

/**
 * These are the four examples of safe publication of mutable objects. Putting
 * an object in a thread-safe collection also constitutes safe publishing.
 * 
 * @author Drusev
 *
 */
public class SafePublication {

	public static Holder holder1 = new Holder(8);

	public volatile Holder holder2;

	public AtomicReference<Holder> holder3;

	public final Holder holder4;

	private Object lock = new Object();

	private Holder holder5; // guarded by lock

	public void initializeHolder2() {
		holder2 = new Holder(34);
	}

	public void initializeHolder3() {
		holder3 = new AtomicReference<Holder>(new Holder(34));
	}

	public SafePublication() {
		this.holder4 = new Holder(54);
	}

	public void initializeHolder5() {
		synchronized (lock) {
			holder5 = new Holder(56);
		}
	}

	public Holder getHolder5() {
		synchronized (lock) {
			return holder5;
		}
	}

}
