# Selenium UI Testing Framework

A comprehensive Selenium WebDriver testing framework built with Java, Maven, and Cucumber BDD. This project demonstrates modern UI testing practices with support for both local execution and Selenium Grid distributed testing.

## 🚀 Features

- **Page Object Model (POM)**: Clean separation of page elements and business logic
- **Cucumber BDD**: Behavior-driven development with Gherkin syntax
- **Selenium Grid Support**: Run tests on remote Selenium Grid for distributed execution
- **Multi-browser Support**: Chrome and Firefox browser support
- **Thread-safe Driver Management**: ThreadLocal-based WebDriver management for parallel execution
- **Configurable Execution**: Easy switching between local and grid execution modes
- **Explicit Waits**: Robust element waiting strategies using WebDriverWait
- **Maven Profiles**: Pre-configured profiles for different execution scenarios
- **Automatic Screenshot Capture**: Screenshots automatically captured on test failures and attached to reports

## 📋 Prerequisites

- **Java 21** or higher
- **Maven 3.6+**
- **Chrome or Firefox browser** (for local execution)
- **ChromeDriver/GeckoDriver** in PATH (for local execution)
- **Selenium Grid Hub** running on `http://192.168.10.69:4444` (for grid execution)

## 🏗️ Project Structure

```
src/
├── main/java/
│   ├── pages/
│   │   ├── CommonPage.java      # Base page with common utilities
│   │   └── SearchPage.java      # Google search page implementation
│   └── utils/
│       ├── DriverManager.java   # Thread-safe WebDriver management
│       └── WebDriverConfig.java # WebDriver initialization and configuration
└── test/java/
    ├── features/
    │   └── Search.feature       # Cucumber feature file
    ├── runners/
    │   └── TestRunner.java      # Cucumber test runner
    └── stepDefinitions/
        ├── Hooks.java           # Setup and teardown hooks
        └── SearchSteps.java     # Step definition implementations
```

## 🛠️ Technologies

- **Selenium WebDriver 4.27.0**: Browser automation
- **Cucumber 7.14.0**: BDD framework
- **JUnit 4.13.2**: Test execution framework
- **Maven**: Build and dependency management

## ⚙️ Configuration

### System Properties

The framework uses system properties to configure execution:

- `runMode`: Execution mode - `"local"` or `"grid"` (default: `"local"`)
- `browser`: Browser type - `"chrome"` or `"firefox"` (default: `"chrome"`)

### Grid Configuration

- **Grid URL**: `http://localhost:4444` (configurable in `WebDriverConfig.java`)
- **Chrome Options**: 
  - `--no-sandbox`
  - `--disable-dev-shm-usage`
  - `--disable-gpu`
  - `--window-size=1920,1080`
- **Firefox Options**: Window size 1920x1080

### Maven Profiles

The project includes three Maven profiles:

1. **local** (default): Runs tests locally with Chrome
2. **grid**: Runs tests on Selenium Grid with Chrome
3. **grid-firefox**: Runs tests on Selenium Grid with Firefox

## 🏃 Running Tests

### Local Execution (Default)

```bash
# Run with default profile (local + Chrome)
mvn test

# Explicitly specify local profile
mvn test -Plocal

# Run with Firefox locally
mvn test -Plocal -Dbrowser=firefox
```

### Selenium Grid Execution

```bash
# Run with Chrome on Grid
mvn test -Pgrid

# Run with Firefox on Grid
mvn test -Pgrid-firefox
```

### Custom Configuration

```bash
# Override browser and mode via system properties
mvn test -DrunMode=grid -Dbrowser=firefox

# Run with specific profile and override browser
mvn test -Pgrid -Dbrowser=firefox
```

## 📖 Test Scenarios

The framework currently includes:

- **Google Search Test**: Tests Google search functionality and AI Overview visibility
  - Navigate to Google search page
  - Enter search keyword
  - Verify AI Overview is displayed

## 🏛️ Architecture

### Page Object Model

- **CommonPage**: Base class providing common utilities:
  - Element visibility waiting
  - Element clickability waiting
  - Keyword input handling
  - Element visibility checking
  - Enter key simulation

- **SearchPage**: Google search page implementation:
  - Search bar interaction
  - AI Overview verification

### Driver Management

- **DriverManager**: Thread-safe WebDriver management using ThreadLocal
  - `getDriver()`: Get current thread's WebDriver instance
  - `setDriver()`: Set WebDriver for current thread
  - `removeDriver()`: Clean up and remove WebDriver instance

### Hooks

- **@Before**: Initializes WebDriver based on configuration
  - Creates local or grid driver
  - Maximizes browser window
  - Sets driver in DriverManager
  - Logs execution mode and browser

- **@After**: Cleans up WebDriver resources
  - Captures screenshot on test failure
  - Quits and removes driver from ThreadLocal

### WebDriver Configuration

- **WebDriverConfig**: Centralized WebDriver creation
  - Reads system properties for runMode and browser
  - Creates appropriate driver (local or remote)
  - Configures browser options
  - Provides configuration getters

## 🔍 Test Execution Flow

1. **Setup Phase** (`@Before` hook):
   - Read `runMode` and `browser` system properties
   - Initialize WebDriver (local or grid)
   - Maximize browser window
   - Store driver in DriverManager

2. **Test Execution**:
   - Navigate to Google search page
   - Enter search keyword
   - Submit search
   - Verify AI Overview visibility

3. **Teardown Phase** (`@After` hook):
   - Quit WebDriver
   - Clean up ThreadLocal storage

## 📊 Test Reports

Cucumber generates test reports after execution:
- **HTML Report**: `target/cucumber-reports.html` - Visual test execution report
- **JSON Report**: `target/cucumber-reports.json` - Machine-readable report for integration with other tools

## 🐛 Troubleshooting

### Grid Connection Issues

- Ensure Selenium Grid Hub is running on `http://localhost:4444` (or update `GRID_URL` in `WebDriverConfig.java`)
- Check network connectivity to the Grid Hub
- Verify Grid nodes are registered and available
- Check Grid Hub logs for connection errors

### Browser Issues

**Local Execution:**
- Ensure browser drivers (ChromeDriver/GeckoDriver) are in PATH
- Verify browser versions match driver versions
- Check browser installation and accessibility

**Grid Execution:**
- Ensure Grid nodes have required browsers installed
- Verify browser versions on Grid nodes
- Check node registration status on Grid Hub

### Test Failures

- Check console output for WebDriver initialization messages
- Verify Grid URL and browser configuration
- Ensure test environment has proper network access
- Check element locators if tests fail on element interaction
- Review Cucumber HTML reports for detailed failure information

### Common Issues

- **Driver not found**: Add driver to PATH or use WebDriverManager
- **Grid timeout**: Check Grid Hub availability and network connectivity
- **Element not found**: Verify locators and add appropriate waits
- **Thread safety issues**: Ensure using DriverManager for parallel execution

## 📝 Notes

- The framework uses explicit waits (WebDriverWait) with 10-second timeout
- ThreadLocal ensures thread-safe WebDriver management for parallel execution
- Grid URL is configured in `WebDriverConfig.java` (default: `http://localhost:4444`) - modify if needed
- Test runner is configured with tag `@fajar` - modify in `TestRunner.java` if needed
- Screenshots are automatically captured on test failures and attached to Cucumber reports

## 🔄 Future Enhancements

- Add WebDriverManager for automatic driver management
- Support for additional browsers (Edge, Safari)
- Allure reporting integration
- CI/CD pipeline configuration examples
- Parallel test execution configuration
- Enhanced logging and reporting capabilities
