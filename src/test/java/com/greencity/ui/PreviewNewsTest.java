package com.greencity.ui;

import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.PreviewNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class PreviewNewsTest extends TestRunnerWithUser {

    @DataProvider(name = "newsData")
    public Object[][] newsDataProvider() {
        return new Object[][] {
                {
                        "Test Preview",
                        "This is a test preview content",
                        LocalDate.now().format(DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH))
                }
        };
    }

    @Test(dataProvider = "newsData")
    public void testUserCanPreviewNewsContent(String expectedTitle, String expectedContent, String expectedDate) {

        CreateEditNewsPage createNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton();

        createNewsPage.enterTitle(expectedTitle);
        createNewsPage.enterContent(expectedContent);

        PreviewNewsPage previewPage = createNewsPage.clickPreview();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(previewPage.getCreateNewsTitle(), "Create news", "Preview header text is incorrect");
        softAssert.assertEquals(previewPage.getTitleText(), expectedTitle, "Preview title does not match the input");
        softAssert.assertEquals(previewPage.getContentText(), expectedContent, "Preview content does not match the input");
        softAssert.assertEquals(previewPage.getAuthorText(), testValueProvider.getUserName(), "Author name in preview is incorrect");
        softAssert.assertTrue(previewPage.getPublicationDateText().contains(expectedDate),
                "Publication date in preview is incorrect: " + previewPage.getPublicationDateText());

        softAssert.assertTrue(previewPage.isBackToEditButtonVisible(), "'Back to editing' button is not visible in preview");
        softAssert.assertAll();
    }
}