package edu.spring.step1.event.logger;

import edu.spring.step1.Event;
import edu.spring.step1.EventLogger;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger{
    private final Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        super();
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger: loggers) {
            eventLogger.logEvent(event);
        }
    }
}
