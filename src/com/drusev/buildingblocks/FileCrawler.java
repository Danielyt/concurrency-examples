/**
 * 
 */
package com.drusev.buildingblocks;

import java.io.File;
import java.io.FileFilter;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

/**
 * @author Drusev
 *
 */
public class FileCrawler implements Runnable {

	private final BlockingQueue<File> fileQueue;
	private final FileFilter fileFilter;
	private final File root;
	// guareded by lock
	private final static Set<File> indexedFiles = new HashSet<>();
	private final static Object lock = new Object();

	public FileCrawler(final BlockingQueue<File> fileQueue, final FileFilter fileFilter, final File root) {
		this.fileQueue = fileQueue;
		this.fileFilter = fileFilter;
		this.root = root;
	}

	@Override
	public void run() {
		try {
			crawl(root);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void crawl(final File root) throws InterruptedException {
		File[] entries = root.listFiles(fileFilter);
		if (entries != null) {
			for (File entry : entries) {
				if (entry.isDirectory()) {
					crawl(entry);
				} else if (!alreadyIndexed(entry)) {
					fileQueue.put(entry);
				}
			}
		}
	}

	private boolean alreadyIndexed(final File entry) {
		synchronized (lock) {
			if (indexedFiles.contains(entry)) {
				return true;
			} else {
				indexedFiles.add(entry);
				return false;
			}
		}
	}
}
