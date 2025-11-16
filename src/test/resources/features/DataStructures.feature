Feature: DS Algo Portal functional testing 

Background: Signed in user is navigated to Data Structure module from Home Page

Scenario Outline: Verifying static content on the DataStructures page
Given The user is in the Data Structures - Introduction page
#When The Data Structures - Introduction page loads
Then the user should be able to see "<expected_text>"

    Examples:
      | expected_text  |
      | Data Structures-Introduction |
      | Topics Covered |

      
Scenario: Verify that Time Complexity tab is visible and clickable 
Given The user is in the Data Structures - Introduction page
When The user clicks Time Complexity button
Then Time Complexity tab should be visible and clickable

Scenario:Verify that user is able to navigate to Time Complexity page
Given The user is in the Data Structures - Introduction page
When The user clicks Time Complexity button
Then The user should be redirected to Time Complexity page of Data structures-Introduction


Scenario: Verify that Try here tab is visible and clickable
Given The user is in the Time Complexity tab
Then Try here tab should be visible and clickable


Scenario:Verify that user is able to navigate to try Editor page
Given The user is in the Time Complexity tab
When The user clicks Try Here button
Then The user should be redirected to a page having an try Editor with a Run button to test


Scenario: Verify that user gets error message when click on Run button without entering code
Given The user is in the tryEditor page
When The user clicks the Run Button without entering the code in the Editor
Then The user should able to get the error message "Empty Code Editor"
@Singletest
 Scenario Outline: Verify Try Editor response for "<topic_tab>" with "<code_type>" code
    Given User is in "<topic_tab>" UI
    When User enters "<code_type>" code in the Try Editor and clicks on "Run" button
	Then User must see "<expected_result>" in the UI


    Examples:
      | topic_tab                | code_type | expected_result |
      | Time Complexity          | valid     | Hello |
      | Time Complexity          | invalid   | an error popup stating NameError: name 'invalid' is not defined on line 1 |
    
Scenario: Verify that user is able to navigate to Practice Questions page
Given The user is in the Time Complexity tab
When The user clicks the Practice Questions tab
Then The user should be redirected to list of Practice Questions of Data structures-Introduction
