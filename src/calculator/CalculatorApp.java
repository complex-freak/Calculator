package calculator;// Entry point

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class CalculatorApp extends Application {
    public static void main(String[] args) throws Exception {
        Application.launch(args);
    }

    public void start(Stage stage) {
        CalculatorView view = new CalculatorView();
        // CalculatorController controller = new CalculatorController();

        Scene scene = new Scene(view.buildUI());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles.css")).toExternalForm());
        
        stage.setScene(scene);
        stage.setTitle("Calculator");
        stage.show();

    }
}
