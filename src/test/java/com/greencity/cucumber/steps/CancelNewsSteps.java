package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.baseComponents.CancelConfirmModal;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;

public class CancelNewsSteps {
    private final Hooks hooks;
    private final SoftAssert softAssert;
    private CreateEditNewsPage createNewsPage;
    private CancelConfirmModal confirmModal;

    public CancelNewsSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
    }

    @Given("user navigates to the Create News Page")
    public void userNavigatesToCreateNewsPage() {
        createNewsPage = new HomePage(hooks.getDriver())
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();
    }

    @Given("user enters {string} as title and {string} as content")
    public void userEntersTitleAndContent(String title, String content) {
        createNewsPage.enterTitle(title);
        createNewsPage.enterContent(content);
    }

    @When("user clicks the {string} button")
    public void userClicksButton(String btnText) {
        switch (btnText) {
            case "Cancel":
                confirmModal = createNewsPage.clickCancelButton();
                break;
            case "Yes, cancel":
                confirmModal.clickYesCancel();
                break;
            case "Continue editing":
                confirmModal.clickContinueEditing();
                break;
            default:
                throw new IllegalArgumentException("Unknown button: " + btnText);
        }
    }

    @Then("confirmation modal should be displayed")
    public void confirmationModalShouldBeDisplayed() {
        softAssert.assertTrue(confirmModal.isDisplayed(), "Confirmation modal should be displayed");
    }

    @Then("modal title should contain {string}")
    public void modalTitleShouldContain(String expected) {
        String actual = confirmModal.getTitleText().getText();
        softAssert.assertTrue(actual.contains(expected),
                "Modal title should contain: \"" + expected + "\", but was: \"" + actual + "\"");
    }

    @Then("modal subtitle should contain {string}")
    public void modalSubtitleShouldContain(String expected) {
        String actual = confirmModal.getSubtitleText().getText();
        softAssert.assertTrue(actual.contains(expected),
                "Modal subtitle should contain: \"" + expected + "\", but was: \"" + actual + "\"");
    }

    @Then("user should be redirected to the News listing page")
    public void userShouldBeRedirectedToNewsPage() {
        String url = hooks.getDriver().getCurrentUrl();
        softAssert.assertTrue(url.endsWith("/news") || url.contains("/news?"),
                "User should be on News listing page. Actual URL: " + url);
    }

    @Then("confirmation modal should be closed")
    public void modalShouldBeClosed() {
        softAssert.assertFalse(confirmModal.isDisplayed(), "Confirmation modal should be closed");
    }

    @Then("Create News form should remain open")
    public void formShouldRemainOpen() {
        softAssert.assertTrue(createNewsPage.isFormDisplayed(), "Create News form should be displayed");
    }

    @Then("the title field should contain {string}")
    public void titleShouldContain(String expected) {
        String actual = createNewsPage.getTitleText();
        softAssert.assertEquals(actual, expected,
                "Title field content mismatch: expected \"" + expected + "\", actual \"" + actual + "\"");
    }

    @Then("the content field should contain {string}")
    public void contentShouldContain(String expected) {
        String actual = createNewsPage.getContentText();
        softAssert.assertEquals(actual, expected,
                "Content field content mismatch: expected \"" + expected + "\", actual \"" + actual + "\"");
    }
}