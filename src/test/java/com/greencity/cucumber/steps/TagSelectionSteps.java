package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

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

    @Then("The 'Initiatives' tag is not selected")
    public void initiativesTagIsNotSelected() {
        createEditNewsPage = getCreateEditNewsPage();
        hooks.getSoftAssert().assertFalse(createEditNewsPage.getSelectedTags().contains(NewsTags.INITIATIVES_TAG),
                "The 'Initiatives' tag should not be selected");
    }


    @Then("News is published with the {string} tag")
    public void newsPublishedWithOneTag(String tag) {
        ecoNewsPage = getEcoNewsPage();
        List<String> publishedTags = ecoNewsPage.getSelectedTags();
        hooks.getSoftAssert().assertEquals(publishedTags.size(), 1, "News should be published with one tag");
        hooks.getSoftAssert().assertTrue(publishedTags.contains(tag), "Published tag should be: " + tag);
    }

    @Then("News is published with the {string}, {string}, and {string} tags")
    public void newsPublishedWithThreeTags(String tag1, String tag2, String tag3) {
        ecoNewsPage = getEcoNewsPage();
        List<String> publishedTags = ecoNewsPage.getSelectedTags();
        List<String> expected = List.of(tag1, tag2, tag3);
        hooks.getSoftAssert().assertTrue(publishedTags.containsAll(expected),
                "Published tags should contain all: " + expected);
    }

}
