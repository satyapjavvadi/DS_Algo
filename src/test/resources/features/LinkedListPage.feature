@LinkedListfeature @GetStarted @Login
Feature: Linked List Page Functionality

 
  Background:
    Given the signed-in user has navigated to the Linked List page

  Scenario Outline: Verifying static content on the Linked List page
 
   Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text     |
      | Linked List       |
      | Topics Covered    |

  Scenario: Verifying topic links under "Topics Covered"

    Then the user should be able to see Linked List topics as clickable links under "Topics Covered"

  Scenario Outline: Navigating to a Linked List topic page

    When the user selects "<topic>" under Topics Covered
    Then the "<page_url>" content should be visible

    Examples:
      | topic                          | page_url                            |
      | Introduction                   | Linked List                         |
      | Creating Linked List           | Creating Linked List                |
      | Types of Linked List           | Types of Linked List                |
      | Implement Linked List in Python| Implement Linked List in Python     |
      | Traversal                      | Traversal                           |
      | Insertion                      | Insertion                           |
      | Deletion                       | Deletion                            |

  Scenario Outline: Verifying sidebar navigation on topic pages
    Given the user is on the "<current_page>" page
    When the user selects "<target_topic>" from the sidebar
    Then the relevant topic content should appear "<target_topic>"

       Examples:
      | current_page                   | target_topic                    |
      | Introduction                   |  Introduction                   |
      | Creating Linked LIst           |  Creating Linked LIst           |
      | Types of Linked List           |  Types of Linked List           |
      | Implement Linked List in Python|  Implement Linked List in Python|
      | Traversal                      |  Traversal                      |
      | Insertion                      |  Insertion                      |
      | Deletion                       |  Deletion                       |

  Scenario Outline: Verifying "Try here>>>" button visibility
    Given the user is on the "<topic_page>" page
    When the user scrolls through the content
    Then the "Try here>>>" button should be visible below the "<section>" content

    Examples:
      | topic_page                     | section                         |
      | Introduction                   | Introduction                    |
      | Creating Linked List           | Creating Linked List            |
      | Types of Linked List           | Types of Linked List            |
      | Implement Linked List in Python| Implement Linked List in Python |
      | Traversal                      | Traversal                       |
      | Insertion                      | Insertion                       |
      | Deletion                       | Deletion                        |

  Scenario Outline: Opening the code editor from topic pages
    Given the user is on the "<topic_page>" page
    When the user activates the "Try here>>>" button
    Then the code editor should open

    Examples:
      | topic_page                     |
      | Introduction                   |
      | Creating Linked List           |
      | Types of Linked List           |
      | Implement Linked List in Python|
      | Traversal                      |
      | Insertion                      |
      | Deletion                       |
      
 Scenario Outline: Running code in Try Editor with Excel data
  Given the user runs all Try Editor scenarios from "LinkedListPageContent" sheet

    

  Scenario Outline: Scrolling through code explanations
    Given the user is on the "<topic_page>" page
    When the user scrolls down
    Then the "<expected_content>" should be visible

    Examples:
      | topic_page                     | expected_content           |
      | Creating Linked List           | append, explanation        |
      | Implement Linked List in Python| Node, SLinkedList          |
      | Traversal                      | Python, code               |
      | Insertion                      | insert, position           |
      | Deletion                       | delete,output              |

  Scenario: Navigating to Practice Questions page
    Given the user is on the Linked List Introduction page
    When the user selects "Practice Questions" from the sidebar
    Then an appropriate error message should be displayed if the page fails to load
