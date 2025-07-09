package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class CreateEditSteps {

    private final Hooks hooks;

    private CreateEditNewsPage createEditNewsPage;

    public CreateEditSteps(Hooks hooks ) {
        this.hooks = hooks;
    }

    private CreateEditNewsPage getCreateEditNewsPage() {
        return new CreateEditNewsPage(hooks.getDriver());
    }

    @Then("the \"Create News\" form should open")
    public void userShouldSeeEcoNewsPage(){
        String currentUrl = hooks.getDriver().getCurrentUrl();
        hooks.getSoftAssert().assertTrue(currentUrl.contains("/create-news"),"User is not on Create News page");
    }

    @Then("the \"Title\" field should be displayed")
    public void titleFieldDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getTitleCounter().isDisplayed(),"The title counter is not displayed");
    }

    @Then("the \"Image\" upload button should be displayed")
    public void imageButtonDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getBrowserLabel().isDisplayed(),"The image upload button is not displayed");
    }

    @Then("the \"Text\" field should be displayed")
    public void textFieldDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getContentCharacterCounter().isDisplayed(),"The text counter is not displayed");
    }

    @Then("the \"Source\" placeholder should be displayed")
    public void sourcePlaceholderDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getSourcePlaceholder().isDisplayed(),"The source placeholder is not displayed");
    }

    @Then("the \"Author\" field should be displayed with the non-editable \"Username\"")
    public void authorEditable(){
        hooks.getSoftAssert().assertFalse(getCreateEditNewsPage().isAuthorEditable(),"The author can not be edited");
    }

    @Then("the \"Date\" field should be displayed with the non-editable current date")
    public void dataEditable(){
        hooks.getSoftAssert().assertFalse(getCreateEditNewsPage().isDateEditable(),"The data can not be edited");
    }

    @Then("the \"Cancel\" button should be displayed")
    public void cancelDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getCancelButton().isDisplayed(),"The button cancel is not displayed");
    }

    @Then("the \"Preview\" button should be displayed")
    public void previewDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getPreviewButton().isDisplayed(),"The button preview is not displayed");
    }

    @Then("the \"Publish\" button should be displayed")
    public void publishDisplayed(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getPublishButton().isDisplayed(),"The button publish is not displayed");
    }

    @When("enter a valid title {string}")
    public void enterTitle(String title){
        getCreateEditNewsPage().enterTitle(title);
    }

    @When("enter a valid text {string}")
    public void enterText(String text){
        getCreateEditNewsPage().enterContent(text);
    }

    @When("select a tag {newsTag}")
    public void selectTag(NewsTags tag){
        getCreateEditNewsPage().clickTag(tag);
    }

    @When("click the Edit news")
    public void clickEditNews(){
        getCreateEditNewsPage().clickEdit();
    }

    @Then("the \"Edit\" button should be disabled")
    public void editDisabled(){
        hooks.getSoftAssert().assertFalse(getCreateEditNewsPage().getEditButton().isEnabled(),"The button should be inactive");
    }

    @Then("the user a still on the edit page")
    public void isStillEdit(){
       hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().isStillOnEditPage(),"User should be on edit page");
    }

    @Then("the \"Title\" field should be highlighted in red")
    public void titleWarning(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().isTitleFieldHighlightedInRed(),"The title should be in a red highlighter");
    }

    @Then("an error message indicating minimum length requirement should be displayed")
    public void textWarning(){
        hooks.getSoftAssert().assertTrue(getCreateEditNewsPage().getDescriptionWarningTextarea().isDisplayed(),"In the text area, there must be description warning");
    }

}
