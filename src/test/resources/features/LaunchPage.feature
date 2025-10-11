@refFeature
Feature: DS Algo Portal functional testing

  Background: User launches the browser and navigates to DS Algo portal

    #background steps are already implemented in driver factory class in init method

  Scenario: Endpoint of the launch page URL
    Then the user should be on the DS Algo Portal page

  Scenario: Content text on the Launch page
    Then the user should be able to see the content text on the Launch page
      | Preparing for the Interviews|
      | Copyright@NumpyNinja2021|


  Scenario: Launch page should have 1 button
   Then the user should be able to see 1 button on the Launch page

   Scenario: Button should have text "Get Started"
    Then the user should be able to see button with text "Get Started"


    # below two scenario is for homepage feature file
  Scenario: Navigation from Launch to home Page

    When the user clicks on "Get Started" button
    Then The user should be redirected to the home page


  Scenario: Presence of linkText in home page

    When the user clicks on "Get Started" button
    Then the user should see linkTexts

      |NumpyNinja|
      |Data Structures|
      |Register|
      |Sign in|