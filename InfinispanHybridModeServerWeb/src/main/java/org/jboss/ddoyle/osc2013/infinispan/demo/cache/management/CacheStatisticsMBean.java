package org.jboss.ddoyle.osc2013.infinispan.demo.cache.management;

import java.util.Map;

/**
 * Custom JBoss DataGrid <code>MBean</code> which provides the number of entries in the entire JDG grid (including entries which have been
 * evicted to disk).
 * 
 * @author <a href="mailto:duncan.doyle@redhat.com">Duncan Doyle</a>
 */
public interface CacheStatisticsMBean {

	public abstract int numberOfEntries();
	
	public abstract Map<Object, String> cacheEntriesInfo();

}
