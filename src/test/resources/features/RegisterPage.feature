Feature: Functional testing for DSAlgo Register page

  Background: User clicks on Register button in Home page after launching the application

  Scenario: Successful registration with valid credentials
    Given User lands on Register page
    When User clicks Register button after entering valid credentials
    Then User must be navigated to Home page

  Scenario: Successful registration with valid credentials
    Given User lands on Register page
    When User clicks Register button after entering valid credentials
    Then User must see the registered username on top navigation bar in home page

  Scenario: Successful registration with valid credentials
    Given User lands on Register page
    When User clicks Register button after entering valid credentials
    Then Sign out link must be present next to username in the top navigation bar in home page

  Scenario: Successful registration with valid credentials
    Given User lands on Register page
    When User clicks Register button after entering valid credentials
    Then Success message stating "New Account Created. You are logged in as 'username' " must be displayed below the top navigation bar in hom page

  Scenario: Attempting registration without username
    Given User lands on Register page
    When User clicks Register button without filling up username field
    Then user must see "Please fill out this field" tooltip below username field

  Scenario: Attempting registration with specialcharacternumeric username
    Given User lands on Register page
    When User clicks Register button with username as special character
    Then user must see "password_mismatch:The two password fields didn’t match." message below the registraion section

  Scenario: Attempting registration without password
    Given User lands on Register page
    When User clicks Register button without filling up password field
    Then user must see "Please fill out this field" tooltip below password field

  Scenario: Attempting registration without password confirmation
    Given User lands on Register page
    When User clicks Register button without filling up password confirmation field
    Then user must see "Please fill out this field" tooltip below password confirmation field

  Scenario: Attempting registration with numeric password
    Given User lands on Register page
    When User clicks Register button with entering  numeric password
    Then user must see "password_mismatch:The two password fields didn’t match." message below the registraion section

  Scenario: Attempting registration with  special characters password
    Given User lands on Register page
    When User clicks Register button with entering special character password
    Then user must see "password_mismatch:The two password fields didn’t match." message below the registraion section

  Scenario: Attempting registration with password less than 8 characters
    Given User lands on Register page
    When User clicks Register button with password less than 8 characters
    Then user must see "password_mismatch:The two password fields didn’t match." message below the registraion section

  Scenario: Attempting registration with mismatch password
    Given User lands on Register page
    When User clicks Register button with mismatch password
    Then user must see "password_mismatch:The two password fields didn’t match." message below the registraion section

  Scenario: Navigating to login page from Register page
    Given User lands on Register page
    When User clicks login button present in the Register page
    Then User must be navigated to login screen successfully.
