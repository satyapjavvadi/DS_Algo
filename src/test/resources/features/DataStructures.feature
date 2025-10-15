Feature: DS Algo Portal functional testing 

Background: Signed in user is navigated to Data Structure module from Home Page

Scenario: Verify that user is able to navigate to "Data Structures - Introduction" page
Given The user is in the Home page after logged in
When The user select Data Structures - Introduction item from the drop down menu
Then The user should land in "Data Structures- Introduction Page" 

Scenario: Verify that "Time Complexity" tab is visible and clickable 
Given The user is in the "Data Structures - Introduction" page
When The user clicks "Time Complexity" button
Then "Time Complexity" tab should be visible and clickable

Scenario:Verify that user is able to navigate to "Time Complexity" page
Given The user is in the "Data Structures - Introduction" page
When The user clicks "Time Complexity" button
Then The user should be redirected to "Time Complexity" page of Data structures-Introduction

Scenario: Verify that "Try here" tab is visible and clickable
Given The user is in the "Time Complexity" tab
When The user clicks "Try Here" button
Then "Try here" tab should be visible and clickable

Scenario:Verify that user is able to navigate to "try Editor" page
Given The user is in the "Time Complexity" tab
When The user clicks "Try Here" button
Then The user should be redirected to a page having an try Editor with a Run button to test

Scenario: Verify that no action is performed when click on Run button without entering code
Given The user is in the tryEditor page
When The user clicks the Run Button without entering the code in the Editor
Then The user should able to see the state of the page remains unchanged

Scenario: Verify that user receives error for invalid python code
Given The user is in the tryEditor page
When The user write the invalid (wrong syntax) code in Editor and click the Run Button
Then The user should able to see an error message "NameError: name 'hello' is not defined on line 1" in alert window

Scenario:Verify that user is able to see output for valid python code
Given The user is in the tryEditor page
When The user write the valid code in Editor and click the Run Button
Then The user should able to see output in the console

Scenario: Verify that user is able to navigate to "Practice Questions" page
Given The user is in the "Time Complexity" page
When The user clicks the "Practice Questions" button
Then The user should be redirected to list of Practice Questions of Data structures-Introduction
