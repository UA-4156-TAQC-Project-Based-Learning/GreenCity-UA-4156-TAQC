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

import java.io.File;

import static com.greencity.utils.LoginUtil.loginViaLocalStorage;

public class EcoNewsSteps {

    private final Hooks hooks;
    private final SoftAssert softAssert;
    private final HomePage homePage;
    private NewsPage newsPage;
    private EcoNewsPage ecoNewsPage;
    private CreateEditNewsPage createEditNewsPage;

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
        loginViaLocalStorage(hooks.getDriver(), hooks.getLocalStorageJS(), hooks.getTestValueProvider());
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

    @Given("the user is logged in to the GreenCity system")
    @Description("Log in by setting localStorage tokens for default user")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theUserIsLoggedInToTheGreenCitySystem() {
        loginViaLocalStorage(hooks.getDriver(), hooks.getLocalStorageJS(), hooks.getTestValueProvider());
    }

    @And("the user navigates to the \"Create News\" page")
    @Description("Open the Create News page")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theUserNavigatesToThePage() {
        ecoNewsPage = homePage.getHeader().goToEcoNews();
        createEditNewsPage = ecoNewsPage.clickCreateButton();
    }

    @When("the user uploads a {string} image")
    @Description("Upload an image by filename from test resources")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theUserUploadsAImage(String fileName) {
        String path = "src/test/resources/imagesForTests/" + fileName;
        File image = new File(path);
        createEditNewsPage.uploadImage(image.getAbsolutePath());
    }

    @Then("the image should be uploaded successfully without any error")
    @Description("Check that uploaded image is displayed in preview")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theImageShouldBeUploadedSuccessfullyWithoutAnyError() {
        softAssert.assertTrue(createEditNewsPage.getImagePresentation().isDisplayed(),
                "Uploaded image is not displayed");
    }

    @Then("the system should show an error message {string}")
    @Description("Check that warning is displayed for invalid image")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theSystemShouldShowAnErrorMessage(String expectedMessage) {
        softAssert.assertTrue(createEditNewsPage.getImageWarning().isDisplayed(),
                "Image warning is not visible");
        softAssert.assertEquals(createEditNewsPage.getImageWarning().getText(), expectedMessage,
                "Unexpected image warning text");

    }

    @And("the image field should be highlighted in red")
    @Description("Verify image field error state visually or by class")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theImageFieldShouldBeHighlightedInRed() {
        String style = createEditNewsPage.getImageWarning().getAttribute("class");
        softAssert.assertTrue(createEditNewsPage.getImageWarning().isDisplayed() || style.contains("warning-color"),
                "Error class not applied to image warning");
    }

    @When("the user clicks the \"Create News\" button on the Eco News page")
    @Description("Click Create News button from Eco News section")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void theUserClicksTheButtonOnTheEcoNewsPage() {
        theUserNavigatesToThePage();
    }

    @Then("the page should display a title containing {string} or {string}")
    @Description("Verify page header contains create keyword in EN or UA")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    public void thePageShouldDisplayATitleContainingOr(String wordEn, String wordUa) {
        String header = createEditNewsPage.getTitleHeader().getText().toLowerCase();
        softAssert.assertTrue(header.contains(wordEn.toLowerCase()) || header.contains(wordUa.toLowerCase()),
                "Page title doesn't contain expected text: " + header);
    }


    @And("User creates a new news post with title {string} and tag {string}")
    @Description("Create a news post with specified title and tag")
    @Owner("Prykhodchenko Oleksandra")
    public void userCreatesANewNewsPostWithTitleAndTag(String arg0, String arg1) {
        userACreatesNews(arg0, arg1);
    }


    @And("user navigates to the {string} Page")
    public void userNavigatesToThePage(String arg0) {
        homePage
                .getHeader()
                .goToMySpace();
    }
}