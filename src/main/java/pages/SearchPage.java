package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchPage {
    WebDriver driver;
    WebDriverWait wait;
    CommonPage commonPage;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        // Wait up to 10 seconds for the element to be visible
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.commonPage = new CommonPage(driver);
    }

    private final By searchBar = By.xpath("//textarea[@class='gLFyf']");

    private final By aiOverview = By.xpath("//div[@data-hveid]");


    public void enterSearchKeyword(String keyword) {
        commonPage.enterKeyword(keyword,searchBar);
    }

    public void clickEnter() {
        commonPage.clickEnter(searchBar);
    }

    public boolean isSeenAIOverview() {
        return commonPage.isSeenElement(aiOverview);
    }
}
