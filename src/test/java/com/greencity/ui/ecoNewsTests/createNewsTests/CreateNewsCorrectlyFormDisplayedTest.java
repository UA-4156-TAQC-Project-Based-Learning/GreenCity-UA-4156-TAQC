package com.greencity.ui.ecoNewsTests.createNewsTests;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewsCorrectlyFormDisplayedTest extends TestRunnerWithUser {

    @Feature("Create news")
    @Owner("kokun.v")
    @Issue("7C13")
    @Description("Verify that an element in the form is displayed correctly.")
    @Test
    public void testCreateNewsFormElementsAreDisplayedCorrectly() {

        CreateEditNewsPage createEditNewsPage = homePage.getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createEditNewsPage.getTitleCharacterCounter().isDisplayed(),"Title field is not displayed.");
        softAssert.assertTrue(createEditNewsPage.getBrowserLabel().isDisplayed(),"Browse label is not displayed");
        softAssert.assertNotNull(createEditNewsPage.getBrowserLink(),"Browse input (upload) is not found");
        softAssert.assertEquals(createEditNewsPage.getSourcePlaceholder().getText(),"Please add the link of original article/news/post. Link must start with http(s)://");
        softAssert.assertEquals(createEditNewsPage.getContentCharacterCounter().getText(),"Must be minimum 20 and maximum 63 206 symbols");
        softAssert.assertFalse(createEditNewsPage.isAuthorEditable(),"Author field should be non-editable");
        softAssert.assertFalse(createEditNewsPage.isDateEditable(), "Date field should be non-editable");
        softAssert.assertTrue(createEditNewsPage.getCancelButton().isDisplayed(),"Cancel button not displayed");
        softAssert.assertTrue(createEditNewsPage.getPreviewButton().isDisplayed(),"Preview button not displayed");
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isDisplayed(),"Publish button not displayed");

        softAssert.assertAll();

    }
}
