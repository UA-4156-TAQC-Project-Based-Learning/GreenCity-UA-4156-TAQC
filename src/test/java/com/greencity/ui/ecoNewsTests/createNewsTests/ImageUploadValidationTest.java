package com.greencity.ui.ecoNewsTests.createNewsTests;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class ImageUploadValidationTest extends TestRunnerWithUser {

    private EcoNewsPage ecoNewsPage;

    @BeforeMethod
    @Description("Before method for image upload validation class")
    public void navigateToEcoNews(){
        ecoNewsPage = homePage.getHeader().goToEcoNews();
    }

    @Test
    @Description("This is navigate to create news page which verify that we at create news page")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    @Feature("Navigate To Create News Page")
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
    @Description("This is positive test for upload image. In this test we upload image which is 5mb and have .png format")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    @Feature("Image Upload Successfully")
    public void imageUploadSuccessfullyTest() {
        File image = new File("src/test/resources/imagesForTests/5mb.png");

        CreateEditNewsPage createEditNewsPage = ecoNewsPage
                .clickCreateButton();

        createEditNewsPage.uploadImage(image.getAbsolutePath());

        Assert.assertTrue(createEditNewsPage.getImagePresentation().isDisplayed());
    }

    @DataProvider(name = "invalidImageFiles")
    public Object[][] invalidImageFiles() {
        return new Object[][] {
                { new File("src/test/resources/imagesForTests/15mb.jpg") },
                { new File("src/test/resources/imagesForTests/download.gif") }
        };
    }

    @Test(dataProvider = "invalidImageFiles")
    @Description("This is negative test for upload image. In this test we upload images which are 15mb or have .gif format")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("16")
    @Feature("Image Don`t upload")
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
