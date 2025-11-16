Feature: DS Algo Portal functional testing 


Scenario: Verify that user is able to see company name "NumpyNinja" 
Given The user is on the Launch page
When The user clicks the Get Started button
Then The user should be able to see company name "NumpyNinja"

Scenario: Verify that user is able to see " ResisterLink "
Given The user is on the Launch page
When The user clicks the Get Started button
Then The user should be able to see <LinkName> 
Examples:
| LinkName      |
|  ResisterLink  |
| Sign in   |


Scenario:Verify that user is able to view options for "Data Structures" dropdown on home page
Given The user is on the Home page
When The user clicks the Data Structures dropdown 
Then The user should able to see 6 options Arrays "Linked,List,Stack,Queue,Tree,Graph" in dropdown menu


Scenario:Verify that user able to see warning message while selecting "Stack" from the drop down
Given The user is on the Home page
When The user selects <Option> from the drop down without Sign in.
Then The user should able to see an warning message "You are not logged in"
Examples:
| Option   |
|  Stack  |
| Arrays   |
| Queue |
| Tree |
| Graph |


Scenario: Verify that user able to see warning message on clicking Get Started buttons of Array Heading on the home page
Given The user is on the Home page
When The user clicks Get Started buttons of Array on the homepage without Sign in
Then The user should able to see an warning message "You are not logged in"



Scenario: Verify that user able to see warning message on clicking Get Started buttons of Linked List Heading on the home page
Given The user is on the Home page
When The user clicks Get Started buttons of Linked List on the homepage without Sign in
Then The user should able to see an warning message "You are not logged in"



Scenario: Verify that user able to see warning message on clicking Get Started buttons of Stack Heading on the home page
Given The user is on the Home page
When The user clicks Get Started buttons of Stack on the homepage without Sign in
Then The user should able to see an warning message "You are not logged in"



Scenario: Verify that user able to see warning message on clicking Get Started buttons of Queue Heading on the home page
Given The user is on the Home page
When The user clicks Get Started buttons of Queue on the homepage without Sign in
Then The user should able to see an warning message "You are not logged in"



Scenario: Verify that user able to see warning message on clicking Get Started buttons of Tree Heading on the home page
Given The user is on the Home page
When The user clicks Get Started buttons of Tree on the homepage without Sign in
Then The user should able to see an warning message "You are not logged in"




