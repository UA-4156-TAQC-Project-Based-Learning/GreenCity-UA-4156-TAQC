Feature: Validation of "Content" field in Create News form

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: Info message is shown when text in the "Content" field is too short

    When User opens the Create News page
    And User enters 20 characters into the title field
    And User selects the first tag
    And User enters 19 characters into the Content field
    Then Content field info message "Must be minimum 20 and maximum 63 206 symbols" is shown
    And Content field info message  is displayed in red
    And Publish button is disabled

  Scenario: Counter error is shown when text in the "Content" field exceeding maximum length

    When User opens the Create News page
    And User enters 20 characters into the title field
    And User selects the first tag
    And User enters 63207 characters into the Content field
    Then Content field counter is displayed in red
    And Content field counter message "The maximum character length is greater than 1" is shown
    And Publish button is disabled

  Scenario: Successfully publish news with valid length text in the Content field

    When User opens the Create News page
    And User enters 20 characters into the title field
    And User selects the first tag
    And User enters 25 characters into the Content field
    And User clicks the Publish button
    Then The news is published successfully

