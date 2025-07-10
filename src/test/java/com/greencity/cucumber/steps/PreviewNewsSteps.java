package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.PreviewNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PreviewNewsSteps {
    private final Hooks hooks;
    private final SoftAssert softAssert;
    private CreateEditNewsPage createNewsPage;
    private PreviewNewsPage previewPage;
    private String expectedTitle;
    private String expectedContent;
    private String expectedDate;

    public PreviewNewsSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
    }

    @Given("the user navigates to the Create News Page")
    public void userNavigatesToCreateNewsPage() {
        createNewsPage = new HomePage(hooks.getDriver())
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();
    }

    @Given("the user enters {string} as title and {string} as content")
    public void userEntersTitleAndContent(String title, String content) {
        this.expectedTitle = title;
        this.expectedContent = content;
        this.expectedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH));
        createNewsPage.enterTitle(title);
        createNewsPage.enterContent(content);
    }

    @Given("the user clicks the Preview button")
    public void userClicksPreviewButton() {
        previewPage = createNewsPage.clickPreview();
    }

    @Then("the preview header should be {string}")
    public void previewHeaderShouldBe(String expectedHeader) {
        softAssert.assertEquals(previewPage.getCreateNewsTitle(), expectedHeader, "Preview header mismatch");
    }

    @Then("the preview title should be {string}")
    public void previewTitleShouldBe(String title) {
        softAssert.assertEquals(previewPage.getTitleText(), title, "Title in preview does not match");
    }

    @Then("the preview content should be {string}")
    public void previewContentShouldBe(String content) {
        softAssert.assertEquals(previewPage.getContentText(), content, "Content in preview does not match");
    }

    @Then("the preview author should match the logged in user")
    public void previewAuthorShouldMatchLoggedInUser() {
        String expectedAuthor = hooks.getTestValueProvider().getUserName();
        softAssert.assertEquals(previewPage.getAuthorText(), expectedAuthor, "Author in preview does not match");
    }

    @Then("the preview date should be today")
    public void previewDateShouldBeToday() {
        softAssert.assertTrue(previewPage.getPublicationDateText().contains(expectedDate),
                "Date in preview is incorrect: " + previewPage.getPublicationDateText());
    }

    @Then("the {string} button should be visible")
    public void backToEditingButtonShouldBeVisible(String buttonText) {
        softAssert.assertTrue(previewPage.isBackToEditButtonVisible(),
                "'" + buttonText + "' button is not visible in preview");
    }
}