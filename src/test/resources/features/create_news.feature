Feature: Create News Form Display

  Description: Verify that the "Create News" form displays all the necessary fields in the correct order.

  Background:
    Given the user is registered and logged into the GreenCity system

  Scenario: Verify the "Create News" form fields and their order
    When the user opens a browser and navigates to GreenCity
    And the user logs in as a registered user
    And the user clicks the "Create News" button on the news page
    Then the "Create News" form should open
    And the form should display the following fields in the specified order:
      | Field Name    | Type                        | Details                                                                                                    |
      | ------------- | --------------------------- | ---------------------------------------------------------------------------------------------------------  |
      | Title         | auto-resizing text field    | with a character counter "0/170"                                                                           |
      | Tag           | set of selectable buttons   | allowing up to three choices: News, Events, Education, Initiatives, Ads; selected tags change appearance   |
      | Add Image     | button or field             | for uploading an image                                                                                     |
      | Main Text     | auto-resizing text field    | with manual expansion and a character counter "63 206"                                                     |
      | Author        | pre-filled text field       | with "User name" and non-editable                                                                          |
      | Date          | pre-filled text field       | with the current date (e.g., "March 10, 2025") and non-editable                                            |
      | Source        | text field                  | with placeholder: "Please add the link of the original article/news/post. Link must start with http(s)://" |
    And the form should display the following buttons in the specified order: "Cancel", "Preview", "Publish"

Feature: Create news

  Description: Verify that user can create news and data is saved correctly.

  Background:
    Given the user is register and logged into the GreenCity system

    Scenario: User create news and data is saved correctly
      When the user opens a browser and navigates to GreenCity
      And the user logs in as a registered user
      And the user clicks the "Create News" button on the news page
      When the user entered valid title, source, content and selected tag
      And the user click publish button
      Then the news article should be present at database with correct data