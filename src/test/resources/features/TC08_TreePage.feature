@Tree_feature @Getstarted @Login
Feature: Tree Page Functionality

  Background:
    Given signed in user has navigated to the "Tree" UI

  Scenario Outline: Verifying static content "<expected_text>" on the Tree page
    Then the user should be able to see "<expected_text>" in Tree page

    Examples:
      | expected_text  |
      | Tree           |
      | Topics Covered |

  Scenario: Verifying topic links under Topics Covered
    Then the user should be able to see Tree topics links under Topics_Covered
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

  Scenario Outline: Navigating to a Tree topic page
    When the user selects Tree "<topic>" Topics Covered
    Then Tree "<page_url>" content should be present

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

  Scenario: Verify "Try here>>>" button presence in UI
    When the user selects Tree "Overview of Trees" Topics Covered
    Then the Try here>>> button should be visible in tree ui


  Scenario: Navigating to the code editor from topic pages
    Given User is on the tree subtopic "Overview of Trees" page
    When User clicks the "Try here>>>" button in tree ui
    Then User must be navigated to tree code editor

  Scenario: Presence of practice question link in "Overview of Trees" UI
    When User clicks on "Overview of Trees" link under Tree Topics Covered section
    Then User must see "Practice Questions" clickable link displayed in the side navigation bar in Tree UI

  Scenario: User must be navigated to Practice Questions page
    When User clicks on Practice Questions link in Tree "Overview of Trees" UI
    Then User must be navigated to "Practice Questions"

  Scenario: List of questions displayed in Practice Questions page
    When User clicks on Practice Questions link in Tree "Overview of Trees" UI
    Then User must see list of question in practice question of Tree
