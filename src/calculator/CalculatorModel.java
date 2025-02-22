package calculator;
// Handles calculations

public class CalculatorModel {
    private double current;
    private double previous;
    private String operator;

    public void setOperator (String operator) {
        this.operator = operator;
        previous = current;
        current = 0;
    }

    public double calculate () {
        switch (operator) {
            case "+": add();
            case "-": subtract();
            case "*": multiply();
            case "/": divide();
            default: return current;
        }
    }

    public double add() {
        return previous + current;
    }

    public double subtract() {
        return previous - current;
    }

    public double multiply() {
        return previous * current;
    }

    public double divide() {
        if (current == 0) {
            System.out.println("Can not divide by Zero!!");
            return 0;
        }

        return previous / current;
    }

    

    public double calcPercentage () {
        double percentage = 0;

        return percentage;
    }

    public void toggleSign () {
        
    }

    public void clear () {

    }
}