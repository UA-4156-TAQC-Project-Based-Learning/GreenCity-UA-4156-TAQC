Feature: Content Length Validation

  Description: Verify that content shorter than 20 characters is not accepted when editing a news post.

  Background:
    Given the author is editing a news post

  Scenario: Attempt to submit content shorter than 20 characters
    When the author sets the "Content" field to less than 20 characters
    And the author clicks "Submit"
    Then an error message indicating minimum length requirement should be displayed
    And the submit action should fail

Feature: Title Field Validation

  Description: Verify that a news post cannot be submitted without a title.

  Background:
    Given the author is editing a news post

  Scenario: Attempt to submit news with an empty title
    When the author opens the edit form
    And the author clears the "Title" field
    And the author clicks the "Edit" button
    Then the "Title" field should be highlighted in red
    And the "Edit" button should be disabled
    And the news post should not be updated