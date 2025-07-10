Feature: News Comments Edit Icon Visibility

  Description:
  Verify the visibility of the edit icon for news comments.
  The edit icon should be visible only for comments authored by the currently logged-in user.

  Background:
    Given the user is logged in
    And the user navigates to the first news page in Eco News

  Scenario: Edit icon visible only for comments created by logged-in user
    When the user views the list of comments
    Then there should be multiple comments
    And at least one comment is authored by the logged-in user
    And at least one comment is authored by another user
    And the edit icon is visible only for comments authored by the logged-in user