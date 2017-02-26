/**
 * 
 */
package com.drusev.sharingobjects;

/**
 * An example of using a factory method to prevent the {@code this} reference
 * from escaping during construction.
 * 
 * @author Drusev
 *
 */
public class SafeListener {

	private int a;	
	private EventListener listener;

	/**
	 * The constructor creates a new instance of EventListener and stores it in
	 * the class field, which is then used to pass the instance to an
	 * EventSource.
	 */
	private SafeListener() {
		this.listener = new EventListener() {			
			@Override
			public void onEvent(Event e) {
				doSomething();
			}
		};
		this.a = 5;
	}

	private void doSomething() {
		if (a != 5) {
			throw new AssertionError(a == 5);
		}
		System.out.println("Everything is OK.");
	}

	/**
	 * When the listener is registered in eventSource, we are sure the
	 * constructor has finished and the object is in a correct state.
	 * 
	 * @param eventSource
	 * @return the newly created instance
	 */
	public static SafeListener newInstance(final EventSource eventSource) {
		SafeListener safe = new SafeListener();
		eventSource.registerListener(safe.listener);
		return safe;
	}
}
