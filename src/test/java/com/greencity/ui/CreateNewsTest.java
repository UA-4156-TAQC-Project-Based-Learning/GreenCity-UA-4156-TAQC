package com.greencity.ui;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateNewsTest extends TestRunnerWithUser {

    @Test
    public void testCreateNewsFormElementsAreDisplayedCorrectly() {

        CreateEditNewsPage createEditNewsPage = homePage.getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        String contentEditableAttr = createEditNewsPage.getAuthorLabel().getAttribute("contenteditable");
        boolean isAuthorEditable  = contentEditableAttr != null && contentEditableAttr.equals("true");
        String contentEditableAttr2 = createEditNewsPage.getDateLabel().getAttribute("contenteditable");
        boolean isDateEditable  = contentEditableAttr2 != null && contentEditableAttr2.equals("true");

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createEditNewsPage.getTitleCharacterCounter().isDisplayed(),"Title field is not displayed.");
        softAssert.assertTrue(createEditNewsPage.getBrowserLabel().isDisplayed(),"Browse label is not displayed");
        softAssert.assertNotNull(createEditNewsPage.getBrowserLink(),"Browse input (upload) is not found");
        softAssert.assertEquals(createEditNewsPage.getSourcePlaceholder().getText(),"Please add the link of original article/news/post. Link must start with http(s)://");
        softAssert.assertEquals(createEditNewsPage.getContentCharacterCounter().getText(),"Must be minimum 20 and maximum 63 206 symbols");
        softAssert.assertFalse(isAuthorEditable,"Author field should be non-editable");
        softAssert.assertFalse(isDateEditable, "Date field should be non-editable");
        softAssert.assertTrue(createEditNewsPage.getCancelButton().isDisplayed(),"Cancel button not displayed");
        softAssert.assertTrue(createEditNewsPage.getPreviewButton().isDisplayed(),"Preview button not displayed");
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isDisplayed(),"Publish button not displayed");

        softAssert.assertAll();

    }
}
