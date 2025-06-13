package com.greencity.ui;

import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class CheckingTagSelectionTest extends TestRunnerWithUser {
    @Issue("15")
    @Owner("Yuliia Terentieva")
    @Description("Verify that the user can select between 1 and 3 tags from the predefined list.")
    @Feature("Create News")
    @Test
    public void checkingNewsTagSelection() {
        driver.get(testValueProvider.getBaseUIUrl() + "/profile");

        String newsTag = new EcoNewsPage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .clickTag(NewsTags.NEWS_TAG)
                .enterTitle("Test")
                .enterContent("Test content with 20 chars")
                .clickPublish()
                .getNewsComponents()
                .getFirst()
                .getTags()
                .getFirst()
                .getText();


        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(newsTag, NewsTags.NEWS_TAG, "Expected News tag is missed.");


        List<String> updatedListOfTags3 = new EcoNewsPage(driver)
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .clickTag(NewsTags.EVENTS_TAG)
                .clickTag(NewsTags.EDUCATION_TAG)
                .clickPublish()
                .getNewsComponents()
                .getFirst()
                .getTags()
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        List<String> expected = Arrays.asList(
                NewsTags.NEWS_TAG.getUkrainianName(),
                NewsTags.EVENTS_TAG.getUkrainianName(),
                NewsTags.EDUCATION_TAG.getUkrainianName()
        );

        softAssert.assertEquals(updatedListOfTags3.size(), expected.size(), "Expected 3 tags on the news card.");
        softAssert.assertTrue(updatedListOfTags3.containsAll(expected), "Expected tags are missing.");

        List<String> actualTags = new EcoNewsPage(driver)
                .clickFirstNewsPage()
                .clickEditNewsButton()
                .clickTag(NewsTags.ADS_TAG)
                .clickPublish()
                .getNewsComponents()
                .getFirst()
                .getTags()
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();

        softAssert.assertEquals(actualTags.size(), 3, "Expected 3 tags on the news card.");
        softAssert.assertAll();

    }
}
