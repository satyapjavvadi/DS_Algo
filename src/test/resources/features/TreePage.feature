@treefeature @Getstarted
Feature: Tree Page Functionality

  Background:
    Given The registered user has navigated to the Tree page

  Scenario Outline: Verifying static content on the Tree page
    Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text  |
      | Tree           |
      | Topics Covered |

  Scenario: Verifying topic links under "Topics Covered"
    Then the user should be able to see Tree topics as clickable links under "Topics Covered"

  Scenario Outline: Navigating to a Tree topic page
    When the user selects "<topic>" under Topics Covered
    Then the "<page_url>" content should be present

    Examples:
      | topic                          | page_url                       |
      | Overview of Trees              | overview-of-trees              |
      | Terminologies                  | terminologies                  |
      | Types of Trees                 | types-of-trees                 |
      | Tree Traversals                | tree-traversals                |
      | Traversals-Illustration        | traversals-illustration        |
      | Binary Trees                   | binary-trees                   |
      | Types of Binary Trees          | types-of-binary-trees          |
      | Implementation in Python       | implementation-in-python       |
      | Binary Tree Traversals         | binary-tree-traversals         |
      | Implementation of Binary Trees | implementation-of-binary-trees |
      | Applications of Binary trees   | applications-of-binary-trees   |
      | Binary Search Trees            | binary-search-trees            |
      | Implementation Of BST          | implementation-of-bst          |

  Scenario Outline: Verify "Try here>>>" button presence in UI
    Given the user is on the "<topic_page>" page
    When the user scrolls through the page
    Then the "Try here>>>" button should be visible below the page content

    Examples:
      | topic_page                     |
      | Overview of Trees              |
      | Terminologies                  |
      | Types of Trees                 |
      | Tree Traversals                |
      | Traversals-Illustration        |
      | Binary Trees                   |
      | Types of Binary Trees          |
      | Implementation in Python       |
      | Binary Tree Traversals         |
      | Implementation of Binary Trees |
      | Applications of Binary trees   |
      | Binary Search Trees            |
      | Implementation Of BST          |

  Scenario Outline: Navigating to the code editor from topic pages
    Given User is on the "<topic_page>" page
    When User clicks the "Try here>>>" button
    Then User must be navigated to code editor

    Examples:
      | topic_page                     |
      | Overview of Trees              |
      | Terminologies                  |
      | Types of Trees                 |
      | Tree Traversals                |
      | Traversals-Illustration        |
      | Binary Trees                   |
      | Types of Binary Trees          |
      | Implementation in Python       |
      | Binary Tree Traversals         |
      | Implementation of Binary Trees |
      | Applications of Binary trees   |
      | Binary Search Trees            |
      | Implementation Of BST          |

  # Scenario Outline: Verify Try Editor response for "<topic_page>" with "<code_type>" code
  # Given User is in "<topic_page>" UI
  # When User enters "<code_type>" code in the Try Editor and clicks on "Run" button
  # Then User must see "<expected_result>" in the UI
  Scenario Outline: User must be able to view practice questions link after navigating to "<topic_page>" UI
    When User clicks on "<topic_page>" link under Topics Covered section
    Then User must see "Practice Questions" link displayed in the side navigation bar in UI

    Examples:
      | topic_page                     |
      | Overview of Trees              |
      | Terminologies                  |
      | Types of Trees                 |
      | Tree Traversals                |
      | Traversals-Illustration        |
      | Binary Trees                   |
      | Types of Binary Trees          |
      | Implementation in Python       |
      | Binary Tree Traversals         |
      | Implementation of Binary Trees |
      | Applications of Binary trees   |
      | Binary Search Trees            |
      | Implementation Of BST          |

  Scenario: User must be able to view Practice questions link in Tree sub topic UI
    Then User must view "Practice Questions" as clickable link in side nav bar

  Scenario: User must be navigated to Practice Questions page
    When User clicks on "Pratice Questions" link in Tree sub topic UI
    Then User must be navigated to "Pratice Questions" link containing list of questions
