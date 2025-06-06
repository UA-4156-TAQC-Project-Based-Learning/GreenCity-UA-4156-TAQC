package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CheckingTagSelectionTest extends TestRunnerWithUser {
    @Test
    public void checkingNewsTagSelection() {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");

        EcoNewsPage ecoNewsPage = new EcoNewsPage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .clickTag(NewsTags.NEWS_TAG)
                .enterTitle("Test")
                .enterContent("Test content with 20 chars")
                .clickPublish();

        List<String> actualTags = ecoNewsPage.getNewsComponents().getFirst().getTagTexts();

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(actualTags.size(), 1, "Expected 2 tags on the news card.");
        softAssert.assertTrue(actualTags.contains("НОВИНИ"), "Expected tags are missing.");

        EcoNewsPage ecoNewsPage1 = new EcoNewsPage(driver)
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .clickTag(NewsTags.EVENTS_TAG)
                .clickTag(NewsTags.EDUCATION_TAG)
                .clickPublish();

        List<String> actualTags3 = ecoNewsPage1.getNewsComponents().getFirst().getTagTexts();
        softAssert.assertEquals(actualTags3.size(), 3, "Expected 3 tags on the news card.");
        softAssert.assertTrue(actualTags3.containsAll(List.of("НОВИНИ", "ПОДІЇ", "ОСВІТА")), "Expected tags are missing.");

        EcoNewsPage ecoNewsPage2 = new EcoNewsPage(driver)
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .clickTag(NewsTags.ADS_TAG)
                .clickPublish();

        List<String> actualTags4 = ecoNewsPage2.getNewsComponents().getFirst().getTagTexts();
        softAssert.assertEquals(actualTags4.size(), 3, "Expected 3 tags on the news card.");

    }
}
