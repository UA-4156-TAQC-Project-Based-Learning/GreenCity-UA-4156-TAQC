package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.pages.myspacepage.MySpacePage;
import io.cucumber.java.en.Then;

public class MySpaceSteps {
    private final Hooks hooks;

    private MySpacePage mySpacePage;
    public MySpaceSteps(Hooks hooks) {
        this.hooks = hooks;

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
}
