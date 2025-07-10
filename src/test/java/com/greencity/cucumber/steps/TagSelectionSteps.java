package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TagSelectionSteps {
    private final Hooks hooks;
    private CreateEditNewsPage createEditNewsPage;
    private EcoNewsPage ecoNewsPage;

    public TagSelectionSteps(Hooks hooks) {
        this.hooks = hooks;
    }

    private CreateEditNewsPage getCreateEditNewsPage() {
        return new CreateEditNewsPage(hooks.getDriver());
    }

    private EcoNewsPage getEcoNewsPage() {
        return new EcoNewsPage(hooks.getDriver());
    }

    @Given("User opens the Create News page")
    public void userOpensEcoNewsPage() {
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + "/news");
        ecoNewsPage = getEcoNewsPage();
    }

    @Given("User opens the CreateEditNews page")
    public void userIsOnCreateNewsPage() {
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl() + "/news/create-news");
        createEditNewsPage = getCreateEditNewsPage();
    }

    @When("User clicks Create News button")
    public void userClicksCreateNewsButton() {
        ecoNewsPage = getEcoNewsPage();
        createEditNewsPage = ecoNewsPage.clickCreateNewsButton();
    }

    @When("User selects the first tag")
    public void selectFirstTag() {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickTag(NewsTags.NEWS_TAG);
    }

    @When("User selects the {string} tag")
    public void userSelectsDynamicTag(String tag) {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickTag(NewsTags.getByName(tag));
    }

    @When("User fills in the Title with {string}")
    public void userFillsTitle(String title) {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.enterTitle(title);
    }

    @When("User fills in the Main Text with {string}")
    public void userFillsMainText(String text) {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.enterContent(text);
    }

    @When("User clicks Publish button")
    public void userClicksPublish() {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickPublish();
    }

    @Then("The {string} tag is not selected")
    public void tagIsNotSelected(String ta) {
        createEditNewsPage = getCreateEditNewsPage();
        hooks.getSoftAssert().assertEquals(createEditNewsPage.getSelectedTagCount(),3, "There should be three tags selected");
        hooks.getSoftAssert().assertFalse(createEditNewsPage.isTagSelected(NewsTags.getByName(ta)), "The tag " + ta + " should not be selected");
    }


    @Then("News is published with the one tag")
    public void newsPublishedWithOneTag() {
        ecoNewsPage = getEcoNewsPage();
        hooks.getSoftAssert().assertEquals(ecoNewsPage.getSelectedTagCount(), 1, "News should be published with one tag selected");
    }

    @Then("News is published with three tags")
    public void newsPublishedWithThreeTags() {
        ecoNewsPage = getEcoNewsPage();
        hooks.getSoftAssert().assertEquals(ecoNewsPage.getSelectedTagCount(), 3, "News should be published with three tags selected");
    }

}
