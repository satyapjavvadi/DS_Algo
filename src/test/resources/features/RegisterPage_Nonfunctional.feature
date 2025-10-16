Feature: Non Functional testing for DSAlgo Register page

  Background: User launches the application and lands on Home page

  Scenario: Validating whether fields required for Registration flow is present in Register UI
    Given User is in Home page
    When user clicks on Register button in Home page
    Then User must see Username field in Register UI

  Scenario: Validating whether fields required for Registration flow is present in Register UI
    Given User is in Home page
    When user clicks on Register button in Home page
    Then User must see password field in Register UI

  Scenario: Validating whether fields required for Registration flow is present in Register UI
    Given User is in Home page
    When user clicks on Register button in Home page
    Then user must see password confirmation field in Register UI

  Scenario: Validating whether fields required for Registration flow is present in Register UI
    Given User is in Home page
    When user clicks on Register button in Home page
    Then User must see " Register " button in Register UI

  Scenario: Validating whether fields required for Registration flow is present in Register UI
    Given User is in Home page
    When user clicks on Register button in Home page
    Then User must see the pre-requisite conditions listed  for password entry field in Register UI

  Scenario: Validating whether fields required for Registration flow is present in Register UI
    Given User is in Home page
    When user clicks on Register button in Home page
    Then User must see Login link along with relevant context in Register UI