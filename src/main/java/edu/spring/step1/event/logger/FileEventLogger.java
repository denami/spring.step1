package edu.spring.step1.event.logger;

import edu.spring.step1.EventLogger;
import edu.spring.step1.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    String logFilePath;
    File logFile;

    public FileEventLogger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

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
            FileUtils.writeStringToFile(logFile, event.toString(), true);
            FileUtils.writeStringToFile(logFile, "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
