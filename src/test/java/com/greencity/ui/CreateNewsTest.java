package com.greencity.ui;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewsTest extends TestRunnerWithUser {

    @Test
    public void testCheckingFormDisplayCreateNews() {

        homePage.getHeader()
                .goToEcoNews()
                .clickCreateNews();

        CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage(driver);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createEditNewsPage.getTittleCharterCounter().isDisplayed());
        softAssert.assertTrue(createEditNewsPage.getBrowserLink().isDisplayed());
        softAssert.assertTrue(createEditNewsPage.getAuthorLabel().isEnabled());
        softAssert.assertTrue(createEditNewsPage.getDateLabel().isEnabled());
        softAssert.assertTrue(createEditNewsPage.getSourcePlaceholder().isDisplayed());
        softAssert.assertTrue(createEditNewsPage.getCancelButton().isDisplayed());
        softAssert.assertTrue(createEditNewsPage.getPreviewButton().isDisplayed());
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isDisplayed());

    }
}
