Feature: DS Algo Portal functional testing 
Background: User is signed in
Given The user is on the Home page after sign in


Scenario Outline: Verify that user is able to see "<Option>" details page while selecting "<Option>" from the drop down
 
When The user selects "<Option>" from the drop down after Sign in.
Then The user should be able to see "<Option>" page with details.
Examples:
| Option |
| Arrays |
| Linked List |
| Queue |
| Tree |
| Graph |

Scenario Outline: Verify that user is able to see respective page with details on clicking "Get Started" buttons of "<TabName>" tab on the home page
 
When The user clicks "Get Started" buttons of "<TabName>" tab on the homepage after Sign in
Then The user should be able to see "<PageName>" page with details.
Examples:

| TabName | PageName | 
| Data Structures-Introduction | Data Structures-Introduction |
| Array | Array |
| Linked List | Linked List |
| Stack | Stack |
| Queue | Queue |
| Tree | Tree |
| Graph | Graph |
