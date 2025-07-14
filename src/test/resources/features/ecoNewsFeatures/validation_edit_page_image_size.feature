Feature: Edit News Image Upload Validation

  As an authenticated user
  I want to ensure the edit news form validates image size correctly
  So that I cannot upload images larger than 10MB

  Background:
    Given the user is logged in to the GreenCity system
    And User creates a new news post with title "Test News" and tag "Education"
    And user navigates to the "My space" Page

  @EditNews @Negative @Image @Validation @Issue40
  Scenario: User cannot upload image larger than 10MB when editing a news post
    Given the user navigates to "My news" tab
    And the user opens the first news post
    And the user clicks "Edit news"
    And the user uploads an image larger than 10MB
    And the user clicks "Edit"
    Then the image should not be updated
    And  User A deletes the created news post