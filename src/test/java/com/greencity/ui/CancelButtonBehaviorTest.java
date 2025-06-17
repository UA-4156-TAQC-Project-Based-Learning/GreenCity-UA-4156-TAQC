package com.greencity.ui;

import com.greencity.ui.components.baseComponents.CancelConfirmModal;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

@Feature("Cancel Button Functionality")
public class CancelButtonBehaviorTest extends TestRunnerWithUser {

    @Step("Open Create News Page and enter test data")
    private CreateEditNewsPage prepareCreateNewsPageWithTestData() {
        CreateEditNewsPage createNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        createNewsPage.enterTitle("Test");
        createNewsPage.enterContent("Test content with 20 chars");

        return createNewsPage;
    }

    @Issue("19")
    @Description("Verify that clicking Cancel and confirming redirects user to the News page, discarding changes")
    @Owner("Svitlana Kovalova")
    @Test
    public void testCancelNewsCreationAndConfirmCancel() {
        CreateEditNewsPage createNewsPage = prepareCreateNewsPageWithTestData();

        CancelConfirmModal confirmModal = createNewsPage.clickCancelButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(confirmModal.isDisplayed(), "Confirmation modal should be displayed");
        softAssert.assertTrue(confirmModal.getTitleText().getText()
                        .contains("content will be lost"),
                "Modal title should contain content loss warning");
        softAssert.assertTrue(confirmModal.getSubtitleText().getText()
                        .contains("cancel"),
                "Modal subtitle should contain cancel confirmation");

        confirmModal.clickYesCancel();
        softAssert.assertTrue(driver.getCurrentUrl().endsWith("/news") || driver.getCurrentUrl().contains("/news?"), "User should be redirected to News listing page");
        softAssert.assertAll();
    }

    @Issue("19")
    @Description("Verify that clicking Cancel and choosing to continue editing keeps the filled form intact")
    @Owner("Svitlana Kovalova")
    @Test
    public void testCancelNewsCreationAndContinueEditing() {
        CreateEditNewsPage createNewsPage = prepareCreateNewsPageWithTestData();

        CancelConfirmModal confirmModal = createNewsPage.clickCancelButton();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(confirmModal.isDisplayed(), "Confirmation modal should be displayed");
        confirmModal.clickContinueEditing();

        softAssert.assertFalse(confirmModal.isDisplayed(), "Confirmation modal should be closed");
        softAssert.assertTrue(createNewsPage.isFormDisplayed(), "Create News form should remain open");
        softAssert.assertEquals(createNewsPage.getTitleText(), "Test", "Title should be intact");
        softAssert.assertEquals(createNewsPage.getContentText(), "Test content with 20 chars", "Content should be intact");
        softAssert.assertAll();
    }
}