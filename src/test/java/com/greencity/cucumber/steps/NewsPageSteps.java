package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import io.cucumber.java.en.And;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public class NewsPageSteps {
    private final Hooks hooks;
    private final SoftAssert softAssert;
    private final NewsPage newsPage;


    public NewsPageSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
        this.newsPage = new NewsPage(hooks.getDriver());
    }
    @And("the user clicks \"Edit news\"")
    @Step("Click \"Edit news\" to attempt saving changes")
    @Owner("Prykhodchenko Oleksandra")
    public void theUserClicks() {
        newsPage.clickEditNewsButton();
    }
}
