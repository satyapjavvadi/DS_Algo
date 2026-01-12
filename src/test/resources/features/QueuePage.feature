@queuefeature @Getstarted @Login
Feature: Queue Page Functionality

  Background:
    Given The registered user has navigated to the Queue page

  Scenario Outline: Verifying static content on the Queue page
    Then the user should be able to see "<expected_text>" in Queue page

    Examples:
      | expected_text  |
      | Queue          |
      | Topics Covered |

  Scenario Outline: Verifying topic links under "Topics Covered"
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

  @queueEditor
  Scenario Outline: Validate Queue editor for "<scenario_type>" case on "<topic_page>"
    Given user is on topic Queue "<topic_page>" page
    When user clicks try here button and runs "<scenario_type>" code in the editor
    Then result should be "<expected_result>"

    Examples:
      | scenario_type                                           | topic_page                             | code           | expected_result                                                 |
      | valid code for Implementation of Queue in Python        | Implementation of Queue in Python      | print('hello') | hello                                                           |
      | invalid code for Implementation of Queue in Python      | Implementation of Queue in Python      | abcd           | NameError: name 'abcd' is not defined on line 1                 |
      | valid code for Implementation using collections.deque   | Implementation using collections.deque | print(2+5.0)   | 7.0                                                             |
      | invalid code for Implementation using collections.deque | Implementation using collections.deque | print('a       | SyntaxError: bad input on line 1                                |
      | valid code for Implementation using array               | Implementation using array             | print('abcd')  | abcd                                                            |
      | invalid code for Implementation using array             | Implementation using array             | prin('hi')     | NameError: name 'prin' is not defined on line 1                 |
      | valid code for Queue Operations                         | Queue Operations                       | print('Queue') | Queue                                                           |
      | invalid code for Queue Operations                       | Queue Operations                       | print(5/0)     | ZeroDivisionError: integer division or modulo by zero on line 1 |

  Scenario: Navigating to Practice Questions page
    Given the user is on "<topic_page>" page
    When user clicks "Practice Questions" from the sidebar
    Then page loads with list of questions

    Examples:
      | topic_page                             |
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |
