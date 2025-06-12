package com.greencity.ui;

import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorCanEditNewsTest extends TestRunnerWithUser {
    @Issue("36")
    @Owner("Yuliia Terentieva")
    @Description("Verify that the author can edit their own news and the changes are saved.")
    @Feature("")
    @Test
    public void authorCanEditOwnNews() {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");


        EcoNewsPage ecoNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews();
        String originalNewsTitle = ecoNewsPage.getNewsComponents().getFirst().getTitle().getText();

        ecoNewsPage
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .enterTitle("New title")
                .clickPublish();

        String currentTitle = new EcoNewsPage(driver).getNewsComponents().getFirst().getTitle().getText();

        Assert.assertEquals(currentTitle,"New title", "Title is not as expected." + "Expected: New title. But displayed: " + currentTitle);
    }
}
