package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.pages.myspacepage.MySpacePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public class MySpaceSteps {
    private final Hooks hooks;
    private final SoftAssert softAssert;

    private MySpacePage mySpacePage;
    public MySpaceSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
        this.mySpacePage = new MySpacePage(hooks.getDriver());

    }

    private MySpacePage getMySpacePage() {
        return new MySpacePage(hooks.getDriver());
    }

    @Then("the user should see MySpace page")
    public void userShouldSeeMySpacePage() {
        // This step can be implemented to verify that the user is on the MySpace page.
        // For example, you might check for a specific element that is unique to the MySpace page.
        String currentUrl = hooks.getDriver().getCurrentUrl();
//        hooks.getSoftAssert().assertTrue(currentUrl.contains("/profile"), "User is not on MySpace page");
        mySpacePage = getMySpacePage();
        hooks.getSoftAssert().assertEquals(mySpacePage.getMySpaceDescription().getText(),
                "Welcome to My Habits. It seems you don’t have any habits in progress. Let’s start to acquire a new one!");
    }

    @Given("the user navigates to {string} tab")
    @Step("Navigate to '{0}' tab in My Space")
    @Owner("Prykhodchenko Oleksandra")
    public void theUserNavigatesTo(String arg0) {
        mySpacePage
                .clickTabsByText(arg0);
    }

    @And("the user opens the first news post")
    @Step("Open the first news post")
    @Owner("Prykhodchenko Oleksandra")
    public void theUserOpensTheFirstNewsPost() {
        mySpacePage.clickFirstNew();
    }

    @Then("the image should not be updated")
    @Step("Verify the image was not updated")
    @Owner("Prykhodchenko Oleksandra")
    public void theImageShouldNotBeUpdated() {
        theUserNavigatesTo("My news");
        softAssert.assertTrue(mySpacePage
                .clickFirstNew()
                .getNewsImage()
                .getAttribute("src")
                .contains("assets/img/icon/econews/news-default-large.png"), "Image was changed");
    }
}
