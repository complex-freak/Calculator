package calculator;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CalculatorModel {
    private double currentNumber;
    private double previousNumber;
    private String operator = "";

    private static final Logger logger = Logger.getLogger(CalculatorModel.class.getName());

    // Static block to configure the logger
    static {
        try {
            File logDirectory = new File("logs");
            if (!logDirectory.exists()) {
                logDirectory.mkdir(); // Create directory if it doesn't exist
            }

            FileHandler fileHandler = new FileHandler("logs/calculator.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // Prevent logging to console
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public double getCurrentNumber() {
        return currentNumber;
    }

    public double PrevNumber() {
        return previousNumber;
    }

    public void setOperand(double value, boolean isFirstOperand) {
        if (isFirstOperand) {
            previousNumber = value;
        } else {
            currentNumber = value;
        }
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

            return formatResult(result);
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

    private double formatResult(double result) {
        BigDecimal rounded = new BigDecimal(result)
                .setScale(8, RoundingMode.HALF_UP)
                .stripTrailingZeros();

        DecimalFormat formatter = new DecimalFormat("#.########");
        return Double.parseDouble(formatter.format(rounded));
    }
}