@arrayfeature @Getstarted
Feature: Array Page Functionality

  Background:
    Given The registered user has navigated to the Array page

  Scenario Outline: Verifying static content on the Array page
    Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text  |
      | Array          |
      | Topics Covered |

  Scenario: Verifying topic links under "Topics Covered"
    Then the user should be able to see Array topics as clickable links under "Topics Covered"

  Scenario Outline: Navigating to a Array topic page
    When the user selects "<topic>" under Topics Covered
    Then the "<page_url>" content should be present

    Examples:
      | topic                     | page_url                  |
      | Arrays in Python          | arrays in Python          |
      | Arrays Using List         | arrays Using List         |
      | Basic Operations in Lists | basic Operations in Lists |
      | Applications of Array     | applications of Array     |

  Scenario Outline: Verify "Try here>>>" button presence in UI
    Given the user is on the "<topic_page>" page
    When the user scrolls through the content
    Then the "Try here>>>" button should be visible below the "<section>" content

    Examples:
      | topic_page                | section                   |
      | Arrays in Python          | Arrays in Python          |
      | Arrays Using List         | Arrays Using List         |
      | Basic Operations in Lists | Basic Operations in Lists |
      | Applications of Array     | Applications of Array     |

  Scenario Outline: Navigating to the code editor from topic pages
    Given User is on the "<topic_page>" page
    When User clicks the "Try here>>>" button
    Then User must be navigated to code editor

    Examples:
      | topic_page                |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |

  Scenario Outline: Verify Try Editor response for "<topic_page>" with "<code_type>" code
    Given User is in "<topic_page>" UI
    When User enters "<code_type>" code in the Try Editor and clicks on "Run" button
    Then User must see "<expected_result>" in the UI

    Examples:
      | topic_page                | code_type | expected_result                                                          |
      | Arrays in Python          | valid     | the expected output is displayed successfully                            |
      | Arrays in Python          | invalid   | an error popup stating "NameError: name 'text' is not defined on line 1" |
      | Arrays Using List         | valid     | the expected output is displayed successfully                            |
      | Arrays Using List         | invalid   | an error popup stating "NameError: name 'text' is not definedon line 1"  |
      | Basic Operations in Lists | valid     | the expected output is displayed successfully                            |
      | Basic Operations in Lists | invalid   | an error popup stating "NameError: name 'text' is not defined on line 1" |
      | Applications of Array     | valid     | the expected output is displayed successfully                            |
      | Applications of Array     | invalid   | an error popup stating "NameError: name 'text' is not defined on line 1" |

  Scenario Outline: User must be able to view practice questions link after navigating to "<topic_page>" UI
    When User clicks on "<topic_page>" link under Topics Covered section
    Then User must see "Practice Questions" link displayed in the side navigation bar in UI

    Examples:
      | topic_page                |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |

  Scenario: User must be able to view Practice questions link in Array sub topic UI
    Then User must view "Practice Questions" as clickable link in side nav bar

  Scenario: User must be navigated to Practice Questions page
    When User clicks on "Pratice Questions" link in Array sub topic UI
    Then User must be navigated to "Pratice Questions" link containing list of questions
