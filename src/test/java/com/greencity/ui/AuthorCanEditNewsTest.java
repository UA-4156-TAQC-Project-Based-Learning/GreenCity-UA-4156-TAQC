package com.greencity.ui;

import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AuthorCanEditNewsTest extends TestRunnerWithUser {
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
