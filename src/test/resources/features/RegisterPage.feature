@Registerfeature @Getstarted
Feature: Functional testing for DSAlgo Register page

  Background: User launches the DSAlgo application and navigates to home page
    When User clicks Register link in home page
    Then user lands on Register page

  

  @positiveRegister
  Scenario Outline: Successful register from Excel
    Given user tries register using "<submission_method>" with "<scenarioType>"
    Then user should be redirected to  Home Page with a message "New Account Created. You are logged in as"

    Examples:
      | scenarioType   | submission_method         |
      | valid_register | submits the register form |

  @negativeRegister
  Scenario Outline: Invalid register attempts for "<scenarioType>"
    When user tries registration "<submission_method>" with "<scenarioType>"
    Then appropriate message "<expected_message>" should be displayed

    Examples:
      | scenarioType                             | submission_method         | expected_message                                        |
      | Null value in  username                  | submits the register form | Please fill out this field.                             |
      | Null value in password                   | submits the register form | Please fill out this field.                             |
      | Null value in  confirm password          | submits the register form | Please fill out this field.                             |
      | with specialcharacter password           | submits the register form | Password does not match requirement                     |
      | with password less then eight characters | submits the register form | Password does not match requirement                     |
      | with mismatch password                   | submits the register form | password_mismatch:The two password fields didn’t match. |
      | with specialcharacter username           | submits the register form | Username must be alphanumeric                           |
