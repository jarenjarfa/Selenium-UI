package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.SearchPage;
import utils.DriverManager;

public class SearchSteps {
    SearchPage searchPage;
    
    @Given("I am on the google search page")
    public void iAmOnTheGoogleSearchPage() {
        WebDriver driver = DriverManager.getDriver();
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
}
