package calculator;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.logging.Logger;

public class CalculatorView implements ICalculatorView {
    private final GridPane layout = new GridPane();

    private final TextField display = new TextField("0");
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

    private static final Logger logger = LoggingUtil.getLogger(CalculatorApp.class.getName());

    public CalculatorView() {
        try {
            initializeButtons();
            initializeStyles();
        } catch (Exception e) {
            handleInitializationError(e);
            logger.severe("User Interface initialization failed: " + e.getMessage());
        }
    }

    public TextField getScene() {
        return display;
    }

    public String getDisplay() {
        return display.getText();
    }

    public void setDisplay(String value) {
        display.setText(value);
    }

    public ButtonBase[] getNumberButtons() {
        return numberButtons;
    }

    public ButtonBase getAddButton() {
        return addButton;
    }

    public ButtonBase getSubtractButton() {
        return subtractButton;
    }

    public ButtonBase getMultiplyButton() {
        return multiplyButton;
    }

    public ButtonBase getDivideButton() {
        return divideButton;
    }

    public ButtonBase getEqualButton() {
        return equalButton;
    }

    public ButtonBase getClearButton() {
        return clearButton;
    }

    public ButtonBase getToggleButton() {
        return toggleButton;
    }

    public ButtonBase getPercentageButton() {
        return percentageButton;
    }

    public ButtonBase getDecimalButton() {
        return decimalButton;
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

        display.setEditable(false);
        display.setFocusTraversable(false);
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

    private void handleInitializationError(Exception ignoredE) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Initialization Error");
        alert.setHeaderText(null);
        alert.setContentText("An error occurred while initializing the User Interface.");

        String cssPath = "styles.css";
        DialogPane dialogPane = alert.getDialogPane();

        if (getClass().getResource(cssPath) != null) {
            dialogPane.getStylesheets().add(
                    Objects.requireNonNull(getClass().getResource(cssPath)).toExternalForm());
        } else {
            logger.warning("Warning: Stylesheet not found!");
        }

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                forceExit();
            }
        });

        Stage alertStage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertStage.setOnCloseRequest(_ -> forceExit());
    }

    // helper methods
    private Button createButton(String text, String styleClass) {
        Button button = new Button(text);
        button.getStyleClass().add(styleClass);
        return button;
    }

    private void forceExit() {
        Platform.exit();
        System.exit(0);
    }
}
