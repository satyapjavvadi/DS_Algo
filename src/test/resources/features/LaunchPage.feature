Feature: DS Algo Portal functional testing 

Scenario: User opens the launch page
Given the user has a browser open
When the user enters the correct DS Algo portal URL
Then the user should be able to see the DS Algo portal
       
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
Then the user should be navigated to home page 


