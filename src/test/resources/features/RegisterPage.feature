@Registerfeature @Getstarted
Feature: Functional testing for DSAlgo Register page

  Background: User launches the DSAlgo application and navigates to home page
    When User clicks Register link in home page
    Then user lands on Register page

  Scenario: User must be navigated to home page upon succesfull registration
    When User clicks Register button using positive data from excel
    Then User must be navigated to Home page

  Scenario Outline: Data-driven Register negative test using Excel
    When User executes register validations from excel
