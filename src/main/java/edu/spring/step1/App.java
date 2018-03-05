package edu.spring.step1;

import edu.spring.step1.beans.Client;
import edu.spring.step1.beans.Event;
import edu.spring.step1.beans.EventType;
import edu.spring.step1.loggers.EventLogger;
import edu.spring.step1.spring.AppConfig;
import edu.spring.step1.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class App {

    @Autowired
    private Client client;

    @Resource(name = "defaultLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    Map<EventType, EventLogger> loggers;

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("edu.spring.step1");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);

        System.out.println("Client says: " + client.getGreeting());


        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");


        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 12");
        ctx.close();

    }

    public App(){

    }

    App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
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
