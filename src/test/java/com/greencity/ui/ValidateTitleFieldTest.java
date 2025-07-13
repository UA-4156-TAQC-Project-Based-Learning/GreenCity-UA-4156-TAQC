package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.greencity.ui.pages.CreateEditNewsPage.generateText;

public class ValidateTitleFieldTest extends TestRunnerWithUser {

    @Issue("14")
    @Owner("Maryna Rasskazova")
    @Description("Verify the validation of an empty Title field ")
    @Feature("Create News")
    @Test
    public void verifyEmptyTitleField(){
        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        createEditNewsPage.getTitleInput().click();
        createEditNewsPage
                .clickTag(NewsTags.ADS_TAG)
                .getContentInput()
                .sendKeys(generateText(25));

        String actualTitleBorderColor = createEditNewsPage.getTitleInput().getCssValue("border-color");
        String expectedTitleBorderColor = "rgb(255, 0, 0)";
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitleBorderColor, expectedTitleBorderColor,
                "The actual title border color does not match the expected color.");
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled());
        softAssert.assertAll();
    }

    @Issue("14")
    @Owner("Maryna Rasskazova")
    @Description("Verify the validation of the Title field with text that exceeds the maximum length")
    @Feature("Create News")
    @Test
    public void verifyTooLongTextInTitleField() {

        int titleLength = 171;
        String titleText = generateText(titleLength);

        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(titleText)
                .clickTag(NewsTags.INITIATIVES_TAG)
                .enterContent(generateText(25));

        createEditNewsPage.getTitleInput().click();
        createEditNewsPage.getContentInput().click();

        String actualTitleBorderColor = createEditNewsPage.getTitleInput().getCssValue("border-color");
        String expectedTitleBorderColor = "rgb(255, 0, 0)";

        String actualTitleCounterText=createEditNewsPage.getTitleCounter().getText();
        String expectedTitleCounterText="171/170";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTitleBorderColor, expectedTitleBorderColor,
                "The actual title border color does not match the expected color.");
        softAssert.assertEquals(actualTitleCounterText, expectedTitleCounterText);
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled());
        softAssert.assertAll();

    }

    @Issue("14")
    @Owner("Maryna Rasskazova")
    @Description("Verify that text between 1 and 170 characters in the Title field is accepted")
    @Feature("Create News")
    @Test
    public void verifyValidTextLengthInTitleField() {

        int validTitleLength = 9;
        int validContentLength = 25;

        String validTitle = generateText(validTitleLength);
        String validContent = generateText(validContentLength);

        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(validTitle)
                .clickTag(NewsTags.EVENTS_TAG)
                .enterContent(validContent);

        String actualTitleBorderColor = createEditNewsPage.getTitleInput().getCssValue("border-color");
        String expectedTitleBorderColor = "rgb(156, 167, 176)";

        String actualTitleCounterText=createEditNewsPage.getTitleCounter().getText();
        String expectedTitleCounterText="9/170";

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualTitleBorderColor, expectedTitleBorderColor,
                "The actual title border color does not match the expected color.");
        softAssert.assertEquals(actualTitleCounterText, expectedTitleCounterText);
        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled());
        softAssert.assertAll();
    }

}
