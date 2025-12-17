@loginFeature @Getstarted
Feature: Login Functionality

  Background:
    Given the registered user has navigated to the home page
    When the user clicks sign in link

  @negativeTC_Login
  Scenario Outline: Invalid login attempts for "<testCaseType>"

    When the user "<submission_method>" with "<testCaseType>"
    Then the appropriate error messages should be displayed in "<Field>"

    Examples:
      | testCaseType           | submission_method          | Field          |
      | Null value in cred     | submits the login form     | username field |
      | Null value in cred     | presses Enter              | username field |
      | Null value in password | submits the login form     | password field |
      | Null value in password | presses Enter              | password field |
      | Null value in username | submits the login form     | username field |
      | Null value in username | presses Enter              | username field |
      | Invalid  user          | initiates login            | alert          |
      | Invalid  user          | confirms login using Enter | alert          |
      | Invalid password       | initiates login            | alert          |
      | Invalid password       | confirms login using Enter | alert          |

@positiveTC_Login
  Scenario Outline: Successful login from Excel
    Given the user "<submission_method>" with "<testCaseType>"
    Then the user should be redirected to the Home Page with a message "You are logged in"
    Examples:
      | testCaseType | submission_method          |
      | valid_login  | Submits the login form     |
      | valid_login  | confirms login using Enter |

  Scenario: Register link visibility
    When the login page is displayed
    Then the user should be able to see the "Register" link


