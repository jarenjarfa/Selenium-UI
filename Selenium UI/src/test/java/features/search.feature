Feature: Google Search Functionality

  Scenario: Successfull Search keyword
    Given I am on the google search page
    When I search keyword "selenium"
    And I click enter
    Then I see AI Overview