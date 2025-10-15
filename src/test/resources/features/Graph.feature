Feature: DS Algo Portal functional testing 

background: Signed in user is navigated to Graph module from Home Page

scenario: Verify that user is able to see and click all links uder "Topics covered"   
Given The user is on the "Graph" page
When User scrolls down the page
Then "Graph" and "graph representations" link under the topics covered should be visible and clickable

scenario: Verify that user is able to navigate to "Graph" page
Given The user is on the "Graph" page
When The user clicks "Graph" tab
Then The user should be redirected to "Graph" page with related details

scenario: Verify that user is able to navigate to "Graph Representations" page
Given The user is on the "Graph" page
When The user clicks "Graph Representations" tab
Then The user should be redirected to "Graph Representations" page with related details

scenario: Verify that "Try here" tab is visible and clickable
Given The user is on the "Graph" page
When The user clicks "Try Here" button in Graph in page
Then "Try here" button should be visible and clickable

scenario: Verify that user is able to navigate to "try here" page for "Graph" page
Given The user is on the "Graph" page
When The user clicks "Try Here" button in Graph in page
Then The user should be redirected to a page having an try Editor with a Run button to test

scenario: Verify that Run button is visible and clickable
Given The user is on the "Graph" page
When The user clicks "Try Here" button in Graph in page
Then Run button should be visible and clickable

scenario: Verify that no action is performed when click on Run button without entering code
Given The user is in the tryEditor page
When The user clicks the Run Button without entering the code in the Editor
Then The user should able to see the state of the page remains unchanged

scenario: Verify that user receives error for invalid python code
Given The user is in the tryEditor page
When The user write the invalid code in Editor and click the Run Button
Then The user should able to see an error message "NameError: name 'hello' is not defined on line 1" in alert window

scenario: Verify that user is able to see output for valid python code
Given The user is in the tryEditor page
When The user write the valid code in Editor and click the Run Button
Then The user should able to see output in the console

scenario: Verify that user is able to navigate to "Practice Questions" page
Given The user is in the "Graph" page
When The user clicks the "Practice Questions" button
Then The user should be redirected tolist of Practice Questions of Graph page.