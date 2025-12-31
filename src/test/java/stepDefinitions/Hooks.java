package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverManager;
import utils.WebDriverConfig;

public class Hooks {
    
    @Before
    public void setup() {
        try {
            WebDriver driver = WebDriverConfig.createDriver();
            driver.manage().window().maximize();
            DriverManager.setDriver(driver);
            
            System.out.println("Running tests with browser: " + WebDriverConfig.getBrowser() + 
                             " in mode: " + WebDriverConfig.getRunMode());
            if ("grid".equalsIgnoreCase(WebDriverConfig.getRunMode())) {
                System.out.println("Selenium Grid URL: " + WebDriverConfig.getGridUrl());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }
    
    @After
    public void tearDown(io.cucumber.java.Scenario scenario) {
        // Take screenshot if scenario failed
        if (scenario.isFailed()) {
            try {
                WebDriver driver = DriverManager.getDriver();
                if (driver instanceof org.openqa.selenium.TakesScreenshot) {
                    byte[] screenshot = ((org.openqa.selenium.TakesScreenshot) driver)
                        .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
                    scenario.attach(screenshot, "image/png", "Failed Screenshot");
                }
            } catch (Exception ex) {
                System.err.println("Unable to capture screenshot on failure: " + ex.getMessage());
            }
        }
        DriverManager.removeDriver();
    }
}

