@stackfeature @Getstarted
Feature: Stack Page Functionality

  Background:
    Given the registered user has navigated to the Stack page

  Scenario Outline: Verifying static content on the Stack page

    Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text   |
      | Stack           |
      | Topics Covered  |

  Scenario: Verifying topic links under "Topics Covered"

    Then the user should be able to see Stack topics as clickable links under "Topics Covered"

  Scenario Outline: Navigating to a Stack topic page

    When the user selects "<topic>" under Topics Covered
    Then the "<page_url>" content should be visible

    Examples:
      | topic         | page_url            |
      | Operations    | Operations in Stack    |
      | Implementation| Implementation         |
      | Applications  | Stack Applications     |

  Scenario Outline: Scrolling through topic content
    Given the user is on the "<topic_page>" page
    When the user scrolls down
    Then the <expected_content> should be visible

    Examples:
      | topic_page            | expected_content                                                        |
      | Operations in Stack   | detailed explanation for push and pop, including overflow and underflow |
      | Stack Applications    | examples like Reverse Order, Undo, Call Stack, and Parentheses Balance  |

  Scenario Outline: Verifying "Try here>>>" button visibility
    Given the user is on the "<topic_page>" page
    When the user scrolls through the content
    Then the "Try here>>>" button should be visible below the <section> content

    Examples:
      | topic_page            | section               |
      | Operations in Stack   | Operations in Stack   |
      | Implementation        | Implementation        |
      | Stack Applications    | Application           |

  Scenario Outline: Opening the code editor from topic pages
    Given the user is on the "<topic_page>" page
    When the user activates the "Try here>>>" button
    Then the code editor should open

    Examples:
      | topic_page            |
      | Operations in Stack   |
      | Implementation        |
      | Stack Applications    |

  Scenario Outline: Navigating between topics via sidebar
    Given the user is on the "<current_page>" page
    When the user selects "<target_topic>" from the sidebar
    Then the relevant topic content should appear

    Examples:
      | current_page          | target_topic   |
      | Stack Implementation  | Operations     |
      | Stack Implementation  | Applications   |
      | Stack Applications    | Operations     |
      | Stack Applications    | Implementation|

  Scenario: Navigating to Practice Questions page

    When the user selects "Practice Questions" from the sidebar
    Then an appropriate error message should be displayed if the page fails to load
