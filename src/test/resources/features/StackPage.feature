@stackfeature @Getstarted @Login
Feature: Stack Page Functionality

  Background:
    Given the registered user has navigated to the Stack page

 Scenario: Verifying static content on the Stack page using Excel
  Then the user should be able to see static content from Excel


 Scenario: Navigating to Stack topic pages using Excel
  Then the user should be able to navigate to each Stack topic from Excel

  Scenario: Scroll validation using Excel
  When the user validates scrollable content from Excel



  Scenario Outline: Verifying "Try here>>>" button visibility
    Given the user is on the "<topic_page>" page
    When the user scrolls through the content
    Then the "Try here>>>" button should be visible below the "<section>" content

    Examples:
      | topic_page            | section               |
      | Operations in Stack   | Operations in Stack   |
      | Implementation        | Implementation        |
      |  Applications         |  Applications         |

  Scenario Outline: Opening the code editor from topic pages
    Given the user is on the "<topic_page>" page
    When the user activates the "Try here>>>" button
    Then the code editor should open

    Examples:
      | topic_page            |
      | Operations in Stack   |
      | Implementation        |
      |  Applications         |
      
  Scenario Outline: Running code in Try Editor with Excel data
  Given the user runs all Try Editor scenarios from "StackPageContent" sheet
    

  Scenario Outline: Verifying sidebar navigation on topic pages
    Given the user is on the "<current_page>" page
    When the user selects "<target_topic>" from the sidebar
    Then the relevant topic content should appear "<target_topic>"

    Examples:
      | current_page          | target_topic            |
      | Operations in Stack   | Operations in Stack     |
      | Implementation        | Implementation          |
      |  Applications         |  Applications           |

  Scenario: Navigating to Practice Questions page
    
     Given the user is on the "Applications" page
    When the user selects "Practice Questions" from the sidebar
    Then an appropriate error message should be displayed if the page fails to load
