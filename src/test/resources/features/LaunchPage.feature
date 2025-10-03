Feature: DS Algo Portal functional testing 

Scenario: User opens the launch page
Given the user has a browser open
When the user enters the correct DS Algo portal URL
Then the user should be able to see the DS Algo portal with Preparing for the Interviews, Get Started button and Copyright info
       
Scenario: Launch page shows "Preparing for the Interviews" 
Given the user is on the DS Algo Portal page
When the launch page loads
Then the user should be able to see "Preparing for Interviews"

Scenario: Get Started button is visible
Given the user is on the DS Algo Portal page
When the launch page loads
Then the user should be able to see "Get Started" button

Scenario: Copyright info is displayed
Given the user is on the DS Algo Portal page
When the launch page loads
Then the user should be able to see "Copyright info"

Scenario: Navigation from Launch to home Page 
Given the user is on the DS Algo Portal page
When the user clikcs the "Get Started " button
Then the user shold be navigated to home page 


Scenario Outline: Presence of "<links>" link in home page 
Given the user is in launch page 
When the  user clicks on "Get Started " button
Then the user should see "<links>"

Examples: 
 |links|
 |Numpy ninja|
 |Data Structures|
 |Register|
 |Sign in|