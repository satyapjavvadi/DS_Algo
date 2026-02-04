@homeSigninfeature @Getstarted @Login 
Feature: DS Algo Portal functional testing 
Background: 
Given the registered user has navigated to the home page

@21
Scenario Outline: Verify that user is able to see "<Links>" on the right corner of the Home page
Then The user should be able to see "<Links>" on the right corner of the Home page
Examples:
| Links |
| ValidUser |
| Sign out |

Scenario Outline: Verify that user is able to see "<OptionName>" details page while selecting "<Option>" from the drop down	
When The user selects "<Option>" from the drop down after Sign in.
Then The user should be able to see "<OptionName>" page with details.
Examples:
| Option | OptionName|
| Arrays |Array|
| Linked List |Linked List|
| Queue | Queue |
| Tree | Tree  |
| Graph | Graph |
 | Stack | Stack |
 

Scenario Outline: Verify that user is able to see respective page with details on clicking "Get Started" buttons of "<TabName>" tab on the home page
When The user clicks Get Started buttons of "<TabName>" tab on the homepage after Sign in
Then The user should be able to see "<OptionName>" page with details.
Examples:

| TabName | OptionName | 
| Data Structures-Introduction | Data Structures-Introduction |
| Array | Array |
| Linked List | Linked List |
| Stack | Stack |
| Queue | Queue |
| Tree | Tree |
| Graph | Graph |
 
