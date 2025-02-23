package calculator;// Entry point

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.logging.Logger;

public class CalculatorApp extends Application {
    private static final Logger logger = LoggingUtil.getLogger(CalculatorApp.class.getName());

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            ICalculatorModel model = new CalculatorModel();
            ICalculatorView view = new CalculatorView();
            new CalculatorController(model, view);

            Scene scene = new Scene(view.buildUI());

            String cssPath = "styles.css";
            if (getClass().getResource(cssPath) != null) {
                scene.getStylesheets().add(
                        Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
            } else {
                throw new Exception("Stylesheet '" + cssPath + "' not found.");
            }

            stage.setScene(scene);
            stage.setTitle("Calculator");
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            logger.severe("Initialization failed: " + e.getMessage());
            handleInitializationException("Application failed to start", e.getMessage());
        }
    }

    public void handleInitializationException (String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        String cssPath = "styles.css";
        DialogPane dialogPane = alert.getDialogPane();

        if (getClass().getResource(cssPath) != null) {
            dialogPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
        } else {
            logger.warning("Warning: Stylesheet not found!");
        }
        alert.showAndWait();

        // Ensure the app does not start
        Platform.exit();
        System.exit(1);
    }
}
