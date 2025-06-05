package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        softAssert.assertEquals(actualContentInfoMessageColor, expectedContentInfoMessageColor, "The actual Content Info message color does not match the expected color.");
        softAssert.assertEquals(actualContentInfoMessageText, expectedContentInfoMessageText, "The actual Content Info message text does not match the expected text.");

        softAssert.assertAll();


    }

    @Test
    public void verifyTooLongTextInContentField() {
        int tooLongTextLength = 63200;
    }


    @Test
    public void verifyValidLengthTextInContentField() {
        int validTextLength = 25;


    }

    public static String generateText(int length) {
        String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. ";
        StringBuilder text = new StringBuilder();

        while (text.length() < length) {
            text.append(loremIpsum);
        }
        return text.substring(0, length);
    }

}
