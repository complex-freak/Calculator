package calculator;

import javafx.scene.control.Alert;

import java.util.logging.Logger;

public class ErrorHandler {
    public static void handleError(String message, Exception e) {
        Logger logger = LoggingUtil.getLogger(ErrorHandler.class.getName());
        logger.severe(message + ": " + e.getMessage());

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
