package calculator;

import java.io.File;
import java.io.IOException;
import java.util.logging.*;

@SuppressWarnings("ALL")
public class LoggingUtil {
    // Create one global logger for all classes
    private static final Logger globalLogger = Logger.getLogger("GlobalCalculatorLogger");

    static {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) logDir.mkdir();

            FileHandler fileHandler = new FileHandler("logs/calculator.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            globalLogger.addHandler(fileHandler);
            globalLogger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Failed to initialize global logger: " + e.getMessage());
        }
    }

    /**
     * Instead of creating new loggers per class, return the global logger.
     */
    public static Logger getLogger(String className) {
        return globalLogger;
    }
}