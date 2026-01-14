@Registerfeature @Getstarted
Feature: Functional testing for DSAlgo Register page

  Background: User launches the DSAlgo application and navigates to home page
    When User clicks "Register" link in home page

  Scenario: Number of fields present in Register UI
    Then User must see 3 input fields in Register UI

  Scenario: Validate labels text in Register UI
    Then User must see labels with text in Register UI
      | Username:              |
      | Password:              |
      | Password confirmation: |

  Scenario: Number of buttons present in Register UI
    Then User must see 1 button in Register UI

  Scenario: The button should have text "Register"
    Then User should be able to see button with text "Register" in Register UI

  Scenario: validate links text in Register UI
    Then User must see links with text in Register UI
      | Register |
      | Sign in  |

  Scenario: Company name must be  present in top nav bar of Register UI
    Then User must see "NumpyNinja" company name in top nav bar of  Register UI

  Scenario: Validate presence of pre-requisite conditions list for password entry field in Register UI
    Then User must see list items for password entry field in Register UI
      | Your password cant be too similar to your other personal information |
      | Your password must contain at least 8 characters                     |
      | Your password cant be a commonly used password                       |
      | Your password cant be entirely numeric                               |

  @positiveRegister
  Scenario Outline: Successful register from Excel
    When user register using "<submission_method>" with "<scenarioType>"
    Then user should be redirected to  Home Page with a message "New Account Created. You are logged in as"

    Examples:
      | scenarioType   | submission_method         |
      | valid_register | submits the register form |

  @negativeRegister
  Scenario Outline: Invalid register attempts for "<scenarioType>"
    When user register using "<submission_method>" with "<scenarioType>"
    Then appropriate message "<expected_message>" should be displayed "<scenarioType>"

    Examples:
      | scenarioType                             | submission_method         | expected_message                                        |
      | Null value in  username                  | submits the register form | Please fill out this field.                             |
      | Null value in password                   | submits the register form | Please fill out this field.                             |
      | Null value in  confirm password          | submits the register form | Please fill out this field.                             |
      | with specialcharacter password           | submits the register form | Password does not match requirement                     |
      | with password less then eight characters | submits the register form | Password does not match requirement                     |
      | with mismatch password                   | submits the register form | password_mismatch:The two password fields didn’t match. |
      | with specialcharacter username           | submits the register form | Username must be alphanumeric                           |
