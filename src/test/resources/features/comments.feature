Feature: Comment

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: Add New Comment on Eco News
    When click the "Eco news" link
    And click on their own news article with title "title"
    And remember comment count
    And add comment "comment for eco news"
    Then comment "comment for eco news" appears below the input field
    And displays correct date
    And username are displayed
    And avatar are displayed
    And total comment count is updated correctly

  Scenario: Add New Comment on Event pages
    When open the Events page
    And click on event with title "Test Event"
    And remember comment count
    And add comment "comment for events"
    Then comment "comment for events" appears below the input field
    And displays correct date
    And username are displayed
    And avatar are displayed
    And total comment count is updated correctly
