Feature: Content Length Validation

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: Attempt to submit content shorter than 20 characters
    When click the "Eco news" link
    And click on their own news article with title "title"
    And click the Edit news
    And enter a valid text "bla "
    And click the "Edit" button
    Then an error message indicating minimum length requirement should be displayed
    And the "Edit" button should be disabled
    And the user a still on the edit page


  Scenario: Attempt to submit news with an empty title
    When click the "Eco news" link
    And click on their own news article with title "title"
    And enter a valid title " "
    And click the "Edit" button
    Then the "Title" field should be highlighted in red
    And the "Edit" button should be disabled
    And the user a still on the edit page