/**
 * 
 */
package com.drusev.buildingblocks;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Drusev
 *
 */
public class ConsumerProducerMainApp {

	private static final int BOUND = 10;
	private static final int N_CONSUMERS = 2;

	public static void main(final String[] args) {
		List<File> roots = new ArrayList<>();
		roots.add(new File("D:\\"));
		startIndexing(roots);
	}

	private static void startIndexing(final List<File> roots) {
		BlockingQueue<File> queue = new LinkedBlockingQueue<File>(BOUND);
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(final File file) {
				return file.getName().contains(".txt");
			}
		};

		for (File root : roots) {
			new Thread(new FileCrawler(queue, filter, root)).start();
		}

		for (int i = 0; i < N_CONSUMERS; i++)
			new Thread(new Indexer(queue)).start();
	}

}
