package edu.spring.step1;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public static void main(String[] args) {

        @SuppressWarnings("resource")
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");


        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");


        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 12");
        ctx.close();

    }

    public App(Client client, EventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    private void logEvent(Event event,String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);
        eventLogger.logEvent(event);

    }
}
