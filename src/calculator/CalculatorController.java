package calculator;

import java.util.logging.Logger;

public class CalculatorController {
    private final ICalculatorModel model;
    private final ICalculatorView view;
    private boolean isNewInput = true;

    private static final Logger logger = LoggingUtil.getLogger(CalculatorApp.class.getName());

    public CalculatorController(ICalculatorModel model, ICalculatorView view) {
        this.model = model;
        this.view = view;
        initializeEventHandlers();
    }

    private void initializeEventHandlers() {
        initializeKeyHandlers();
        initializeNumberButtons();
        initializeOperatorButtons();
        initializeSpecialButtons();
    }

    private void initializeNumberButtons() {
        for (int i = 0; i < 10; i++) {
            int number = i;
            view.getNumberButtons()[i].setOnAction(event -> handleNumberInput(number));
        }
    }

    private void initializeOperatorButtons() {
        view.getAddButton().setOnAction(event -> handleOperator("+"));
        view.getSubtractButton().setOnAction(event -> handleOperator("-"));
        view.getMultiplyButton().setOnAction(event -> handleOperator("*"));
        view.getDivideButton().setOnAction(event -> handleOperator("/"));
    }

    private void initializeSpecialButtons() {
        view.getEqualButton().setOnAction(event -> handleEquals());
        view.getClearButton().setOnAction(event -> handleClear());
        view.getToggleButton().setOnAction(event -> handleToggleSign());
        view.getPercentageButton().setOnAction(event -> handlePercentage());
        view.getDecimalButton().setOnAction(event -> handleDecimalInput());
    }

    private void initializeKeyHandlers() {
        view.getScene().setOnKeyPressed(event -> handleKeyPress(event.getText(), event.getCode().toString()));
    }

    private void handleKeyPress(String key, String code) {
        if (key.matches("[0-9]")) {
            handleNumberInput(Integer.parseInt(key));
        } else if (".".equals(key)) {
            handleDecimalInput();
        } else if ("+".equals(key) || "-".equals(key) || "*".equals(key) || "/".equals(key)) {
            handleOperator(key);
        } else if ("ENTER".equals(code) || "=".equals(key)) {
            handleEquals();
        } else if ("BACK_SPACE".equals(code)) {
            handleBackspace();
        } else if ("C".equalsIgnoreCase(key)) {
            handleClear();
        } else if ("ESCAPE".equals(code)) {
            handleClear();
        }
    }

    private void handleNumberInput(int number) {
        String currentDisplay = view.getDisplay();

        // If a new input is starting, replace the display, else append
        if (isNewInput || currentDisplay.equals("0")) {
            view.setDisplay(String.valueOf(number));
        } else {
            view.setDisplay(currentDisplay + number);
        }

        isNewInput = false; // Next number should append
    }

    private void handleOperator(String operator) {
        try {
            double currentValue = Double.parseDouble(view.getDisplay());

            if (!isNewInput) {
                if (!model.getOperator().isEmpty()) {
                    model.setOperand(currentValue, false);

                    double result = model.calculate();

                    view.setDisplay(formatNumber(result));
                    model.setOperand(result, true);
                } else {
                    model.setOperand(currentValue, true);
                }
            }

            model.setOperator(operator);
            isNewInput = true;
        } catch (NumberFormatException e) {
            logger.warning("Error: Invalid number input.\n" + e.getMessage());
            view.setDisplay("0");
        }
    }

    private void handleEquals() {
        if (!model.getOperator().isEmpty() && !isNewInput) {
            try {
                double current = Double.parseDouble(view.getDisplay());
                model.setOperand(current, false);

                double result = model.calculate();
                view.setDisplay(formatNumber(result));

                model.clear();
                isNewInput = true;
            } catch (NumberFormatException e) {
                logger.warning("Error: Invalid input during calculation.\n" + e.getMessage());
                view.setDisplay("0");
            }
        }
    }

    private void handleClear() {
        model.clear();
        view.setDisplay("0");
        isNewInput = true;
    }

    private void handleToggleSign() {
        double currentValue = Double.parseDouble(view.getDisplay());
        double result = model.toggleSign(currentValue);
        view.setDisplay(formatNumber(result));
    }

    private void handlePercentage() {
        double currentValue = Double.parseDouble(view.getDisplay());
        model.setOperand(currentValue, false);

        double result = model.calcPercentage();

        view.setDisplay(formatNumber(result));
        model.setOperand(result, false);
        isNewInput = true;
    }

    private void handleDecimalInput() {
        String currentDisplay = view.getDisplay();

        if (!currentDisplay.contains(".")) {
            if (isNewInput || currentDisplay.equals("0")) {
                view.setDisplay("0.");
            } else {
                view.setDisplay(currentDisplay + ".");
            }
            isNewInput = false;
        }
    }

    private void handleBackspace() {
        String currentDisplay = view.getDisplay();
        if (currentDisplay.length() > 1) {
            view.setDisplay(currentDisplay.substring(0, currentDisplay.length() - 1));
        } else {
            view.setDisplay("0");
        }
    }

    // helper method
    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}

