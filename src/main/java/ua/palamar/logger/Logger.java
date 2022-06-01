package ua.palamar.logger;

import ua.palamar.property.Property;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Logger {
    private int modCount;
    private static Logger logger;
    private static final String logPath = Property
            .getInstance()
            .getProperties()
            .getProperty("default.logger.path");

    private Logger() {}

    public static Logger getInstance() {
        if (logger == null) {
            logger = new Logger();
        }

        if (!loggerFileExists()) {
            createNewLoggerFile();
        }

        return logger;
    }

    public boolean isEmpty() {
        return modCount == 0;
    }

    public static String getLogFilePath() {
        return logPath;
    }

    public void log(String message) {
        try (FileWriter writer = new FileWriter(logPath, true)) {
            writer.write(String.format("[%s] %s\n", LocalDateTime.now(), message));
            this.modCount++;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean loggerFileExists() {
        return new File(logPath).exists();
    }

    private static boolean createNewLoggerFile() {
        boolean created = false;
        File file = new File(logPath);
        if (!file.exists()) {
            try {
                created = file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return created;
    }
}
