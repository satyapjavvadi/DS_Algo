@LinkedListfeature @Getstarted @Login
Feature: Linked List Page Functionality


  Background:
    Given the registered user has navigated to the "Linked List" page

  Scenario Outline: Verifying static content "<expected_text>" on the Linked List page

    Then the user should be able to see "<expected_text>" in LinkedList page

    Examples:
      | expected_text  |
      | Linked List    |
      | Topics Covered |

  Scenario: Verifying topic links under Topics Covered

    Then the user should be able to see Linked List topics as clickable links under Topics Covered
      | Introduction                    |
      | Creating Linked LIst            |
      | Types of Linked List            |
      | Implement Linked List in Python |
      | Traversal                       |
      | Insertion                       |
      | Deletion                        |

  Scenario Outline: Navigating to a Linked List topic page

    When the user selects Linked List "<topic>" under Topics Covered
    Then Linked List "<topic>" content should be visible

    Examples:
      | topic                           |
      | Introduction                    |
      | Creating Linked List            |
      | Types of Linked List            |
      | Implement Linked List in Python |
      | Traversal                       |
      | Insertion                       |
      | Deletion                        |


  Scenario: Verifying "Try here>>>" button visibility

    When the user selects "Introduction" topics covered
    Then the Try here>>> button should be visible below the content


  Scenario: Opening the code editor from topic pages
    Given the user is on the "Introduction" page
    When the user activates the Try here button in Linked List page
    Then User should see the code "tryEditor" open

  Scenario: Presence of practice question link in "Introduction" Linked List page
    When User clicks on "Introduction" link under Linked List Topics Covered section
    Then User must see "Practice Questions" clickable link displayed in the side navigation bar in Linked List UI


  Scenario: Navigating to Practice Questions page

    When the user selects Practice Questions link in Linked List "Introduction"
    Then User must see title as "Practice questions" of Linked List

  Scenario: List of questions displayed in Practice Questions page
    When the user selects Practice Questions link in Linked List "Introduction"
    Then User must see list of practice questions of Linked List

 

