@practicequestionseditor @Getstarted @Login
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
