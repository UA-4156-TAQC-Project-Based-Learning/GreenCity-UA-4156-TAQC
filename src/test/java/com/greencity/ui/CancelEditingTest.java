package com.greencity.ui;

import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CancelEditingTest extends TestRunnerWithUser {
    @Test
    public void declineEditTitle() {

        driver.get(testValueProvider.getBaseUIUrl() + "/profile");


        EcoNewsPage ecoNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews();

        String originalNewsTitle = ecoNewsPage.getNewsCards().getFirst().getText();

        ecoNewsPage
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .enterTitle("New temporary title")
                .clickCancelButton()
                .clickYesCancel();

        String currentTitle = new EcoNewsPage(driver).getNewsCards().getFirst().getText();

       Assert.assertEquals(currentTitle,originalNewsTitle,"Title was changed after cancel editing. Original title was: " + originalNewsTitle + "Current title is: " + currentTitle);
    }
}
