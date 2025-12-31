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
    public void tearDown() {
        DriverManager.removeDriver();
    }
}

