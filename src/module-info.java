module Calculator {
    requires javafx.controls;
    requires javafx.graphics;
    requires java.logging;
    requires org.jetbrains.annotations;

    opens calculator to javafx.graphics;
}