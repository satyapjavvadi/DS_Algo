@loginfeature
Feature: Login Functionality

  Background:
    Given the registered user has navigated to the login page

  Scenario: Invalid login attempts from Excel
    Given the user attempts invalid login from Excel
    Then the appropriate error messages should be displayed
   
  Scenario: Successful login from Excel
  Given the user provides valid credentials from Excel
  Then the user should be redirected to the Home Page with a message "You are logged in"

  Scenario: Register link visibility
    When the login page is displayed
    Then the user should be able to see the "Register" link  