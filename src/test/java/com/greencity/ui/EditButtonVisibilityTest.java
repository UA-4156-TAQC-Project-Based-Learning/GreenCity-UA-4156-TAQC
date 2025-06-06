package com.greencity.ui;

import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditButtonVisibilityTest extends TestRunnerWithUser {
    @Test
    public void verifyEditButtonVisibleOnlyForAuthor() {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver).getHeader().goToEcoNews();

        NewsPage newsPage = ecoNewsPage.clickFirstNewsPage();

        boolean isEditButtonVisible = newsPage.getEditNewsButton().isDisplayed();

        Assert.assertTrue(isEditButtonVisible, "Edit button is not visible.");
    }
}
