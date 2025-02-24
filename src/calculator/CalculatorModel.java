package calculator;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorModel implements ICalculatorModel {
    private double currentNumber;
    private double previousNumber;
    private String operator = "";

    private static final Logger logger = LoggingUtil.getLogger(CalculatorModel.class.getName());

    @Override
    public double getCurrentNumber() {
        return currentNumber;
    }

    @Override
    public void setOperand(double value, boolean isFirstOperand) {
        if (isFirstOperand) {
            previousNumber = value;
        } else {
            currentNumber = value;
        }
    }

    @Override
    public String getOperator() {
        return operator;
    }

    @Override
    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public double calculate() {
        if (operator.isEmpty()) {
            logger.warning("No operator selected. Returning currentNumber.");
            return currentNumber;
        }

        if (currentNumber == 0) {
            String errorMessage = "Division by zero error";
            logger.log(Level.SEVERE, errorMessage);
            throw new ArithmeticException(errorMessage);
        }

        switch (operator) {
            case "+":
                return add();
            case "-":
                return subtract();
            case "*":
                return multiply();
            case "/":
                return divide();
            default:
                String errorMessage = "Invalid operator: " + operator;
                logger.log(Level.SEVERE, errorMessage);
                throw new IllegalArgumentException(errorMessage);
        }
    }

    @Override
    public double add() {
        return previousNumber + currentNumber;
    }

    @Override
    public double subtract() {
        return previousNumber - currentNumber;
    }

    @Override
    public double multiply() {
        return previousNumber * currentNumber;
    }

    @Override
    public double divide() {
        return previousNumber / currentNumber;
    }

    @Override
    public double calcPercentage() {
        return currentNumber / 100;
    }

    @Override
    public double toggleSign(double value) {
        return -value;
    }

    @Override
    public void clear () {
        currentNumber = 0;
        previousNumber = 0;
        operator = "";
    }
}