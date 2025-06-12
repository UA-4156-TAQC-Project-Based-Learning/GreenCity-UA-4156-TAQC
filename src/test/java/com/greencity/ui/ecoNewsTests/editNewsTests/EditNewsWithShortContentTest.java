package com.greencity.ui.ecoNewsTests.editNewsTests;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditNewsWithShortContentTest extends TestRunnerWithUser {

    @Test
    public void testEditNewsWithShortContent() {

        CreateEditNewsPage createEditNewsPage = homePage.getHeader()
                .goToEcoNews()
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .enterContent("")
                .enterSource(" ");

        SoftAssert sf = new SoftAssert();
        sf.assertTrue(createEditNewsPage.getDescriptionWarningTextarea().isDisplayed(),"Warning is not displayed in the content.");
        sf.assertTrue(createEditNewsPage.isStillOnEditPage(),"Form should be not submitted.");
        sf.assertAll();
    }
}
