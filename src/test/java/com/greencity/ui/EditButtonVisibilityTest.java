package com.greencity.ui;

import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditButtonVisibilityTest extends TestRunnerWithUser {
    @Issue("34")
    @Owner("Yuliia Terentieva")
    @Description("Verify that only the author of the news can see the Edit new sbutton.")
    @Feature("")
    @Test
    public void verifyEditButtonVisibleOnlyForAuthor() {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver)
                .getHeader()
                .goToEcoNews();

        NewsPage newsPage = ecoNewsPage
                .clickFirstNewsPage();

        boolean isEditButtonVisible = newsPage
                .isEditNewsButtonVisible();

        Assert.assertTrue(isEditButtonVisible, "Edit button is not visible.");
    }
}
