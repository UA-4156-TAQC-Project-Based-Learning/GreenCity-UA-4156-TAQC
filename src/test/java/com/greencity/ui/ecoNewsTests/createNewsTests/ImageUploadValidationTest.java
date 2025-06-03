package com.greencity.ui.ecoNewsTests.createNewsTests;

import com.greencity.ui.BaseTest;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ImageUploadValidationTest extends BaseTest {
    @Test
    public void navigateToEcoNewsTest(){
        homePage.getHeader().goToEcoNews();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        Assert.assertTrue(ecoNewsPage.getTitle().isDisplayed(), "The element is not displayed.");
    }

    @Test
    public void navigateToCreateNewsPage() {
        navigateToEcoNewsTest();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.clickCreateButton();
        CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage(driver);
        Assert.assertEquals(createEditNewsPage.getTitleHeader().getText(), "Create news");
    }
}
