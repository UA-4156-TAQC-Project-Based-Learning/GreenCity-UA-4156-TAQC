package com.greencity.ui.ecoNewsTests.createNewsTests;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class ImageUploadValidationTest extends TestRunnerWithUser {

    private EcoNewsPage ecoNewsPage;

    @BeforeMethod
    public void navigateToEcoNews(){
        ecoNewsPage = homePage.getHeader().goToEcoNews();
    }

    @Test
    public void navigateToCreateNewsPageTest() {
        String headerText = ecoNewsPage
                .clickCreateButton()
                .getTitleHeader()
                .getText()
                .toLowerCase();

        Assert.assertTrue(headerText.contains("create") || headerText.contains("створити"),
                "The headline text does not contain the expected word to create a news story: " + headerText);
    }

    @Test
    public void imageUploadSuccessfullyTest() {
        File image = new File("src/test/resources/imagesForTests/5mb.png");

        CreateEditNewsPage createEditNewsPage = ecoNewsPage
                .clickCreateButton();

        createEditNewsPage.uploadImage(image.getAbsolutePath());

        Assert.assertTrue(createEditNewsPage.getCropper().isDisplayed());
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
        CreateEditNewsPage createEditNewsPage = ecoNewsPage
                .clickCreateButton();

        createEditNewsPage.uploadImage(imageFile.getAbsolutePath());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(createEditNewsPage.getImageWarning().isDisplayed());
        softAssert.assertEquals(createEditNewsPage.getImageWarning().getText(),
                "Upload only PNG or JPG. File size must be less than 10MB");
        softAssert.assertAll();
    }
}
