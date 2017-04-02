/**
 * 
 */
package com.drusev.composingobjects.addingfunctionality;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author Drusev
 *
 */
public class ImprovedList<E> implements List<E> {

	private final List<E> list;

	public ImprovedList(final List<E> list) {
		this.list = list;
	}

	public synchronized boolean putIfAbsennt(final E x) {
		boolean absent = !list.contains(x);
		if (absent) {
			list.add(x);
		}
		return absent;
	}

	@Override
	public synchronized int size() {
		return list.size();
	}

	@Override
	public synchronized boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public synchronized boolean contains(final Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return list.iterator();
	}

	@Override
	public synchronized Object[] toArray() {
		return list.toArray();
	}

	@Override
	public synchronized <T> T[] toArray(final T[] a) {
		return list.toArray(a);
	}

	@Override
	public synchronized boolean add(final E e) {
		return list.add(e);
	}

	@Override
	public synchronized boolean remove(final Object o) {
		return list.remove(o);
	}

	@Override
	public synchronized boolean containsAll(final Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public synchronized boolean addAll(final Collection<? extends E> c) {
		return list.addAll(c);
	}

	@Override
	public synchronized boolean addAll(final int index, final Collection<? extends E> c) {
		return list.addAll(index, c);
	}

	@Override
	public synchronized boolean removeAll(final Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public synchronized boolean retainAll(final Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public synchronized void clear() {
		list.clear();
	}

	@Override
	public synchronized E get(final int index) {
		return list.get(index);
	}

	@Override
	public synchronized E set(final int index, final E element) {
		return list.set(index, element);
	}

	@Override
	public synchronized void add(final int index, final E element) {
		list.add(index, element);
	}

	@Override
	public synchronized E remove(final int index) {
		return list.remove(index);
	}

	@Override
	public synchronized int indexOf(final Object o) {
		return list.indexOf(o);
	}

	@Override
	public synchronized int lastIndexOf(final Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(final int index) {
		return list.listIterator(index);
	}

	@Override
	public synchronized List<E> subList(final int fromIndex, final int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

	@Override
	public synchronized boolean equals(final Object o) {
		return list.equals(o);
	}

	@Override
	public synchronized int hashCode() {
		return list.hashCode();
	}

}
