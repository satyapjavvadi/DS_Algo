Feature: DS Algo Portal functional testing 

Background : 
Given The user is on the Launch page
When The user clicks the Get Started button

Scenario: Verify that user is able to see company name "NumpyNinja" 
 
Then The user should be able to see company name "NumpyNinja"

Scenario Outline: Verify that user is able to see "ResisterLink"

Then The user should be able to see "<LinkName>"
Examples:
| LinkName      |
| ResisterLink  |
| Sign in   |


Scenario: Verify that user is able to view options for "Data Structures" dropdown on home page
Given The user is on the Home page
When The user clicks the Data Structures dropdown 
Then The user should able to see 6 options in dropdown menu
|Arrays|
|Linked List|
|Stack|
|Queue|
|Tree|
|Graph|


Scenario Outline: Verify that user able to see warning message while selecting "<Option>" from the drop down
Given The user is on the Home page
When The user selects "<Option>" from the drop down without Sign in.
Then The user should able to see an warning message "You are not logged in"
Examples:
| Option   |
|  Stack  |
| Arrays   |
| Queue |
| Tree |
| Graph |


@smoke
Scenario Outline: Verify that user able to see warning message on clicking Get Started buttons of "<Heading>" on the home page
Given The user is on the Home page
When The user clicks Get Started buttons of "<Heading>" on the homepage without Sign in
Then The user should able to see an warning message "You are not logged in"

Examples
| Heading |
|Data structure|
| Array |
| Linked List |
| Stack |
| Queue |
| Tree |
|Graph|


