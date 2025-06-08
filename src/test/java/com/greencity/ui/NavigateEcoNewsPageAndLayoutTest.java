package com.greencity.ui;

import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class NavigateEcoNewsPageAndLayoutTest extends TestRunnerWithUser {

    @Test
    public void verifyNavigateToEcoNewsPageAndLayoutAnauthorized() {
        driver.get(testValueProvider.getBaseUIUrl());
        EcoNewsPage ecoNewsPage = homePage
                .getHeader()
                .goToEcoNews();

        String currentUrl = driver.getCurrentUrl();

        SoftAssert softAssert = new SoftAssert();

//Use assertions to verify that the current URL includes /news or expected route.
        softAssert.assertNotNull(currentUrl, "Current URL should not be null");
        softAssert.assertTrue(currentUrl.contains("/news"), "Current url does not contain '/news'");

//Check presence of header, footer, and nav elements using known selectors.
        softAssert.assertTrue(ecoNewsPage.getHeader().getRootElement().isDisplayed(), "Header is not visible");
        softAssert.assertTrue(ecoNewsPage.getFooter().getRootElement().isDisplayed(), "Footer is not visible");
        softAssert.assertTrue(ecoNewsPage.getNavigationPanel().isDisplayed(), "Navigation panel is not visible");

//Assert that page title text equals "Eco news".
        softAssert.assertEquals(ecoNewsPage.getTitle().getText(),"Eco news", "Page title is not as expected. Expected: Eco News. But Actual tile is: " + ecoNewsPage.getTitle().getText());

//Locate and verify visibility of the filter controls, "Create" button, and toggle options for gallery and list view.
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

