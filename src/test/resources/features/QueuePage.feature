@queuefeature @Getstarted
Feature: Queue Page Functionality

  Background:
    Given The registered user has navigated to the Queue page

  Scenario Outline: Verifying static content on the Queue page
    Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text  |
      | Queue          |
      | Topics Covered |

  Scenario: Verifying topic links under "Topics Covered"
    Then the user should be able to see Queue topics as clickable links under "Topics Covered"

  Scenario Outline: Navigating to a Queue topic page
    When the user selects "<topic>" under Topics Covered
    Then the "<page_url>" content should be present

    Examples:
      | topic                                  | page_url                   |
      | Implementation of Queue in Python      | implementation-lists       |
      | Implementation using collections.deque | implementation-collections |
      | Implementation using array             | Implementation-array       |
      | Queue Operations                       | QueueOp                    |

  Scenario Outline: Verify "Try here>>>" button presence in UI
    Given the user is on the "<topic_page>" page
    When the user scrolls through the page
    Then the "Try here>>>" button should be visible below the page content

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Navigating to the code editor from topic pages
    Given User is on the "<topic_page>" page
    When User clicks the "Try here>>>" button
    Then User must be navigated to code editor

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Verify Try Editor response for "<topic_page>" with "<code_type>" code
    Given User is in "<topic_page>" UI
    When User enters "<code_type>" code in the Try Editor and clicks on "Run" button
    Then User must see "<expected_result>" in the UI

    Examples:
      | topic_page                             | code_type | expected_result                                                          |
      | Implementation of Queue in Python      | valid     | the expected output is displayed successfully                            |
      | Implementation of Queue in Python      | invalid   | an error popup stating "NameError: name 'text' is not defined on line 1" |
      | Implementation using collections.deque | valid     | the expected output is displayed successfully                            |
      | Implementation using collections.deque | invalid   | an error popup stating "NameError: name 'text' is not definedon line 1"  |
      | Implementation using array             | valid     | the expected output is displayed successfully                            |
      | Implementation using array             | invalid   | an error popup stating "NameError: name 'text' is not defined on line 1" |
      | Queue Operations                       | valid     | the expected output is displayed successfully                            |
      | Queue Operations                       | invalid   | an error popup stating "NameError: name 'text' is not defined on line 1" |

  Scenario Outline: User must be able to view practice questions link after navigating to "<topic_page>" UI
    When User clicks on "<topic_page>" link under Topics Covered section
    Then User must see "Practice Questions" link displayed in the side navigation bar in UI

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario: User must be able to view Practice questions link in Queue sub topic UI
    Then User must view "Practice Questions" as clickable link in side nav bar

  Scenario: User must be navigated to Practice Questions page
    When User clicks on "Pratice Questions" link in Queue sub topic UI
    Then User must be navigated to "Pratice Questions" link containing list of questions
