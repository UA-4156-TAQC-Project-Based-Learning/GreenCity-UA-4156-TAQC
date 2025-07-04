Feature: News Basic Functionality

  Description: Verify that the basic functionality of the news feature works as expected.

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: User can view news articles
    When click the "Start forming a habit!" button
    Then the user should see MySpace page
