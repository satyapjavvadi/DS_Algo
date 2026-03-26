@Array_feature @Getstarted @Login
Feature: Array Page Functionality

  Background:
    Given The registered user has navigated to the "Array" page

  Scenario Outline: Verifying static content "<expected_text>" on the Array page
    Then the user should be able to see "<expected_text>" in Array page

    Examples:
      | expected_text  |
      | Array          |
      | Topics Covered |

  Scenario: Verifying topic links under Topics Covered
    Then the user should be able to see Array topics links under Topics_Covered
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |


  Scenario Outline: Navigating to a Array topic page
    When the user selects "<topic>" Topics Covered
    Then the "<page_url>" content should be present

    Examples:
      | topic                     | page_url                  |
      | Arrays in Python          | arrays-in-python          |
      | Arrays Using List         | arrays-using-list         |
      | Basic Operations in Lists | basic-operations-in-lists |
      | Applications of Array     | applications-of-array     |

  Scenario Outline: Verify "Try here>>>" button presence in UI
    When the user selects "<topic_page>" Topics Covered
    Then the Try here>>> button should be visible

    Examples:
      | topic_page                |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |

   Scenario Outline: Navigating to the code editor from topic pages
    Given User is on the array subtopic "<topic_page>" page
    When User clicks the "Try here>>>" button
    Then User must be navigated to code editor

    Examples:
      | topic_page                |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |


  Scenario Outline: Presence of practice question link in "<topic_page>" UI
    When User clicks on "<topic_page>" link under Topics Covered section
    Then User must see "Practice Questions" clickable link displayed in the side navigation bar in UI

    Examples:
      | topic_page                |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |


  Scenario Outline: User must be navigated to Practice Questions page
    When User clicks on Practice Questions link in Array "<sub topic>" UI
    Then User must be navigated to "Practice Questions"

    Examples:
      | sub topic                 |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |


  Scenario: User must see list of Practice Questions for array
    When User clicks on Practice Questions link in Array "Arrays in Python" UI
    Then User must see list of question in practice question of array
    |Search the array|
    |Max Consecutive Ones|
    |Find Numbers with Even Number of Digits|
    |Squares of a Sorted Array|


  Scenario Outline:User must be able to navigate to assessment page for the "<problem_name>"
    Given User has navigated to Practice Question UI from Array page
    When User clicks on "<problem_name>" link in Practice Questions UI
    Then User must be navigated to "Assessment"

    Examples:
      | problem_name                            |
      | Search the array                        |
      | Max Consecutive Ones                    |
      | Find Numbers with Even number of digits |
      | Squares of a sorted Array               |


  Scenario Outline:User must see run button in assessment page
    Given User has navigated to Practice Question UI from Array page
    When User clicks on "<problem_name>" link in Practice Questions UI
    Then User must see "Run" button in assessment page

    Examples:
      | problem_name                            |
      | Search the array                        |
      | Max Consecutive Ones                    |
      | Find Numbers with Even number of digits |
      | Squares of a sorted Array               |


  Scenario Outline:User must see Submit button in assessment page
    Given User has navigated to Practice Question UI from Array page
    When User clicks on "<problem_name>" link in Practice Questions UI
    Then User must see "Submit" button in assessment page

    Examples:
      | problem_name                            |
      | Search the array                        |
      | Max Consecutive Ones                    |
      | Find Numbers with Even number of digits |
      | Squares of a sorted Array               |

  Scenario Outline:User must see error message when submitting empty ans
    Given User has navigated to Practice Question UI from Array page
    When User clicks on submit link after reaching "<problem_name>"
    Then User must see "Error occurred during submission" in output

    Examples:
      | problem_name                            |
      | Search the array                        |
      | Max Consecutive Ones                    |
      | Find Numbers with Even number of digits |
      | Squares of a sorted Array               |
@array1
  Scenario: Navigating to the code editor from topic pages
    Given User is on the Try editor
    When User clicks the Run button after entering "Question1"
    Then User must see "Element Found" in output