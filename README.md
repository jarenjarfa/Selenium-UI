# Selenium UI Testing with Grid Support

This project demonstrates Selenium UI testing with support for both local execution and Selenium Grid.

## Prerequisites

- Java 21
- Maven 3.6+
- Chrome or Firefox browser (for local execution)
- Selenium Grid Hub running on `http://192.168.10.69:4444`

## Project Structure

```
src/
├── main/java/
│   ├── pages/           # Page Object Model classes
│   └── utils/           # Utility classes including WebDriverConfig
└── test/java/
    ├── features/        # Cucumber feature files
    ├── runners/         # Test runners
    └── stepDefinitions/ # Step definition implementations
```

## Running Tests

### Local Execution (Default)
```bash
mvn test
# or explicitly
mvn test -Plocal
```

### Selenium Grid Execution
```bash
# Run with Chrome on Grid
mvn test -Pgrid

# Run with Firefox on Grid
mvn test -Pgrid-firefox
```

### Custom Browser and Mode
```bash
# Override browser and mode via system properties
mvn test -DrunMode=grid -Dbrowser=firefox
```

## Configuration

The `WebDriverConfig` class handles WebDriver initialization based on:
- `runMode`: "local" or "grid"
- `browser`: "chrome" or "firefox"

### Grid Configuration
- Grid URL: `http://192.168.10.69:4444`
- Chrome options include: `--no-sandbox`, `--disable-dev-shm-usage`, `--disable-gpu`
- Window size: 1920x1080

## Features

- **Page Object Model**: Clean separation of page elements and actions
- **Cucumber BDD**: Behavior-driven development with Gherkin syntax
- **Grid Support**: Run tests on remote Selenium Grid
- **Multi-browser**: Support for Chrome and Firefox
- **Configurable**: Easy switching between local and grid execution

## Test Execution Flow

1. `@Before` method initializes WebDriver based on configuration
2. Tests navigate to Google search page
3. Search functionality is tested
4. AI Overview visibility is verified
5. `@After` method cleans up WebDriver resources

## Troubleshooting

### Grid Connection Issues
- Ensure Selenium Grid Hub is running on `http://192.168.10.69:4444`
- Check network connectivity to the Grid Hub
- Verify Grid nodes are registered and available

### Browser Issues
- For local execution, ensure browser drivers are in PATH
- For Grid execution, ensure Grid nodes have the required browsers installed

### Test Failures
- Check console output for WebDriver initialization messages
- Verify Grid URL and browser configuration
- Ensure test environment has proper network access
