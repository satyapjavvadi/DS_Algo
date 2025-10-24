@Registerfeature @Getstarted
Feature: Functional testing for DSAlgo Register page

  Background: User launches the DSAlgo application and navigates to home page
    When User clicks Register link in home page
    Then user lands on Register page

  Scenario: User must be navigated to home page upon succesfull registration
    When User clicks Register button after entering valid credentials "run3", "Qaninja@123", "Qaninja@123"
    Then User must be navigated to Home page

  Scenario Outline: Registered user must see error message for "<testcase>"
    When User clicks Register button after entering testdata "<username>" , "<password>" ,"<confirmpassword>"
    Then User must see "<errormessage>" in Register UI

    Examples:
      | testcase                                 |  | username |  | password    |  | confirmpassword |  | errormessage                                            |
      | without username                         |  |          |  | Qaninja@123 |  | Qaninja@123     |  | Please fill out this field.                             |
      | with specialcharacter username           |  | !@       |  | Qaninja@123 |  | Qaninja@123     |  | Username must be alphanumeric                           |
      | without password                         |  | qatest   |  |             |  | Qaninja@123     |  | Please fill out this field.                             |
      | without confirm password                 |  | qatest   |  | Qaninja@123 |  |                 |  | Please fill out this field.                             |
      | with numeric password                    |  | qatest   |  | 123         |  | 123             |  | Password does not match requirement                     |
      | with specialcharacter password           |  | qatest   |  | @@@         |  | @@@             |  | Password does not match requirement                     |
      | with password less then eight characters |  | qatest   |  | A@1         |  | A@1             |  | Password does not match requirement                     |
      | with mismatch password                   |  | qatest   |  | Qaninja@123 |  | Ninja@123       |  | password_mismatch:The two password fields didn’t match. |
# Scenario: Registered user must see registered username on top navigation bar in home page
# Given User lands on Register page
# When User clicks Register button after entering valid credentials
# Then User must see the registered username on top navigation bar in home page
# Scenario: Registered user must see Sign out link in the top navigation bar in home page
# Given User lands on Register page
# When User clicks Register button after entering valid credentials
# Then Sign out link must be present next to username in the top navigation bar in home page
# Scenario: Registered user must see success message below the top navigation bar in home page
# Given User lands on Register page
# When User clicks Register button after entering valid credentials
# Then Success message stating "New Account Created. You are logged in as 'username' " must be displayed below the top navigation bar in home page
# Scenario: User must be navigated to login page upon clicking "login" button in Register page
# Given User lands on Register page
# When User clicks "login" button present in the Register page
# Then User must be navigated to login screen successfully.
