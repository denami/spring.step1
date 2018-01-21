package edu.spring.step1;

public class ConsoleEventLogger implements EventLogger{

    @Override
    public void logEvent(String msg) {

        System.out.println(msg);

    }
}
