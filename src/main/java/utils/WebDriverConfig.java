package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverConfig {
    
    private static final String GRID_URL = "http://localhost:4444";
    private static final String BROWSER = System.getProperty("browser", "chrome");
    private static final String RUN_MODE = System.getProperty("runMode", "local");
    
    public static WebDriver createDriver() throws MalformedURLException {
        WebDriver driver;
        
        if ("grid".equalsIgnoreCase(RUN_MODE)) {
            driver = createGridDriver();
        } else {
            driver = createLocalDriver();
        }
        
        return driver;
    }
    
    private static WebDriver createGridDriver() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        
        switch (BROWSER.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-dev-shm-usage");
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                capabilities.merge(chromeOptions);
                break;
                
            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--width=1920");
                firefoxOptions.addArguments("--height=1080");
                capabilities.merge(firefoxOptions);
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported browser: " + BROWSER);
        }
        
        return new RemoteWebDriver(new URL(GRID_URL), capabilities);
    }
    
    private static WebDriver createLocalDriver() {
        switch (BROWSER.toLowerCase()) {
            case "chrome":
                return new ChromeDriver();
            case "firefox":
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Unsupported browser: " + BROWSER);
        }
    }
    
    public static String getBrowser() {
        return BROWSER;
    }
    
    public static String getRunMode() {
        return RUN_MODE;
    }
    
    public static String getGridUrl() {
        return GRID_URL;
    }
}
