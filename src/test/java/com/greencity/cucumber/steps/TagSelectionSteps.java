package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

public class TagSelectionSteps {
    private final Hooks hooks;
    private CreateEditNewsPage createEditNewsPage;

    public TagSelectionSteps(Hooks hooks) {
        this.hooks = hooks;
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

    @When("User selects the second tag")
    public void selectSeconTag() {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickTag(NewsTags.EVENTS_TAG);
    }

    @When("User selects the third tag")
    public void selectThirdTag() {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickTag(NewsTags.EDUCATION_TAG);
    }

    @When("User tries to select the fourth tag")
    public void selectFourthTag() {
        createEditNewsPage = getCreateEditNewsPage();
        createEditNewsPage.clickTag(NewsTags.ADS_TAG);

    }

    @Then("User should see only 3 tags selected")
    public void verifyOnlyThreeTagsSelected() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        createEditNewsPage = getCreateEditNewsPage();
        List<String> selectedTags = createEditNewsPage.getSelectedTags();
        hooks.getSoftAssert().assertEquals(selectedTags.size(), 3, "Only three tags should be selected");
    }

}
