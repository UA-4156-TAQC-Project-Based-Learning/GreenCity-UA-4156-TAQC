Feature: Tag Selection

  Description: Verify that the basic functionality of the tag selection feature works as expected.

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: User can publish news with one tag
    Given User opens the Create News page
    When User clicks Create News button
    And User fills in the Title with "Test"
    And User fills in the Main Text with "Test content with 20 chars"
    And User selects the "News" tag
    And User clicks Publish button
    Then News is published with the one tag


  Scenario: User can publish news with three tags
    Given User opens the Create News page
    When User clicks Create News button
    And User fills in the Title with "Test"
    And User fills in the Main Text with "Test content with 20 chars"
    And User selects the "News" tag
    And User selects the "Events" tag
    And User selects the "Education" tag
    And User clicks Publish button
    Then News is published with three tags


  Scenario: User cannot select more than three tags
    Given User opens the Create News page
    When User clicks Create News button
    And User fills in the Title with "Test"
    And User fills in the Main Text with "Test content with 20 chars"
    And User selects the "News" tag
    And User selects the "Events" tag
    And User selects the "Education" tag
    And User selects the "Initiatives" tag
    Then The "Initiatives" tag is not selected
    When User clicks Publish button
    Then News is published with three tags

