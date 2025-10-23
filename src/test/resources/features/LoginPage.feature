@loginfeature
Feature: Login Functionality

  Background:
    Given the registered user has navigated to the login page

  Scenario Outline: Invalid login attempts with various "<input combinations>"
    Given the user provides <username> and <password>
    When the user <submission_method>
    Then the error message "<expected_error>" should be displayed

    Examples:
      | input combinations     | username  | password  | submission_method          | expected_error                |
      | Null value in cred     |           |           | submits the login form     | Please fill out this field.   |
      | Null value in cred     |           |           | presses Enter              | Please fill out this field.   |
      | Null value in password | validUser |           | submits the login form     | Please fill out this field.   |
      | Null value in password | validUser |           | presses Enter              | Please fill out this field.   |
      | Null value in username |           | validPass | submits the login form     | Please fill out this field.   |
      | Null value in username |           | validPass | presses Enter              | Please fill out this field.   |
      | Invalid cred           | typoUser  | validPass | initiates login            | Invalid username and password |
      | Invalid cred           | typoUser  | validPass | confirms login using Enter | Invalid username and password |
      | Invalid password       | validUser | typoPass  | initiates login            | Invalid username and password |
      | Invalid Password       | validUser | typoPass  | confirms login using Enter | Invalid username and password |

  Scenario Outline: Successful login
    Given the user provides valid credentials
    When the user <submission_method>
    Then the user should be redirected to the Home Page with a message "You are logged in"

    Examples:
      | submission_method          |
      | submits the login form     |
      | confirms login using Enter |

  Scenario: Register link visibility
    When the login page is displayed
    Then the user should be able to see the "Register" link
