@queuefeature @Getstarted
Feature: Queue Page Functionality

  Background:
    Given The registered user has navigated to the Queue page

  
  Scenario Outline: Verifying static content on the Queue page
    Then the user should be able to see "<expected_text>" in Queue page

    Examples:
      | expected_text  |
      | Queue          |
      | Topics Covered |

  Scenario: Verifying topic links under "Topics Covered"
    Then the user should be able to see Queue topics as clickable links under "<Topics Covered>" section

    Examples:
      | Topics Covered                         |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Navigating to a Queue topic page
    When the user selects "<topic>" from Topics Covered
    Then the "<page_url>" content must be present

    Examples:
      | topic                                  | page_url                   |
      | Implementation of Queue in Python      | implementation-lists       |
      | Implementation using collections.deque | implementation-collections |
      | Implementation using array             | Implementation-array       |
      | Queue Operations                       | QueueOp                    |

  Scenario Outline: Verify "Try here>>>" button presence in UI
    Given the user is on the "<topic_page>" UI
    When the user scrolls down the page
    Then the "Try here>>>" button must be visible below the page content

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Navigating to the code editor from topic pages
    Given User is on the queue subtopic "<topic_page>" UI
    When User clicks "Try here>>>" button
    Then User must navigate to code editor

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario: Verify Try Editor responses for all Queue topics
    When User validates Try Editor using Excel data
    Then Validation must be completed successfully

  Scenario Outline: Navigating to Practice Questions page
    Given the user is on "<topic_page>" page
    When user clicks "Practice Questions" from the sidebar
    Then page loads with list of questions

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |
