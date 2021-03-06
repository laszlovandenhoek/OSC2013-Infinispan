package org.jboss.ddoyle.osc2013.infinispan.demo.client;

import java.io.Console;
import java.util.Set;
import java.util.UUID;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RemoteCacheClient {

	/**
	 * Logger.
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RemoteCacheClient.class);

	private RemoteCacheManager cacheManager;

	public RemoteCacheClient() {
		LOGGER.info("Initializing HotRod Cache Manager.");
		cacheManager = new RemoteCacheManager();
	}

	public static void main(String[] args) {
		LOGGER.info("Initializing RemoteCacheClient.");
		RemoteCacheClient client = new RemoteCacheClient();
		
		Console console = System.console();
		String inputString = null;
		while (!"quit".equals(inputString)) {
			System.out.println("Please select one of these options:");
			System.out.println("- populate (populates the cache with random values");
			System.out.println("- put (put a key-value pair in the cache)");
			System.out.println("- quit (exit this application)");
			inputString = console.readLine();
			if ("populate".equals(inputString)) {
				client.populateCache();
			} else if ("put".equals(inputString)) {
				System.out.println("Enter key:");
				String key = System.console().readLine();
				System.out.println("Enter value:");
				String value = System.console().readLine();
				client.put(key, value);
			} else if ("quit".equals(inputString)) {
				LOGGER.info("Shutting down RemoteCacheClient.");
			} else {
				System.out.println("Unrecognized option: '" + inputString + "'.");
			}
		}
	}

	private void populateCache() {
		LOGGER.info("Populating cache with random values.");
		RemoteCache<String, String> remoteCache = cacheManager.getCache("MyCoolCache");
		int numberOfEntries = 10;
		for (int counter = 0; counter < numberOfEntries; counter++) {
			String uuid = UUID.randomUUID().toString();
			remoteCache.put(uuid, "My data object for uuid: " + uuid);
		}
	}
	
	private void put(String key, String value) {
		LOGGER.info("Storing key:value pair '" + key + ":" + value + "' in the cache.");
		RemoteCache<String, String> remoteCache = cacheManager.getCache("MyCoolCache");
		remoteCache.put(key, value);
	}

	/* Does not work in DIST Cache with 'compatibility-mode enabled, See ISPN-3785.
	private int getNumberOfEntriesInCache() {
		RemoteCache<String, String> remoteCache = cacheManager.getCache("MyCoolCache");
		long startTime = System.currentTimeMillis();
		Set<String> keySet = remoteCache.keySet();
		System.out.println("Got remote keyset in: " + (System.currentTimeMillis() - startTime));
		return keySet.size();
	}
	*/
}
