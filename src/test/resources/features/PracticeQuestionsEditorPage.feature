Feature: Functional testing for DSAlgo Array Practice Questions Editor page

  Background: Signed in user has navigated to Practice Question UI from Array page

  Scenario: User must be able to navigate to "Search the array" Practice Editor from Practice question page
    Given User is in Practice Questions UI
    When User clicks on  "Search the array" link
    Then User must  be navigated to  "Search the array" practice question editor which contains a question, try Editor section with Run and Submit buttons

  Scenario: User must see output in console  upon running valid code for "Search the array" question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "Run" button
    Then User must see output in the console

  Scenario: User must see alert upon running invalid code for "Search the array" question
    Given User is in Practice Question Editor
    When User enters invalid code and clicks "Run" button
    Then User must see error message in alert window

  Scenario: User must be able to submit successfully upon entering valid code for "Search the array" question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "submit" button
    Then user must see "submission successful" message in console

  Scenario: User must see error message upon submitting invalid code for "search the array" question
    Given User is in Practice Question Editor
    When User enters invalid python code and clicks "submit" button
    Then User must see message stating"Error occurred during submission" in console

  Scenario: User must be able to navigate to "Max Consecutive Ones"Practice Editor from Practice question page
    Given User is in Practice Questions UI
    When User clicks on "Max Consecutive Ones" link
    Then User must  be navigated to "Max Consecutive Ones" practice question editor which contains a question, try Editor section with Run and Submit buttons

  Scenario: User must see output in console  upon running valid code for "Max Consecutive Ones" question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "Run" button
    Then User must see output in the console

  Scenario: User must see alert upon running invalid code for "Max Consecutive Ones" question
    Given User is in Practice Question Editor
    When User enters invalid code and clicks "Run" button
    Then User must see error message in alert window

  Scenario: User must be able to submit successfully upon entering valid code for "Max Consecutive Ones" question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "submit" button
    Then user must see "submission successful" message in console

  Scenario: User must see error message upon submitting invalid code for "Max Consecutive Ones" question
    Given User is in Practice Question Editor
    When User enters invalid python code and clicks "submit" button
    Then User must see message stating"Error occurred during submission" in console

  Scenario: User must be able to navigate to  "Find Numbers with Even number of digits" Practice Editor from Practice question page
    Given User is in Practice Questions UI
    When User clicks on  "Find Numbers with Even number of digits"  link
    Then User must  be navigated to  "Find Numbers with Even number of digits"  practice question editor which contains a question, try Editor section with Run and Submit buttons

  Scenario: User must see output in console  upon running valid code for  "Find Numbers with Even number of digits"  question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "Run" button
    Then User must see output in the console

  Scenario: User must see alert upon running invalid code for  "Find Numbers with Even number of digits"  question
    Given User is in Practice Question Editor
    When User enters invalid code and clicks "Run" button
    Then User must see error message in alert window

  Scenario: User must be able to submit successfully upon entering valid code for  "Find Numbers with Even number of digits"  question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "submit" button
    Then user must see "submission successful" message in console

  Scenario: User must see error message upon submitting invalid code for  "Find Numbers with Even number of digits"  question
    Given User is in Practice Question Editor
    When User enters invalid python code and clicks "submit" button
    Then User must see message stating"Error occurred during submission" in console

  Scenario: User must be able to navigate to "Squares of a sorted Array" Practice Editor from Practice question page
    Given User is in Practice Questions UI
    When User clicks on "Squares of a sorted Array" link
    Then User must  be navigated to "Squares of a sorted Array" practice question editor which contains a question, try Editor section with Run and Submit buttons

  Scenario: User must see output in console  upon running valid code for "Squares of a sorted Array" question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "Run" button
    Then User must see output in the console

  Scenario: User must see alert upon running invalid code for "Squares of a sorted Array" question
    Given User is in Practice Question Editor
    When User enters invalid code and clicks "Run" button
    Then User must see error message in alert window

  Scenario: User must be able to submit successfully upon entering valid code for "Squares of a sorted Array" question
    Given User is in Practice Question Editor
    When User enters valid python code and clicks "submit" button
    Then user must see "submission successful" message in console

  Scenario: User must see error message upon submitting invalid code for "Squares of a sorted Array" question
    Given User is in Practice Question Editor
    When User enters invalid python code and clicks "submit" button
    Then User must see message stating"Error occurred during submission" in console