package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

import static com.greencity.ui.pages.CreateEditNewsPage.generateText;

public class ValidateContentFieldSteps {

    private final Hooks hooks;
    private CreateEditNewsPage createEditNewsPage;
    private SoftAssert softAssert;

    public ValidateContentFieldSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert=hooks.getSoftAssert();
    }

    private CreateEditNewsPage getCreateEditNewsPage() {
        return new CreateEditNewsPage(hooks.getDriver());
    }

    @Given("User opens the Create News page")
    public void userIsOnCreateNewsPage() {
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + "/news/create-news");
        createEditNewsPage = getCreateEditNewsPage();
    }

    @When("User selects the first tag")
    public void selectFirstTag() {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickTag(NewsTags.NEWS_TAG);
    }

    @When("User enters {int} characters into the Content field")
    public void userEntersTextIntoContentField(int numberOfCharacters) {
        String content = generateText(numberOfCharacters);
        createEditNewsPage.enterContentJS(content);
    }

    @When("User enters {int} characters into the title field")
    public void userEntersTextIntoTitleField(int numberOfCharacters) {
        String title = generateText(numberOfCharacters);
        createEditNewsPage.enterContentJS(title);
    }

    @When("User clicks the Publish button")
    public void the_user_clicks_the_button(String string) {
        createEditNewsPage.getPublishButton().click();
    }

    @Then("Content field info message  is displayed in red")
    public void isContentInfoMessageDisplayedInRed() {
        String actualContentInfoMessageColor = createEditNewsPage.getContentInfoMessage().getCssValue("color");
        String expectedContentInfoMessageColor = "rgba(235, 24, 13, 1)";
        softAssert.assertEquals(actualContentInfoMessageColor, expectedContentInfoMessageColor,
                "The actual Content Info message color does not match the expected color.");
    }

    @Then("Content field counter is displayed in red")
    public void isContentFieldCounterDisplayedInRed() {
        String actualContentCounterColorWithWarning = createEditNewsPage.getContentCounter().getCssValue("color");
        String expectedContentCounterColorWithWarning = "rgba(235, 24, 13, 1)";
        softAssert.assertEquals(actualContentCounterColorWithWarning, expectedContentCounterColorWithWarning,
                "The actual Content Info message color with warning does not match the expected color.");
    }

    @Then("Content field info message {string} is shown")
    public void isErrorMessageShown(String expectedContentInfoMessageText) {
        String actualContentInfoMessageText = createEditNewsPage.getContentInfoMessage().getText();
        softAssert.assertEquals(actualContentInfoMessageText, expectedContentInfoMessageText,
                "The actual Content Info message text does not match the expected text.");
    }

    @Then("Content field counter message {string} is shown")
    public void isCounterMessageShown(String expectedContentCounterTextWithWarning) {
        String actualContentCounterTextWithWarning = createEditNewsPage.getContentCounter().getText();
        softAssert.assertEquals(actualContentCounterTextWithWarning, expectedContentCounterTextWithWarning,
                "The actual Content counter text with warning does not match the expected text.");
    }

    @Then("Publish button is disabled")
    public void isPublishButtonDisabled() {
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled());
    }

    @Then("Publish button is enabled")
    public void isPublishButtonEnabled() {
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled());
        throw new io.cucumber.java.PendingException();
    }

    @Then("The news is published successfully")
    public void isNewsPublishedSuccesfully() {
        softAssert.assertTrue(hooks.getDriver().getCurrentUrl().contains("/news"), "EcoNews Page is not opened");
    }


}
