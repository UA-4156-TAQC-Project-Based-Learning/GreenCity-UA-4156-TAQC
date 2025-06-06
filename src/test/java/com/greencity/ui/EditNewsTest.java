package com.greencity.ui;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditNewsTest extends TestRunnerWithUser {

    @Test
    public void testEditNewsWithEmptyTitle() {

        CreateEditNewsPage createEditNewsPage = homePage.getHeader()
                .goToEcoNews()
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .enterTitle(" ")
                .enterSource("");

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(createEditNewsPage.isTitleFieldHighlightedInRed(),"Title field should be highlighted in red.");
        sf.assertFalse(createEditNewsPage.getEditButton().isEnabled(),"Button edit should be disabled.");
        sf.assertTrue(createEditNewsPage.isStillOnEditPage(),"Form should be not submitted.");
        sf.assertAll();

    }
}
