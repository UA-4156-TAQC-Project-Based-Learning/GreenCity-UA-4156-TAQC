Feature: Restriction of "Edit news" button visibility for different users

  As a logged-in user
  I should not be able to see the "Edit news" button
  If I did not create the news post

  Background:
    Given User A is logged into the system
    And User A creates a new news post with title "Test News" and tag "Education"
    And User A logs out
    And User B is logged into the system

  Scenario: User B cannot see "Edit news" button for a post created by User A
    When User B navigates to the Eco News page
    And User B opens the first news item
    Then the "Edit news" button should not be visible to User B
    And User B logs out
    And User A is logged into the system
    And User A deletes the created news post