package calculator;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingUtil {
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Logger getLogger(String className) {
        Logger logger = Logger.getLogger(className);
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) logDir.mkdir();

            FileHandler fileHandler = new FileHandler("logs/calculator.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Prevent logging to console
        } catch (IOException e) {
            System.err.println("Error initializing logger: " + e.getMessage());
        }
        return logger;
    }
}