@stackfeature @Getstarted @Login
Feature: Stack Page Functionality

  Background:
    Given the registered user has navigated to the "stack" page

  Scenario Outline: Verifying static content "<expected_text>" on the Stack page
 
   Then the user should be able to see "<expected_text>" in Stack page

    Examples:
      | expected_text     |
      | Stack             |
      | Topics Covered    |


 Scenario: Verifying topic links under Topics Coveredin Stack page

    Then the user should be able to see Stack topics as clickable links under Topics Covered
      | Operations in Stack   | 
      | Implementation        |  
      |  Applications         | 


  Scenario Outline: Navigating to a Stack topic page

    When the user selects Stack "<topic>" under Topics Covered
    Then Stack "<page_url>" content should be visible

    Examples:
      | topic                  | page_url             |
      | Operations in Stack    |  Operations in Stack |
      | Implementation         |  Implementation      |
      |  Applications          |  Applications		  |

  Scenario Outline: Verifying "Try here>>>" button visibility  
    When the user scrolls through the "<topic_page>" content
    Then the Try here>>> button should be visible below the content in Stack

    Examples:
      | topic_page            | 
      | Operations in Stack   | 
      | Implementation        | 
      |  Applications         |  

  Scenario: Opening the code editor from topic pages
    Given the user is on the Stack "Operations in Stack" page
    When the user activates the "Try here>>>" button in Stack page
    Then the code editor should open

  Scenario: Navigating to Practice Questions Stack page  
    When the user selects Practice Questions link in Stack "Operations in Stack"
    Then the user must see list of questions in "Practice questions" of Stack
    
    Scenario: List of questions displayed in Practice Questions Stack 
    When User clicks on Practice Questions link in Stack "Operations in Stack" UI
    Then User must see list of practice questions of Stack
    
    Scenario: Presence of practice question link in "Operations in Stack" Linked List page
    When User clicks on "Operations in Stack" link under Stack Topics Covered section
    Then User must see "Practice Questions" clickable link displayed in the side navigation bar in Stack 