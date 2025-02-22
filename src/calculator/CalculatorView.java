package calculator;   // Gui Components

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class CalculatorView {
    private final GridPane layout = new GridPane();

    private final TextField display = new TextField("");
    private final Button[] numberButtons = new Button[10];
    private final Button decimalButton = createButton(".", null);
    private final Button addButton = createButton("+", "func");
    private final Button subtractButton = createButton("-", "func");
    private final Button multiplyButton = createButton("x", "func");
    private final Button divideButton = createButton("÷", "func");
    private final Button equalButton = createButton("=", "func");
    private final Button clearButton = createButton("C", "utility");
    private final Button toggleButton = createButton("+/-", "utility");
    private final Button percentageButton = createButton("%", "utility");

    public CalculatorView() {
        try {
            initializeButtons();
            initializeStyles();
        } catch (Exception e) {
            handleInitializationError(e);
        }
    }

    public VBox buildUI() {
        addButtons();
        return new VBox(display, layout);
    }

    private void initializeButtons() {
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
        }
    }

    private void initializeStyles() {
        GridPane.setMargin(display, new Insets(8, 0, 8, 0));
        layout.getStyleClass().add("layout");

        // display.setEditable(false);
        display.getStyleClass().add("calculator-display");

        numberButtons[0].setId("btn0");

        clearButton.getStyleClass().add("utility");
        toggleButton.getStyleClass().add("utility");
        percentageButton.getStyleClass().add("utility");

        addButton.getStyleClass().add("func");
        subtractButton.getStyleClass().add("func");
        multiplyButton.getStyleClass().add("func");
        divideButton.getStyleClass().add("func");
        equalButton.getStyleClass().add("func");
    }

    private void addButtons (){
        // Top row (utility buttons)
        layout.add(clearButton, 0, 0);
        layout.add(toggleButton, 1, 0);
        layout.add(percentageButton, 2, 0);
        layout.add(divideButton, 3, 0);

        // Number buttons + operators
        int numIndex = 9;
        for (int row = 1; row <= 3; row++) {
            for (int col = 2; col >= 0; col--) {
                layout.add(numberButtons[numIndex--], col, row);
            }
            layout.add(getOperator(row), 3, row);
        }

        // Bottom row
        layout.add(numberButtons[0], 0, 4, 2, 1);
        layout.add(decimalButton, 2, 4);
        layout.add(equalButton, 3, 4);
    }

    private Button getOperator(int row) {
        return switch (row) {
            case 1 -> multiplyButton;
            case 2 -> subtractButton;
            case 3 -> addButton;
            default -> null;
        };
    }

    // helper method
    private Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        return button;
    }

    private void handleInitializationError(Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Initialization Error");
        alert.setHeaderText("An error occurred while initializing the calculator.");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}