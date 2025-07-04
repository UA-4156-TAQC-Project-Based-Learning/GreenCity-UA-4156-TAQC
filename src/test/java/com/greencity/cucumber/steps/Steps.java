package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
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
        hooks.getDriver().navigate().refresh();
    }

}