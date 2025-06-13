package com.greencity.ui;

import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NavigateEcoNewsPageAndLayoutTest extends TestRunnerWithUser {
    @Issue("94")
    @Owner("Yuliia Terentieva")
    @Description("Verify that clicking the Eco news tab or the Read all button on the Home page correctly navigates the user to the Eco news landing page, and that the landing page displays all required components.")
    @Feature("Home Page")
    @Test
    public void verifyNavigateToEcoNewsPageAndLayoutAnauthorized() {
        driver.get(testValueProvider.getBaseUIUrl());
        EcoNewsPage ecoNewsPage = homePage
                .getHeader()
                .goToEcoNews();

        String currentUrl = driver.getCurrentUrl();

        SoftAssert softAssert = new SoftAssert();


        softAssert.assertNotNull(currentUrl, "Current URL should not be null");
        softAssert.assertTrue(currentUrl.contains("/news"), "Current url does not contain '/news'");

        softAssert.assertTrue(ecoNewsPage.getHeader().getRootElement().isDisplayed(), "Header is not visible");
        softAssert.assertTrue(ecoNewsPage.getFooter().getRootElement().isDisplayed(), "Footer is not visible");
        softAssert.assertTrue(ecoNewsPage.getNavigationPanel().isDisplayed(), "Navigation panel is not visible");

        softAssert.assertEquals(ecoNewsPage.getTitle().getText(),"Eco news", "Page title is not as expected. Expected: Eco News. But Actual tile is: " + ecoNewsPage.getTitle().getText());

        softAssert.assertTrue(!ecoNewsPage.getFilteringOptions().isEmpty(), "Filtering options are not visible or empty.");
        softAssert.assertTrue(ecoNewsPage.getListView().isDisplayed(), "List view button is not visible");
        softAssert.assertTrue(ecoNewsPage.getGalleryView().isDisplayed(), "Gallery view button is not visible");

        softAssert.assertTrue(!ecoNewsPage.getNewsCards().isEmpty(), "No news cards are displayed.");

        softAssert.assertAll();

    }

    @Test
    public void verifyNavigateToEcoNewsPageAndLayoutWithUser()  {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");

        EcoNewsPage ecoNewsPage = homePage
                .getHeader()
                .goToEcoNews();

        String currentUrl = driver.getCurrentUrl();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(currentUrl.contains("/news"), "Current url does not contain '/news'");
        softAssert.assertTrue(ecoNewsPage.getCreateNewsButton().isDisplayed(), "Create button is not visible.");
        softAssert.assertAll();
    }
}

