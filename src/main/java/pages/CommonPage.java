package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonPage
{
    private final WebDriver driver;
    private final WebDriverWait wait;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        // Wait up to 10 seconds for the element to be visible
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void waitUntilElementVisible(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
    public void waitUntilElementClickable(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
    public void enterKeyword(String keyword,By by) {
        waitUntilElementVisible(by);
        driver.findElement(by).sendKeys(keyword);
    }
    public boolean isSeenElement(By by) {
        waitUntilElementVisible(by);
        return driver.findElement(by).isDisplayed();
    }
    public void clickEnter(By by) {
        waitUntilElementClickable(by);
        driver.findElement(by).sendKeys(Keys.ENTER);
    }
}
