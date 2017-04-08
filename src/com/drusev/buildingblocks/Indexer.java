/**
 * 
 */
package com.drusev.buildingblocks;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Drusev
 *
 */
public class Indexer implements Runnable {

	private final BlockingQueue<File> queue;
	private static final AtomicInteger count = new AtomicInteger(0);

	public Indexer(final BlockingQueue<File> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while (true) {
				indexFile(queue.take());
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void indexFile(final File file) {
		int count = Indexer.count.incrementAndGet();
		System.out.println(count + " " + file);
	}
}
