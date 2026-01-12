@arrayfeature @Getstarted @Login
Feature: Array Page Functionality

  Background:
    Given The registered user has navigated to the Array page

  Scenario Outline: Verifying static content on the Array page
    Then the user should be able to see "<expected_text>" in Array page

    Examples:
      | expected_text  |
      | Array          |
      | Topics Covered |

  Scenario Outline: Verifying topic links under "Topics Covered"
    Then the user should be able to see Array topics as clickable links under "Topics_Covered"

    Examples:
      | Topics_Covered            |
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
    Given the user is on the "<topic_page>" subtopic array page
    When the user scrolls the array topic page
    Then the "Try here>>>" button should be visible below the "<section>" content

    Examples:
      | topic_page                | section                   |
      | Arrays in Python          | Arrays in Python          |
      | Arrays Using List         | Arrays Using List         |
      | Basic Operations in Lists | Basic Operations in Lists |
      | Applications of Array     | Applications of Array     |

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

  Scenario Outline: User must be able to view practice questions link after navigating to "<topic_page>" UI
    When User clicks on "<topic_page>" link under Topics Covered section
    Then User must see "Practice Questions" clickable link displayed in the side navigation bar in UI

    Examples:
      | topic_page                |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |

  Scenario: User must be navigated to Practice Questions page
    When User clicks on "Pratice Questions" link in Array "<sub topic>" UI
    Then User must be navigated to "Pratice Questions" link containing list of questions

    Examples:
      | sub topic                 |
      | Arrays in Python          |
      | Arrays Using List         |
      | Basic Operations in Lists |
      | Applications of Array     |
