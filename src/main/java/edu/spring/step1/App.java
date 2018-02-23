package edu.spring.step1;

import edu.spring.step1.event.logger.EventType;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    Map<EventType, EventLogger> loggers;

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");


        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 12");
        ctx.close();

    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    private void logEvent(EventType type, Event event, String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = defaultLogger;
        }
        logger.logEvent(event);

    }
}
