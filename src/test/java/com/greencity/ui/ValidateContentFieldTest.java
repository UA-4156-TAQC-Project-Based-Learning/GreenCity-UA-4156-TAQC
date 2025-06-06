package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.greencity.ui.pages.CreateEditNewsPage.generateText;

public class ValidateContentFieldTest extends TestRunnerWithUser {

    @Test
    public void verifyTooShortTextInContentField() {

        String title = "Valid title";
        int tooShortTextLength = 19;

        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(title)
                .clickTag(NewsTags.EDUCATION_TAG)
                .enterContent(generateText(tooShortTextLength));

        String actualContentInfoMessageColor = createEditNewsPage.getContentInfoMessage().getCssValue("color");
        String expectedContentInfoMessageColor = "rgba(235, 24, 13, 1)";

        String actualContentInfoMessageText = createEditNewsPage.getContentInfoMessage().getText();
        String expectedContentInfoMessageText = "Must be a minimum of 20 and a maximum of 63,206 symbols.";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualContentInfoMessageColor, expectedContentInfoMessageColor,
                "The actual Content Info message color does not match the expected color.");
        softAssert.assertEquals(actualContentInfoMessageText, expectedContentInfoMessageText,
                "The actual Content Info message text does not match the expected text.");
        softAssert.assertTrue(createEditNewsPage.getContentInfoMessage().getAttribute("class").contains("warning"));
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled());
        softAssert.assertAll();
    }

    @Test
    public void verifyTooLongTextInContentField() {

        String title = "Valid title";
        int tooLongTextLength = 63207;
        String longText = generateText(tooLongTextLength);

        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(title)
                .clickTag(NewsTags.EDUCATION_TAG)
                .enterContentJS(longText);

        int expectedTextLength = 63206;
        int actualTextLength = createEditNewsPage.getContentInput().getText().length();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualTextLength, expectedTextLength,
                "Expected " + expectedTextLength + " text length, but actual length is " + actualTextLength);
        softAssert.assertFalse(createEditNewsPage.getContentInfoMessage().getAttribute("class").contains("warning"));
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled());
        softAssert.assertAll();
    }

    @Test
    public void verifyValidLengthTextInContentField() throws InterruptedException {

        String title = "Valid title";
        int validTextLength = 25;
        String textWithValidLength = generateText(validTextLength);

        new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(title)
                .clickTag(NewsTags.EDUCATION_TAG)
                .enterContent(textWithValidLength)
                .getPublishButton().click();

        EcoNewsPage ecoNewsPage=new EcoNewsPage(driver);

        String actualTitleOfFirstNews=ecoNewsPage.getNewsComponents().get(0).getTitle().getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/news"), "EcoNews Page is not opened");

        softAssert.assertEquals(actualTitleOfFirstNews, title);
        softAssert.assertAll();

    }

}
