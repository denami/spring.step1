package edu.spring.step1.loggers;

import edu.spring.step1.beans.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {

    @Value("${events.file:target/events_log.txt}")
    private String logFilePath;
    private File logFile;

    public FileEventLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @PostConstruct
    private void init() {
        logFile = new File(logFilePath);

        if (logFile.exists() && !logFile.canWrite()) {
            throw new IllegalArgumentException("Can't write to file");
        } else if (!logFile.exists()){
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                throw new IllegalArgumentException("Can't create new file");
            }
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(logFile, event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
