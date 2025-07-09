package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.asserts.SoftAssert;

public class EcoNewsSteps {

    private final Hooks hooks;
    private final SoftAssert softAssert;
    private final HomePage homePage;
    private NewsPage newsPage;
    private EcoNewsPage ecoNewsPage;


    public EcoNewsSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
        this.homePage = new HomePage(hooks.getDriver());
    }

    private EcoNewsPage getEcoNewsPage() {
        return new EcoNewsPage(hooks.getDriver());
    }

    @Given("User A is logged into the system")
    @Description("Log in as User A by injecting session tokens via local storage")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userAIsLoggedIn() {
        hooks.getLocalStorageJS().setItemLocalStorage("accessToken", hooks.getTestValueProvider().getLocalStorageAccessToken());
        hooks.getLocalStorageJS().setItemLocalStorage("userId", hooks.getTestValueProvider().getLocalStorageUserId().toString());
        hooks.getLocalStorageJS().setItemLocalStorage("refreshToken", hooks.getTestValueProvider().getLocalStorageRefreshToken());
        hooks.getLocalStorageJS().setItemLocalStorage("name", hooks.getTestValueProvider().getLocalStorageName());
        hooks.getDriver().navigate().refresh();
    }

    @And("User A creates a new news post with title {string} and tag {string}")
    @Description("Create a news post under User A account with specified title and tag")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userACreatesNews(String title, String tag) {
        CreateEditNewsPage createPage = homePage
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        createPage.enterTitle(title)
                .enterContent("This is test content for verifying visibility of Edit button.")
                .clickTag(NewsTags.valueOf(tag.toUpperCase() + "_TAG"))
                .clickPublish();
        hooks.getDriver().navigate().refresh();
    }

    @And("User A logs out")
    @Description("Log out User A by clearing local storage")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userALogsOut() {
        hooks.getLocalStorageJS().clearLocalStorage();
        hooks.getDriver().navigate().refresh();
    }

    @And("User B is logged into the system")
    @Description("Log in as User B by injecting their session tokens via local storage")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userBIsLoggedIn() {
        hooks.getLocalStorageJS().setItemLocalStorage("accessToken", hooks.getTestValueProvider().getLocalStorageAccessTokenUserB());
        hooks.getLocalStorageJS().setItemLocalStorage("userId", hooks.getTestValueProvider().getLocalStorageUserIdUserB());
        hooks.getLocalStorageJS().setItemLocalStorage("refreshToken", hooks.getTestValueProvider().getLocalStorageRefreshTokenUserB());
        hooks.getLocalStorageJS().setItemLocalStorage("name", hooks.getTestValueProvider().getLocalStorageNameUserB());
        hooks.getDriver().navigate().refresh();
    }

    @When("User B navigates to the Eco News page")
    @Description("Navigate to the Eco News page while logged in as User B")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userBNavigatesToEcoNews() {
        homePage.waitUntilPageLouder();
        homePage.getHeader()
                .goToEcoNews();
    }

    @And("User B opens the first news item")
    @Description("Open the first available news item in the Eco News list")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userBOpensFirstNews() {

        newsPage = new EcoNewsPage(hooks.getDriver())
                .clickFirstNewsPage();
    }

    @Then("the \"Edit news\" button should not be visible to User B")
    @Description("Verify that the 'Edit news' button is not visible to a user who did not create the news post")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void editButtonNotVisible() {
        softAssert.assertFalse(newsPage.isEditNewsButtonVisible(), "Edit button is visible for another user");
    }

    @And("User B logs out")
    @Description("Log out User B by clearing local storage")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userBLogsOut() {
        userALogsOut();
    }

    @And("User A deletes the created news post")
    @Description("User A deletes the previously created news post to clean up")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("192")
    public void userADeletesTheCreatedNewsPost() {
        homePage.getHeader()
                .goToEcoNews()
                .clickFirstNewsPage()
                .clickDeleteButton()
                .clickYesButton();
        hooks.getDriver().navigate().refresh();
    }

    @When("click the Create news")
    public void clickCreateNews() {
        getEcoNewsPage().clickCreateNewsButton();
    }

    @Then("the news article with title {string}, text {string}, and tag {string} should be saved in the eco-news page")
    public void verifyNewsIsVisible(String expectedTitle, String expectedText, String expectedTag) {

        NewsComponent createdNews = getEcoNewsPage().findNewsByTitle(expectedTitle);

        hooks.getSoftAssert().assertEquals(createdNews.getTitle().getText(), expectedTitle, "The title should be the same");
        hooks.getSoftAssert().assertEquals(createdNews.getContent().getText(), expectedText, "The text should be the same");
        hooks.getSoftAssert().assertEquals(createdNews.getTag().getText(), expectedTag, "The tag should be the same");
    }

    @When("click on their own news article with title {string}")
    public void clickOwnNews(String title) {
        getEcoNewsPage().findNewsByTitleAndClick(title);
    }
}