package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.greencity.ui.pages.CreateEditNewsPage.generateText;

public class ValidateContentFieldTest extends TestRunnerWithUser {

    @Test
    public void verifyTooShortTextInContentField() {

        String title = generateText(20);
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
        String expectedContentInfoMessageText = "Must be minimum 20 and maximum 63 206 symbols";

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

        String title = generateText(20);
        int tooLongTextLength = 63207;
        String longText = generateText(tooLongTextLength);

        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(title)
                .clickTag(NewsTags.EDUCATION_TAG)
                .enterContentJS(longText);

        String actualContentCounterTextWithWarning = createEditNewsPage.getContentCounter().getText();
        String expectedContentCounterTextWithWarning = "The maximum character length is greater than 1";

        String actualContentCounterColorWithWarning = createEditNewsPage.getContentCounter().getCssValue("color");
        String expectedContentCounterColorWithWarning = "rgba(235, 24, 13, 1)";

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualContentCounterTextWithWarning, expectedContentCounterTextWithWarning,
                "The actual Content counter text with warning does not match the expected text.");

        softAssert.assertEquals(actualContentCounterColorWithWarning, expectedContentCounterColorWithWarning,
                "The actual Content Info message color with warning does not match the expected color.");

        softAssert.assertTrue(createEditNewsPage.getContentCounter().getAttribute("class").contains("warning"));
        softAssert.assertFalse(createEditNewsPage.getPublishButton().isEnabled(), "Publish button should be disabled");

        createEditNewsPage.getContentInput().sendKeys(Keys.BACK_SPACE);

        String actualContentCounterText = createEditNewsPage.getContentCounter().getText();
        String expectedContentCounterText = "Number of characters: 63206";

       String actualContentCounterColor = createEditNewsPage.getContentCounter().getCssValue("color");
       String expectedContentCounterColor = "rgba(100, 114, 125, 1)";

        softAssert.assertEquals(actualContentCounterText, expectedContentCounterText,
                "The actual Content counter text does not match the expected text.");

        softAssert.assertEquals(actualContentCounterColor, expectedContentCounterColor,
                "The actual Content Info message color does not match the expected color.");

        softAssert.assertTrue(createEditNewsPage.getPublishButton().isEnabled(), "Publish button should be enabled");

        softAssert.assertAll();
    }

    @Test
    public void verifyValidLengthTextInContentField() {

        String title = generateText(20);
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

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver);

        String actualTitleOfFirstNews = ecoNewsPage.getNewsComponents().get(0).getTitle().getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(driver.getCurrentUrl().contains("/news"), "EcoNews Page is not opened");

        softAssert.assertEquals(actualTitleOfFirstNews, title);
        softAssert.assertAll();
    }
}
