Feature: Recommended News Widget

  Description:
  Verify that the "Interesting for you" widget displays exactly 3 most recent news items
  regardless of any tag filters and excludes the currently opened news.

  Background:
    Given the user has successfully logged in to view interesting news
    And the user is on the Eco News page with no active tag filter

  Scenario: Display 3 most recent recommended news regardless of tag filter
    Given no tag filter is selected
    And at least 4 news items are available
    When the user opens the first news article
    Then the recommended news widget displays exactly 3 most recent news excluding the opened one
