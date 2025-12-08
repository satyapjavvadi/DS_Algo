@graphfeature @Getstarted 
 Feature: DS Algo Portal functional testing 

background: Signed in user is navigated to Graph module from Home Page

scenario: Verifying static content on the Graph page

Scenario Outline: Verifying static content on the graph page
Given The user is in the Graph page
When The Graph page loads
Then the user should be able to see "<expected_topic>" in graph page

    Examples:
      | expected_topic  |
      | Graph          |
      | Topics Covered |


Scenario: Verify that user is able to see and click all links uder Topics covered   
Given The user is in the Graph page
When User scrolls down the page
Then  "<Topic_link>" under the topics covered should be visible and clickable
Examples:
    | Topic_link               |
    | Graph                  |
    | Graph Representations  |


Scenario: Verify that user is able to navigate to Graph Topic page
Given The user is on the Graph page
When The user clicks "<Topic>" tab under Topics covered
Then The user should be redirected to "<Topic_url>" page with related details
Examples:
    | Topic                  | Topic_url                        |
    | Graph                  | Graph                            |
    | Graph Representations  | Graph Representations            |


Scenario: Verify that "Try here>>>" tab is visible and clickable
Given The user is on the Graph page
When The user clicks "<Topic>" page
Then Try here>>> button should be visible and clickable below the "<Topic>" content
Examples:
    | Topic                  |
    | Graph                  |
    | Graph Representations  |
 
  
Scenario: Verify that user is able to navigate to Try here>>> page from topic page
Given The user is on the Graph page
When The user clicks Try Here button in "<Topic>" in page
Then The user should be redirected to a page having an try Editor with a Run button to test
Examples:
    | Topic                  |
    | Graph                  |
    | Graph Representations  |

  
Scenario: Verify that Run button is visible and clickable
Given The user is on the Graph page
When The user clicks Try Here button in Graph page
Then Run button should be visible and clickable

 
Scenario: Verify that user gets error message when click on Run button without entering code
Given The user is in the tryEditor page in Graph module
When The user clicks the Run Button without entering the code in the Editor
Then The user should able to get the error message "Empty Code Editor"


@singleTest   
Scenario Outline: Verify Try Editor response for "<topic_page>" with "<code_type>" code
Given User is in tryEditor page of "<topic_page>"
When User enters "<code_type>" code in the Try Editor and clicks on "Run" button
Then User must see "<expected_result>" in the UI

Examples:
      | topic_page                | code_type | expected_result                                                          |
      | Graph			          | valid     | Hello                            |
      | Graph      				| invalid   | an error popup stating NameError: name 'invalid' is not defined on line 1 |
      | Graph Representations    				  | valid  | Hello                            |
      | Graph Representations     | invalid   | an error popup stating NameError: name 'invalid' is not definedon line 1  |

Scenario: Verify that user is able to navigate to Practice Questions page
Given The user is in the Graph page
When The user clicks the Practice Questions button
Then The user should be redirected to list of Practice Questions of Graph page.
