package calculator;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Objects;
import java.util.logging.Logger;

public class CalculatorModel implements ICalculatorModel {
    private double currentNumber;
    private double previousNumber;
    private String operator = "";

    private static final Logger logger = LoggingUtil.getLogger(CalculatorApp.class.getName());

    public double getCurrentNumber() {
        return currentNumber;
    }

    public double getPreviousNumber() {
        return previousNumber;
    }

    public void setOperand(double value, boolean isFirstOperand) {
        if (isFirstOperand) {
            previousNumber = value;
        } else {
            currentNumber = value;
        }
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public double calculate() {
        try {
            if (operator.isEmpty()) {
                logger.warning("No operator selected. Returning currentNumber.");
                return currentNumber;
            }

            if ("/".equals(operator) && currentNumber == 0) {
                throw new ArithmeticException("Division By Zero Error!");
            }

            double result = switch (operator) {
                case "+" -> add();
                case "-" -> subtract();
                case "*" -> multiply();
                case "/" -> divide();
                default -> currentNumber;
            };

            return  Double.parseDouble(CalculatorUtils.formatNumber(result));
        } catch (ArithmeticException e) {
            logger.severe("Error: " + e.getMessage());
            handleDivisionByZeroError();
            return Double.NaN;
        }
    }

    public double add() {
        return previousNumber + currentNumber;
    }

    public double subtract() {
        return previousNumber - currentNumber;
    }

    public double multiply() {
        return previousNumber * currentNumber;
    }

    public double divide() { return previousNumber / currentNumber; }

    public double calcPercentage () {
        return currentNumber / 100;
    }

    public double toggleSign (double value) {
        setOperand(-value, false);
        return getCurrentNumber();
    }

    public void clear () {
        currentNumber = 0;
        previousNumber = 0;
        operator = "";
    }

    public void handleDivisionByZeroError () {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Division by Zero Error!");
        alert.setHeaderText(null);
        alert.setContentText("Cannot divide by zero!");
        alert.showAndWait();

        DialogPane alertDialogPane = alert.getDialogPane();
        alertDialogPane.getStylesheets().add(Objects.requireNonNull(
                getClass().getResource("styles.css")).toExternalForm());

        logger.warning("Attempted division by zero.");
    }
}