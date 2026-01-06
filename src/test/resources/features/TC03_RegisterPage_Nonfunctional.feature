@Registernonfunctional @Getstarted
Feature: Non Functional testing for DSAlgo Register page

  Background: User launches the DSAlgo application and lands on home page
    When User clicks Register link in home page
    Then user lands on Register page

  Scenario: Number of fields present in Register UI
    Then User must see 3 input fields in Register UI

  Scenario: Validate labels text in Register UI
    Then User must see labels with text in Register UI
      | Username:              |
      | Password:             |
      | Password confirmation: |

  Scenario: Number of buttons present in Register UI
    Then User must see 1 button in Register UI

  Scenario: The button should have text "Register"
    Then User should be able to see button with text "Register" in Register UI

  Scenario: validate links text in Register UI
    Then User must see links with text in Register UI
      | Register |
      | Sign in  |
      | Login    |

  Scenario: Company name must be  present in top nav bar of Register UI
    Then User must see "Numpy Ninja" company name in top nav bar of  Register UI

  Scenario: Validate presence of pre-requisite conditions list for password entry field in Register UI
    Then User must see list items for password entry field in Register UI
      | Your password can't be too similar to your other personal information. |
      | Your password must contain at least 8 characters.                      |
      | Your password can't be a commonly used password.                       |
      | Your password can't be entirely numeric                                |
