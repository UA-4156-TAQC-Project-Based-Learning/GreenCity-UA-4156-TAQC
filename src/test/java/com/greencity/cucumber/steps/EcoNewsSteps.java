package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.asserts.SoftAssert;

public class EcoNewsSteps {

    private final Hooks hooks;
    private final SoftAssert softAssert;
    private NewsPage newsPage;
    private final HomePage homePage;

    public EcoNewsSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
        this.homePage = new HomePage(hooks.getDriver());
    }

    @Given("User A is logged into the system")
    public void userAIsLoggedIn() {
        hooks.getLocalStorageJS().setItemLocalStorage("accessToken", hooks.getTestValueProvider().getLocalStorageAccessToken());
        hooks.getLocalStorageJS().setItemLocalStorage("userId", hooks.getTestValueProvider().getLocalStorageUserId().toString());
        hooks.getLocalStorageJS().setItemLocalStorage("refreshToken", hooks.getTestValueProvider().getLocalStorageRefreshToken());
        hooks.getLocalStorageJS().setItemLocalStorage("name", hooks.getTestValueProvider().getLocalStorageName());
        hooks.getDriver().navigate().refresh();
    }

    @And("User A creates a new news post with title {string} and tag {string}")
    public void userACreatesNews(String title, String tag) {
        CreateEditNewsPage createPage = homePage.getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        createPage
                .enterTitle(title)
                .enterContent("This is test content for verifying visibility of Edit button.")
                .clickTag(NewsTags.valueOf(tag.toUpperCase() + "_TAG"))
                .clickPublish();
        hooks.getDriver().navigate().refresh();
    }

    @And("User A logs out")
    public void userALogsOut() {
        hooks.getLocalStorageJS().clearLocalStorage();
        hooks.getDriver().navigate().refresh();
    }

    @And("User B is logged into the system")
    public void userBIsLoggedIn() {
        hooks.getLocalStorageJS().setItemLocalStorage("accessToken", hooks.getTestValueProvider().getLocalStorageAccessTokenUserB());
        hooks.getLocalStorageJS().setItemLocalStorage("userId", hooks.getTestValueProvider().getLocalStorageUserIdUserB());
        hooks.getLocalStorageJS().setItemLocalStorage("refreshToken", hooks.getTestValueProvider().getLocalStorageRefreshTokenUserB());
        hooks.getLocalStorageJS().setItemLocalStorage("name", hooks.getTestValueProvider().getLocalStorageNameUserB());
        hooks.getDriver().navigate().refresh();
    }

    @When("User B navigates to the Eco News page")
    public void userBNavigatesToEcoNews() {
        homePage.waitUntilPageLouder();
        homePage.getHeader().goToEcoNews();
    }

    @And("User B opens the first news item")
    public void userBOpensFirstNews() {
        newsPage = new EcoNewsPage(hooks.getDriver()).clickFirstNewsPage();
    }

    @Then("the \"Edit news\" button should not be visible to User B")
    public void editButtonNotVisible() {
        softAssert.assertFalse(newsPage.isEditNewsButtonVisible(), "Edit button is visible for another user");
    }

    public void cleanUp() throws InterruptedException {
        userAIsLoggedIn();
        homePage.getHeader()
                .goToEcoNews()
                .clickFirstNewsPage()
                .clickDeleteButton()
                .clickYesButton();
        Thread.sleep(3000);
    }

    @And("User B logs out")
    public void userBLogsOut() {
        userALogsOut();
    }

    @And("User A deletes the created news post")
    public void userADeletesTheCreatedNewsPost() throws InterruptedException {
        homePage.getHeader()
                .goToEcoNews()
                .clickFirstNewsPage()
                .clickDeleteButton()
                .clickYesButton();
        Thread.sleep(3000);
    }
}
