package edu.spring.step1.loggers;

import edu.spring.step1.beans.Event;
import org.springframework.stereotype.Component;

@Component
public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {

        System.out.println(event.toString());

    }
}
