package calculator;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public interface ICalculatorView {
    String getDisplay();
    void setDisplay(String value);
    ButtonBase[] getNumberButtons();
    ButtonBase getAddButton();
    ButtonBase getSubtractButton();
    ButtonBase getMultiplyButton();
    ButtonBase getDivideButton();
    ButtonBase getEqualButton();
    ButtonBase getClearButton();
    ButtonBase getToggleButton();
    ButtonBase getPercentageButton();
    ButtonBase getDecimalButton();
    TextField getScene();
    VBox buildUI();
}