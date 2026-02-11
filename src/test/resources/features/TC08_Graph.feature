@graphfeature @Getstarted @Login 
Feature: DS Algo Portal functional testing 

Background:
Given The registered user has navigated to the "Graph" page

Scenario: Verifying static content on the Graph page

Scenario Outline: Verifying static content on the graph page
Then the user should be able to see "<expected_topic>" in graph page
    Examples:
      | expected_topic |
      | Graph |
      | Topics Covered |

Scenario Outline: Verify that user is able to see and click all links uder Topics covered   
Then  Topics under the topics covered should be visible and clickable
    | Graph |
    | Graph Representations |

Scenario: Verify that user is able to navigate to Graph Topic page
When The user clicks "<Topic>" tab under Topics covered
Then The user should be redirected to "<Topic_url>" page with related details
Examples:
    | Topic | Topic_url |
    | Graph | Graph |
    | Graph Representations | Graph Representations |

Scenario: Verify that "Try here>>>" tab is visible and clickable
When The user clicks "<Topic>" page
Then Try here>>> button should be visible and clickable below the "<Topic>" content
Examples:
    | Topic |
    | Graph |
    | Graph Representations |
   
Scenario: Verify that user is able to navigate to Try here>>> page from topic page
When The user clicks Try Here button in "<Topic>" in page
Then The user should be redirected to a page having an try Editor with a Run button to test
Examples:
    | Topic |
    | Graph |
    | Graph Representations |

Scenario: Verify that Run button is visible and clickable
When The user clicks Try Here button in "Graph" page
Then Run button should be visible and clickable

Scenario: Verify that user gets error message when click on Run button without entering code
Given The user is in the tryEditor page in "Graph" module
When The user clicks the Run button without entering the code in the Editor
Then The user should able to get the error message "Empty Code Editor"
 
Scenario: Verify that user is able to navigate to Practice Questions page
When The user clicks the Practice Questions button
Then The user should be redirected to list of Practice Questions of Graph page.

