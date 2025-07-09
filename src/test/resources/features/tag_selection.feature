@tagSelection
Feature: Tag Selection

  Description: Verify that the basic functionality of the tag selection feature works as expected.

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: User can select up to 3 tags
    Given User opens the Create News page
    When User selects the first tag
    And User selects the second tag
    And User selects the third tag
    And User tries to select the fourth tag
    Then User should see only 3 tags selected