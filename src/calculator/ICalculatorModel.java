package calculator;

public interface ICalculatorModel {
    double getCurrentNumber();
    double getPreviousNumber();
    void setOperand(double value, boolean isFirstOperand);
    String getOperator();
    void setOperator(String operator);
    double calculate();
    double add();
    double subtract();
    double multiply();
    double divide();
    double calcPercentage();
    double toggleSign(double value);
    void clear();
}