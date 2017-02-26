/**
 * 
 */
package com.drusev.sharingobjects.publication;

/**
 * An example of how the {@code this} reference can escape and a client of the
 * class can receive an improperly constructed object.
 * 
 * @author Drusev
 *
 */
public class UnsafeListener {

	private int a;

	public UnsafeListener(final EventSource source) {
		source.registerListener(new EventListener() {	
			@Override
			public void onEvent(final Event e) {
				doSomething();
			}
		});
		this.a = 5;
	}

	private void doSomething() {
		if (5 != a) {
			throw new AssertionError(5 == a);
		}
		System.out.println("Everything is OK");
	}
}
