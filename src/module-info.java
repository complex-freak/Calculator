module Calculator {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.logging;

    opens calculator to javafx.graphics;
}