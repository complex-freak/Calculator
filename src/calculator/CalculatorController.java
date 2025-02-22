package calculator;

public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    private boolean isNewInput = true;

    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        initializeEventHandlers();
    }


    private void initializeEventHandlers() {
        // Handle number buttons (0-9)
        for (int i = 0; i < 10; i++) {
            int number = i; // Capture loop variable
            view.getNumberButtons()[i].setOnAction(event -> handleNumberInput(number));
        }

        // Handle operator buttons
        view.getAddButton().setOnAction(event -> handleOperator("+"));
        view.getSubtractButton().setOnAction(event -> handleOperator("-"));
        view.getMultiplyButton().setOnAction(event -> handleOperator("*"));
        view.getDivideButton().setOnAction(event -> handleOperator("/"));

        // Handle special function buttons
        view.getEqualButton().setOnAction(event -> handleEquals());
        view.getClearButton().setOnAction(event -> handleClear());
        view.getToggleButton().setOnAction(event -> handleToggleSign());
        view.getPercentageButton().setOnAction(event -> handlePercentage());

        view.getDecimalButton().setOnAction(event -> handleDecimalInput());

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
        double currentValue = Double.parseDouble(view.getDisplay());
        model.setOperand(currentValue, true);
        model.setOperator(operator);

        isNewInput = true;
    }

    private void handleEquals() {
        double current = Double.parseDouble(view.getDisplay());
        model.setOperand(current, false);

        double result = model.calculate();

        view.setDisplay(formatNumber(result));
        isNewInput = true;
    }

    private void handleClear() {
        model.clear();
        view.setDisplay("0");
        isNewInput = true;
    }

    private void handleToggleSign() {
        double currentValue = Double.parseDouble(view.getDisplay());
        double result = model.toggleSign(currentValue);

        view.setDisplay(String.valueOf(result));
    }

    private void handlePercentage() {
        double currentValue = Double.parseDouble(view.getDisplay());
        model.setOperand(currentValue, false);

        double result = model.calcPercentage();

        view.setDisplay(String.valueOf(result));
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

    // helper method
    private String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        } else {
            return String.valueOf(value);
        }
    }
}

