/**
 * 
 */
package com.drusev.composingobjects.delegatingthreadsafety;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * There is no invariant involving the two lists of listeners, so the class can
 * safely delegate its thread safety to them
 * 
 * @author Drusev
 *
 */
public class VisualComponent {

	private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
	private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

	public void addKeyListener(final KeyListener listener) {
		keyListeners.add(listener);
	}

	public void addMouseListener(final MouseListener listener) {
		mouseListeners.add(listener);
	}

	public void removeKeyListener(final KeyListener listener) {
		keyListeners.remove(listener);
	}

	public void removeMouseListener(final MouseListener listener) {
		mouseListeners.remove(listener);
	}
}
