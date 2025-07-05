Feature: Eco-news subscription for unregistered users on the GreenCity main page

  As an unregistered user
  I want to view and interact with the "Receive interesting Eco-news" section
  So that I can subscribe to the newsletter

  Background:
    Given the user is not logged in to the GreenCity system
    And the user opens the GreenCity Main Page in a maximized browser window

  Scenario: Display of the "Receive interesting Eco-news" section
    When the user scrolls down to the "Receive interesting Eco-news" section
    Then the section title "Receive interesting Eco-news" should be visible
    And a functional QR code should be displayed
    And the text "Subscribe for our newsletter and always be up to date with news and updates" should be present
    And the email input field should be visible with placeholder text "Enter your email"

  Scenario: Validation of invalid email input
    When the user scrolls down to the "Receive interesting Eco-news" section
    And the user enters "eco-news" into the email input field
    And clicks the "Subscribe" button
    Then the system should display a validation error for invalid email

  Scenario: Successful subscription with valid email
    When the user scrolls down to the "Receive interesting Eco-news" section
    And the user enters "test+3@example.com" into the email input field
    And clicks the "Subscribe" button
    Then the system should accept the email
    And a success message or confirmation should be shown