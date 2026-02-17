@Queue_feature @Getstarted @Login
Feature: Queue Page Functionality

  Background:
    Given authenticated user has navigated to the "Queue" ui

  Scenario Outline: Verifying static content "<expected_text>" on the Queue page
    Then the user should be able to see "<expected_text>" in Queue page

    Examples:
      | expected_text  |
      | Queue          |
      | Topics Covered |

  Scenario: Verifying topic links under Topics Covered
    Then the user should be able to see Queue topics links under Topics_Covered
      | Implementation of Queue in Python      |
      | Implementation using collections.deque |
      | Implementation using array             |
      | Queue Operations                       |

  Scenario Outline: Navigating to a Queue topic page
    When the user selects Queue "<topic>" Topics Covered
    Then Queue "<page_url>" content should be present

    Examples:
      | topic                                  | page_url                   |
      | Implementation of Queue in Python      | implementation-lists       |
      | Implementation using collections.deque | implementation-collections |
      | Implementation using array             | Implementation-array       |
      | Queue Operations                       | QueueOp                    |

  Scenario: Verify "Try here>>>" button presence in UI
    When the user selects Queue "Implementation of Queue in Python" Topics Covered
    Then the Try here>>> button should be visible in queue ui

  Scenario: Navigating to the code editor from topic pages
    Given User is on the queue subtopic "Implementation of Queue in Python" page
    When User clicks the "Try here>>>" button in queue ui
    Then User must be navigated to queue code editor


  Scenario: Presence of practice question link in "Implementation of Queue in Python" UI
    When User clicks on "Implementation of Queue in Python" link under Queue Topics Covered section
    Then User must see "Practice Questions" clickable link displayed in the side navigation bar in Queue UI


  Scenario: User must be navigated to Practice Questions page
    When User clicks on Practice Questions link in Queue "Implementation of Queue in Python" UI
    Then User must be navigated to Queue "Practice Questions"


  Scenario: List of questions displayed in Practice Questions page
    When User clicks on Practice Questions link in Queue "Implementation of Queue in Python" UI
    Then User must see list of question in practice question of Queue
