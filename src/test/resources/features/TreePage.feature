@treefeature @Getstarted @Login
Feature: Tree Page Functionality

  Background:
    Given The registered user has navigated to the Tree page

  Scenario Outline: Verifying static content on the Tree page
    Then the user should be able to see "<expected_text>" in Tree page

    Examples:
      | expected_text  |
      | Tree           |
      | Topics Covered |

  Scenario: Validate Tree page subtopic links
    Then user must see all tree subtopics from excel

  
  @Treetopics  
  Scenario Outline: Navigate to Tree topic "<scenario_type>" and validate page URL
   When user navigates to Tree topic "<scenario_type>"
   Then user should land on the correct Tree topic page

  Examples:
    | scenario_type                  |
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

  Scenario Outline: Verify "Try here>>>" button presence in UI
    Given the user is in Tree "<topic_page>" page
    When the user scrolls  the Tree page
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
    When User clicks "Try here>>>" button in UI
    Then User must be navigated to code editor UI

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

  Scenario Outline: Navigating to Practice Questions page
    Given user is on tree topic "<topic_page>" page
    When user clicks "Practice Questions" present in sidebar
    Then page must load with list of questions

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
