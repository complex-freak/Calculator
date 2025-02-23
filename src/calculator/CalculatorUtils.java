package calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CalculatorUtils {
    private static final Logger logger = LoggingUtil.getLogger(CalculatorUtils.class.getName());

    /**
     * Formats a number to remove trailing zeros and limit decimal places.
     *
     * @param value The number to format.
     * @return The formatted number as a string.
     */
    public static String formatNumber(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value); // Return integer if no decimal part
        } else {
            // Round to 8 decimal places and remove trailing zeros
            BigDecimal rounded = new BigDecimal(value)
                    .setScale(8, RoundingMode.HALF_UP)
                    .stripTrailingZeros();
            DecimalFormat formatter = new DecimalFormat("#.########");
            return formatter.format(rounded);
        }
    }

    /**
     * Logs a message with a specified severity level.
     *
     * @param message The message to log.
     * @param level   The severity level (e.g., Level.SEVERE, Level.WARNING).
     */
    public static void log(String message, Level level) {
        logger.log(level, message);
    }

    /**
     * Validates if a string is a valid number.
     *
     * @param input The string to validate.
     * @return True if the input is a valid number, false otherwise.
     */
    public static boolean isValidNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}