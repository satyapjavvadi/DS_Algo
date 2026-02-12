@dataStructurefeature @Getstarted @Login 
Feature: DS Algo Portal functional testing 

Background:
Given The registered user has navigated to the "Data Structures-Introduction" page

Scenario Outline: Verifying static content on the DataStructures page
Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text  |
      | Data Structures-Introduction |
      | Topics Covered |

Scenario: Verify that user is able to navigate to Time Complexity page
When The user clicks "Time Complexity" topic
Then The user should be redirected to "Time Complexity" page

Scenario: Verify that Try here tab is visible and clickable
Given The user is in the "Time Complexity" tab
Then Try here tab should be visible

Scenario: Verify that user is able to navigate to try Editor page
Given The user is in the "Time Complexity" tab
When The user clicks Try Here button
Then The user should be redirected to code editor

Scenario: Verify that user gets error message when click on Run button without entering code
Given The user is in the tryEditor page
When The user clicks the Run button without entering the code in the Editor
Then The user should able to get the error message "Empty Code Editor"

Scenario: Verify that user is able to navigate to Practice Questions page
Given The user is in the "Time Complexity" tab
When The user clicks the Practice Questions tab
Then The user should be redirected to list of Practice Questions of Data structures-Introduction
