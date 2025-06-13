package com.greencity.ui.ecoNewsTests.editNewsTests;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class EditNewsWithEmptyTitleTest extends TestRunnerWithUser {

    @Feature("Edit news")
    @Owner("kokun.v")
    @Issue("7C38")
    @Description("Check that a warning appears for an empty title and the form is not submitted.")
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
