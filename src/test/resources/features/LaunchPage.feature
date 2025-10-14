@refFeature
Feature: DS Algo Portal functional testing

Background: User launches the browser and navigates to DS Algo portal

#background steps are already implemented in driver factory class in init method

Scenario: Endpoint of the launch page URL
Given the user has a browser open
When the user enters the correct DS Algo portal URL
Then the user should be on the DS Algo Portal page

Scenario: Content text on the Launch page
Given the user is on the DS Algo Portal page
When the launch page loads
Then the user should be able to see the content text on the Launch page
| Preparing for the Interviews|
| Copyright@NumpyNinja2021|


Scenario: Launch page should have 1 button
Given the user is on the DS Algo Portal page
When the launch page loads
Then the user should be able to see 1 button on the Launch page

Scenario: The button should have text "Get Started"
Given the user is on the DS Algo Portal page
When the launch page loads    
Then the user should be able to see button with text "Get Started"

