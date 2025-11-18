@practicequestionseditor @Getstarted
Feature: Practice Questions Editor page fuctionality

  Background:
    Given Registered user has navigated to Practice Question UI from Array page

  Scenario Outline: User must be able to navigate to "<problem_name>" Practice Editor from Practice Questions page
    When User clicks on "<problem_name>" link in Practice Questions UI
    Then User must be navigated to "<problem_name>" practice question editor which contains a question, try Editor section with Run and Submit buttons

    Examples:
      | problem_name                            |
      | Search the array                        |
      | Max Consecutive Ones                    |
      | Find Numbers with Even number of digits |
      | Squares of a sorted Array               |

  Scenario Outline: Verify Practice Question Editor response for "<problem_name>" when running "<code_type>" code
    When User enters "<code_type>" python code and clicks "Run" button
    Then User must see "<expected_result>" in the UI

    Examples:
      | problem_name                            | code_type | expected_result               |
      | Search the array                        | valid     | output in the console         |
      | Search the array                        | invalid   | error message in alert window |
      | Max Consecutive Ones                    | valid     | output in the console         |
      | Max Consecutive Ones                    | invalid   | error message in alert window |
      | Find Numbers with Even number of digits | valid     | output in the console         |
      | Find Numbers with Even number of digits | invalid   | error message in alert window |
      | Squares of a sorted Array               | valid     | output in the console         |
      | Squares of a sorted Array               | invalid   | error message in alert window |

  Scenario Outline: Verify Practice Question Editor response for "<problem_name>" when submitting "<code_type>" code
    When User enters "<code_type>" python code and clicks "Submit" button
    Then User must see "<expected_result>" in the UI

    Examples:
      | problem_name                            | code_type | expected_result                  |
      | Search the array                        | valid     | submission successful            |
      | Search the array                        | invalid   | Error occurred during submission |
      | Max Consecutive Ones                    | valid     | submission successful            |
      | Max Consecutive Ones                    | invalid   | Error occurred during submission |
      | Find Numbers with Even number of digits | valid     | submission successful            |
      | Find Numbers with Even number of digits | invalid   | Error occurred during submission |
      | Squares of a sorted Array               | valid     | submission successful            |
      | Squares of a sorted Array               | invalid   | Error occurred during submission |
