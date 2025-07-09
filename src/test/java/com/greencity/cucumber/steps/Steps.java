package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class Steps {

    private final Hooks hooks;

    public Steps(Hooks hooks) {
        this.hooks = hooks;
    }

    @When("click the {string} button")
    public void clickBtnByName(String btnName) {
        hooks.getDriver().findElement(By.xpath("//button[contains(text(),'" + btnName + "')]")).click();
    }

    @When("click the {string} link")
    public void clickLinkByName(String linkName) {
        hooks.getDriver().findElement(By.xpath("//a[contains(text(),'" + linkName + "')]")).click();
    }

    @Step("Login As User in the Test Runner With Users")
    @Given("the base user is registered and logged into the GreenCity system")
    public void userIsRegisteredAndLoggedIn() {
        // This step can be implemented to ensure the user is registered and logged in.
        // For example, you might navigate to the login page, enter credentials, and submit the form.
        hooks.getDriver().get(hooks.getTestValueProvider().getBaseUIUrl());
        // Add login logic here

        hooks.getLocalStorageJS().setItemLocalStorage("accessToken", hooks.getTestValueProvider().getLocalStorageAccessToken());
        hooks.getLocalStorageJS().setItemLocalStorage("userId", hooks.getTestValueProvider().getLocalStorageUserId().toString());
        hooks.getLocalStorageJS().setItemLocalStorage("refreshToken", hooks.getTestValueProvider().getLocalStorageRefreshToken());
        hooks.getLocalStorageJS().setItemLocalStorage("name", hooks.getTestValueProvider().getLocalStorageName());
        hooks.getLocalStorageJS().setItemLocalStorage("language",hooks.getTestValueProvider().getLocalStorageLanguage());
        hooks.getDriver().navigate().refresh();
    }

    @ParameterType(".*")
    public NewsTags newsTag(String tagName) {
        for (NewsTags tag : NewsTags.values()) {
            if (tag.getEnglishName().equalsIgnoreCase(tagName) || tag.getUkrainianName().equalsIgnoreCase(tagName)) {
                return tag;
            }
        }
        throw new IllegalArgumentException("Did not search NewsTag with name: '" + tagName + "'");
    }
}