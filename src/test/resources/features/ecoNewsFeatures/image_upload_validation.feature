@ImageUpload
Feature: Image Upload Validation in Create News Form

  As a logged-in user
  I want to upload only supported image files (PNG, JPEG) with size up to 10MB
  So that the system accepts only valid images and shows proper validation for invalid ones

  Background:
    Given the user is logged in to the GreenCity system
    And the user navigates to the "Create News" page

  @Positive
  Scenario: Uploading a valid PNG image under 10MB
    When the user uploads a "5mb.png" image
    Then the image should be uploaded successfully without any error

  @Negative
  Scenario: Uploading an unsupported GIF image
    When the user uploads a "download.gif" image
    Then the system should show an error message "Upload only PNG or JPG. File size must be less than 10MB"
    And the image field should be highlighted in red

  @Negative
  Scenario: Uploading an oversized JPEG image
    When the user uploads a "15mb.jpg" image
    Then the system should show an error message "Upload only PNG or JPG. File size must be less than 10MB"
    And the image field should be highlighted in red

  @Smoke
  Scenario: Navigation to the Create News page
    When the user clicks the "Create News" button on the Eco News page
    Then the page should display a title containing "Create" or "Створити"
