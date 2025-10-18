package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
    
    public SearchPage(WebDriver driver){
        this.driver = driver;
        // Wait up to 10 seconds for the element to be visible
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By searchBar=By.xpath("//textarea[@class='gLFyf']");

    private By aiOverview=By.xpath("//div[@data-hveid]");

    public void enterSearchKeyword(String keyword){
        waitUntilElementVisible(searchBar);
        driver.findElement(searchBar).sendKeys(keyword);
    }
    public void clickEnter(){
        waitUntilElementVisible(searchBar);
        driver.findElement(searchBar).sendKeys(Keys.ENTER);
    }
    public boolean isSeenAIOverview(){
        waitUntilElementVisible(aiOverview);
        return driver.findElement(aiOverview).isDisplayed();
    }
    public void waitUntilElementVisible(By by){
    wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }
}
