# Calculator App

A simple Java-based calculator application built using JavaFX that demonstrates a simple Model-View-Controller (MVC) architecture, custom logging functionality, and robust error handling.

## Features

- **Arithmetic Operations:** Supports addition, subtraction, multiplication, division, percentage calculations, and toggling the sign of numbers.
- **MVC Architecture:** Separates the application into Model (calculation logic), View (user interface), and Controller (event handling).
- **Custom Logging:** Implements a global logging utility that directs all logs to a single file for easier debugging.
- **Error Handling:** Critical errors (such as division by zero) are captured and displayed on the UI for a better user experience.
- **User-Friendly UI:**
  Designed with JavaFX to offer a responsive and intuitive interface.

## Installation

### Prerequisites

- **Java Development Kit (JDK):** Ensure JDK 8 or later is installed.
- **JavaFX:** Verify that the JavaFX libraries are available in your development environment.

### Downloading from GitHub

Clone the repository to your local machine:

```bash
git clone https://github.com/yourusername/CalculatorApp.git
```

## Usage

1. **Build the Project:** Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse) or build from the command line.
2. **Compile the Source Code:** If building from the command line, navigate to the project directory and compile the source files. For example:

   ```Bash
   cd CalculatorApp
   javac -d bin src/calculator/*.java
   ```
3. **Run the Application:** Execute the main class to launch the JavaFX application:

   ```Bash
   java -cp bin calculator.CalculatorApp
   ```

   Adjust the classpath and file paths as necessary based on your environment.

## Project Structure

```Bash
CalculatorApp/
├── src/
│   └── calculator/
│       ├── CalculatorApp.java       // Entry point for the JavaFX application.
│       ├── CalculatorController.java  // Handles user input and event processing.
│       ├── CalculatorModel.java       // Contains the core arithmetic operations and logic.
│       ├── CalculatorView.java        // Manages the graphical user interface.
│       ├── CalculatorUtils.java       // Utility methods (e.g., formatting output).
│       ├── LoggingUtil.java           // Custom logging utility for centralized logging.
│       ├── ICalculatorModel.java      // Interface defining the calculator model contract.
│       └── ICalculatorView.java       // Interface defining the calculator view contract.
├── logs/                             // Directory where the application log file is stored.
└── README.md                         // This file.
```

## Logging

The application uses a custom logging utility `(LoggingUtil.java)` that consolidates log messages from all classes into a single log file `(logs/calculator.log)`. This approach simplifies debugging by keeping all logs in one place.

## Contributing

Contributions are welcome! Feel free to fork this repository, make improvements, and submit pull requests.

## License

This project is licensed under the [MIT License](LICENSE).
