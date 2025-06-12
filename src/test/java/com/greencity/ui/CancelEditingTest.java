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

public class CancelEditingTest extends TestRunnerWithUser {
    @Issue("41")
    @Owner("Yuliia Terentieva")
    @Description("Verify that clicking Cancel discards changes and returns to the original view.")
    @Feature("")
    @Test
    public void declineEditTitle() {

        driver.get(testValueProvider.getBaseUIUrl() + "/profile");


        EcoNewsPage ecoNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews();

        String originalNewsTitle = ecoNewsPage
                .getNewsComponents()
                .getFirst()
                .getTitle()
                .getText();

        ecoNewsPage
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .enterTitle("New temporary title")
                .clickCancelButton()
                .clickYesCancel();

        String currentTitle = new EcoNewsPage(driver).getNewsComponents().getFirst().getTitle().getText();

       Assert.assertEquals(currentTitle,originalNewsTitle,"Title was changed after cancel editing. Original title was: " + originalNewsTitle + "Current title is: " + currentTitle);
    }
}
