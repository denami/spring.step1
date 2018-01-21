package edu.spring.step1;

public class App {
    Client client;
    EventLogger eventLogger;

    public static void main(String[] args) {
        App app = new App();
        app.client = new Client( "12","Jon");
        app.eventLogger  = new ConsoleEventLogger();

        app.logEvent("Example event for user 12");
    }

    private void logEvent(String msg) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        eventLogger.logEvent(message);
    }
}
