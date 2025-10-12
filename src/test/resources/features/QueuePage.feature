Feature: Functional testing for DSAlgo Queue page

  Background: Signed in user has navigated to Queue module from home page

  Scenario: User must be able to view the links present under "Topics Covered"  in Queue page
    Given User is in Queue page
    When User scrolls down Queue page
    Then User must see  Queue topics as links under "Topics Covered"

  Scenario: User must be able to view header as "Implementation of Queue"  along with related content in UI
    Given User is in Queue page
    When User clicks on "Implementation of Queue"  link present under Topics Covered section
    Then User must view  header  content as "Implementation of Queue"  along with related content

  Scenario: User must see "Try here" button in sub content Queue UI
    Given User is in Queue page
    When User clicks on "Implementation of Queue"  link present under Topics Covered section
    Then User must see "Try Here" button at the bottom of the UI

  Scenario: User must be navigated  to Editor page upon clicking "Try here" button
    Given User is in "Implementation of Queue"  UI
    When user clicks on "Try Here" button
    Then User must be navigated to Editor page

  Scenario: User must be able to view header as "Implementation using collections.deque"   along with related content in UI
    Given User is in Queue page
    When User clicks on "Implementation using collections.deque" link present under Topics Covered section
    Then User must view  header  content as "Implementation using collections.deque"  along with related content

  Scenario: User must see "Try here" button in sub content Queue UI
    Given User is in Queue page
    When User clicks on "Implementation using collections.deque"  link present under Topics Covered section
    Then User must see "Try Here" button at the bottom of the UI

  Scenario: User must be navigated  to Editor page upon clicking "Try here" button
    Given User is in "Implementation using collections.deque"  UI
    When user clicks on "Try Here" button
    Then User must be navigated to Editor page

  Scenario: User must be able to view header as "Implementation using array" along with related content in UI
    Given User is in Queue page
    When User clicks on "Implementation using array" link present under Topics Covered section
    Then User must view  header  content as "Implementation using array"  along with related content

  Scenario: User must see "Try here" button in sub content Queue UI
    Given User is in Queue page
    When User clicks on "Implementation using array"  link present under Topics Covered section
    Then User must see "Try Here" button at the bottom of the UI

  Scenario: User must be navigated  to Editor page upon clicking "Try here" button
    Given User is in "Implementation using array" UI
    When user clicks on "Try Here" button
    Then User must be navigated to Editor page

  Scenario: User must be able to view header as "Queue Operations"  along with related content in UI
    Given User is in Queue page
    When User clicks on "Queue Operations"  link present under Topics Covered section
    Then User must view  header  content as "Queue Operations"  along with related content

  Scenario: User must see "Try here" button in sub content Queue UI
    Given User is in Queue page
    When User clicks on "Queue Operations"  link present under Topics Covered section
    Then User must see "Try Here" button at the bottom of the UI

  Scenario: User must be navigated  to Editor page upon clicking "Try here" button
    Given User is in "Queue Operations"  UI
    When user clicks on "Try Here" button
    Then User must be navigated to Editor page

  Scenario: User must be able run a valid code in Try Editor page
    Given User is in "Queue Operations"  UI
    When User  clicks on "Run" button after entering valid code
    Then User must see the expected output in UI

  Scenario: User must see alert upon running invalid code in Try Editor page
    Given User is in "Queue Operations"  UI
    When User  clicks on "Run" button after entering invalid code
    Then User must see error popup along with OK button  stating "NameError: name 'text' is not defined on line 1"

  Scenario: User must be able to view Practice questions link in UI
    Given User is in Queue page
    When User clicks on "Queue Operations" link present under Topics Covered section
    Then User must  view " Practice Questions " link in side nav bar

  Scenario: User must be able to navigate to Practice Questions page
    Given User is in "Queue Operations"  UI
    When User clicks on "Pratice Questions" link
    Then User must be navigated to "Pratice Questions" link containing list of questions
