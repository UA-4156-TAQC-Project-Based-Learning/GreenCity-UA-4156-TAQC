package com.greencity.ui.ecoNewsTests.editNewsTests;

import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;

public class ValidationEditPageImageSize  extends TestRunnerWithUser {

    @Issue("40")
    @Owner("Dmytro Kovalchuk")
    @Description("Verify that user cannot upload image greater than 10MB in edit news page")
    @Feature("Edit news image upload validation")
    @Test
    public void editNewsImageUpload_ShouldNotAllowImagesGreaterThan10MB(){
        SoftAssert softAssert = new SoftAssert();
        File image = new File("src/test/resources/imagesForTests/15mb.jpg");
        String imageSrc = homePage
                .getHeader()
                .goToMySpace()
                .clickTabsByText("My news")
                .clickFirstNew()
                .clickEditNewsButton()
                .uploadImage(image.getAbsolutePath())
                .clickPublish()
                .getHeader()
                .goToMySpace()
                .clickTabsByText("My news")
                .clickFirstNew()
                .getNewsImage()
                .getAttribute("src");

        softAssert.assertTrue(imageSrc.contains("assets/img/icon/econews/news-default-large.png"), "Image was changed");
        softAssert.assertAll();
    }
}
