package com.greencity.ui.ecoNewsTests.createNewsTests;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class ImageUploadValidationTest extends TestRunnerWithUser {

    @Test
    public void navigateToCreateNewsPageTest() {
        homePage.getHeader().goToEcoNews();
        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);
        ecoNewsPage.clickCreateButton();
        CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage(driver);
        String headerText = createEditNewsPage.getTitleHeader().getText().toLowerCase();
        Assert.assertTrue(headerText.contains("create") || headerText.contains("створити"),
                "The headline text does not contain the expected word to create a news story: " + headerText);
    }

    @Test
    public void imageUploadSuccessfullyTest() {
        navigateToCreateNewsPageTest();
        CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage(driver);

        File image = new File("src/test/resources/imagesForTests/5mb.png");
        String path = image.getAbsolutePath();

        createEditNewsPage.uploadImage(path);

        Assert.assertTrue(driver.findElement(By.xpath(".//div[contains(@class, 'ngx-ic-cropper')]")).isDisplayed());
    }

    @DataProvider(name = "invalidImageFiles")
    public Object[][] invalidImageFiles() {
        return new Object[][] {
                { new File("src/test/resources/imagesForTests/15mb.jpg") },
                { new File("src/test/resources/imagesForTests/download.gif") }
        };
    }

    @Test(dataProvider = "invalidImageFiles")
    public void imageUploadNegativeTest(File imageFile) {
        navigateToCreateNewsPageTest();
        CreateEditNewsPage createEditNewsPage = new CreateEditNewsPage(driver);

        createEditNewsPage.uploadImage(imageFile.getAbsolutePath());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.findElement(By.xpath(".//p[contains(@class, 'warning')]")).isDisplayed());
        softAssert.assertEquals(driver.findElement(By.xpath(".//p[contains(@class, 'warning')]")).getText(),
                "Upload only PNG or JPG. File size must be less than 10MB");
        softAssert.assertAll();
    }
}
