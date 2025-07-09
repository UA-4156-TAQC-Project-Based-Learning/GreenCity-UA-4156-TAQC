Feature: Display of all visual elements of the comment

  Description:
  Verify that all visual elements of a comment are displayed on the news page.
  The user should see the comment text, author name, avatar, date,
  and the edit, delete, and reply buttons.

  Background:
    Given the user has successfully logged in to view comments
    And the user opens the first news article page in Eco News
    And the user observes the comments section

  Scenario: Verify comment visual elements are displayed
    Then at least one comment is displayed
    And the comment text is visible
    And the comment author name is visible
    And the comment avatar icon is visible
    And the comment date is visible
    And the edit button is visible
    And the delete button is visible
    And the reply button is visible