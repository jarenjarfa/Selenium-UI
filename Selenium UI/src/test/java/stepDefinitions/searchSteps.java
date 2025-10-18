package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;
import utils.WebDriverConfig;

public class searchSteps {
    WebDriver driver;
    SearchPage searchPage;
    @Before
    public void setup(){
        try {
            driver = WebDriverConfig.createDriver();
            driver.manage().window().maximize();
            System.out.println("Running tests with browser: " + WebDriverConfig.getBrowser() + 
                             " in mode: " + WebDriverConfig.getRunMode());
            if ("grid".equalsIgnoreCase(WebDriverConfig.getRunMode())) {
                System.out.println("Selenium Grid URL: " + WebDriverConfig.getGridUrl());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver", e);
        }
    }
    @Given("I am on the google search page")
    public void iAmOnTheGoogleSearchPage() {
        driver.get("https://www.google.com/");
        searchPage = new SearchPage(driver);
    }

    @When("I search keyword {string}")
    public void iSearchKeyword(String keyword) {
        searchPage.enterSearchKeyword(keyword);
    }

    @And("I click enter")
    public void iClickEnter() {
        searchPage.clickEnter();
    }

    @Then("I see AI Overview")
    public void iSeeAIOverview() {
        Assert.assertTrue("Ai overview is not visible",searchPage.isSeenAIOverview());
    }
    @After
    public void tearDown(){
        driver.close();
        driver.quit();
    }
}
