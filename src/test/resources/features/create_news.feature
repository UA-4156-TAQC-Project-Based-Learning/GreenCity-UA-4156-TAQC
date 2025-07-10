Feature: Create news

  Background:
    Given the base user is registered and logged into the GreenCity system

  Scenario: User creates news and data is saved correctly
    When click the "Eco news" link
    And click the Create news
    And enter a valid title "title"
    And enter a valid text "For most of human history, the night sky was not just for scientists; it was vital for survival."
    And select a tag NEWS
    And click the "Publish" button
    Then the news article with title "title", text "For most of human history, the night sky was not just for scientists; it was vital for survival.", and tag "NEWS" should be saved in the eco-news page

  Scenario: Verify the "Create News" form fields and their order
    When click the "Eco news" link
    And click the Create news
    Then the "Create News" form should open
    And the "Title" field should be displayed
    And the "Image" upload button should be displayed
    And the "Text" field should be displayed
    And the "Source" placeholder should be displayed
    And the "Author" field should be displayed with the non-editable "Username"
    And the "Date" field should be displayed with the non-editable current date
    And the "Cancel" button should be displayed
    And the "Preview" button should be displayed
    And the "Publish" button should be displayed