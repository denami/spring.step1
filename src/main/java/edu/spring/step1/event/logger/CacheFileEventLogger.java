package edu.spring.step1.event.logger;

import edu.spring.step1.Event;

import java.util.LinkedList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {
    private Integer cacheSize;
    private List<Event> cache;

    public CacheFileEventLogger(String logFilePath, Integer cacheSize) {
        super(logFilePath);
        this.cacheSize = cacheSize;
        cache = new LinkedList<Event>();
    }

    @Override
    public void logEvent(Event event) {
        cache.add(event);

        if (cache.size() >= cacheSize) {
            writeEventFromCache();
            cache.clear();
        }
    }

    private void writeEventFromCache() {
        for ( Event event  : cache) {
            super.logEvent(event);
        }
    }

    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventFromCache();
        }
    }

}
