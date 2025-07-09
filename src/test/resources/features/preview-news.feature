Feature: News Preview Display

  Description:
  Verify that a user can preview title, content, author, and date of the news before publishing.

  Background:
    Given the user is logged in
    And the user navigates to the Create News Page
    And the user enters "Test Preview" as title and "This is a test preview content" as content
    And the user clicks the Preview button

  Scenario: Verify news preview contains correct data
    Then the preview header should be "Create news"
    And the preview title should be "Test Preview"
    And the preview content should be "This is a test preview content"
    And the preview author should match the logged in user
    And the preview date should be today
    And the "Back to editing" button should be visible
