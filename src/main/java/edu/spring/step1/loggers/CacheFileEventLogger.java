package edu.spring.step1.loggers;

import edu.spring.step1.beans.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Component
public class CacheFileEventLogger extends FileEventLogger {

    @Value("${cache.size:5}")
    private Integer cacheSize;

    private List<Event> cache;

    public CacheFileEventLogger(String logFilePath, Integer cacheSize) {
        super(logFilePath);
        this.cacheSize = cacheSize;
    }

    @PostConstruct
    public void initCache() {
        cache = new ArrayList<Event>(cacheSize);
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

    @PreDestroy
    private void destroy() {
        if (!cache.isEmpty()) {
            writeEventFromCache();
        }
    }

}
