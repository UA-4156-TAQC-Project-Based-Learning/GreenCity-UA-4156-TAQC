Feature: Cancel Button Functionality

  Description:
  Verify the functionality of the "Cancel" button during news creation.
  The user can cancel the news creation process, receive a confirmation about losing changes,
  and choose to continue editing without losing entered data.

  Background:
    Given the base user is registered and logged into the GreenCity system
    And user navigates to the Create News Page
    And user enters "Test" as title and "Test content with 20 chars" as content

  Scenario: Confirm news creation cancelation
    When user clicks the "Cancel" button
    Then confirmation modal should be displayed
    And modal title should contain "content will be lost"
    And modal subtitle should contain "cancel"
    When user clicks the "Yes, cancel" button
    Then user should be redirected to the News listing page

  Scenario: Continue editing news after cancel
    When user clicks the "Cancel" button
    Then confirmation modal should be displayed
    When user clicks the "Continue editing" button
    Then confirmation modal should be closed
    And Create News form should remain open
    And the title field should contain "Test"
    And the content field should contain "Test content with 20 chars"
