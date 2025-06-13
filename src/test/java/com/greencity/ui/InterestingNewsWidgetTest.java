package com.greencity.ui;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class InterestingNewsWidgetTest extends TestRunnerWithUser {

    @Issue("96")
    @Feature("EcoNews")
    @Description("Verify that the recommended news widget shows exactly 3 most recent news regardless of any tag filters.")
    @Owner("Svitlana Kovalova")
    @Test
    public void verifyRecommendedNewsShows3MostRecentRegardlessOfTagWhenNoFilter() {
        SoftAssert softAssert = new SoftAssert();

        EcoNewsPage ecoNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews();

        softAssert.assertFalse(ecoNewsPage.isAnyTagFilterActive(), "No tag filter should be active.");


        List<NewsComponent> allNewsComponents = ecoNewsPage.getNewsComponents();
        softAssert.assertTrue(allNewsComponents.size() >= 4,
                "There should be at least 4 news items to run this test. Found: " + allNewsComponents.size());

        if (allNewsComponents.size() < 4) {
            throw new AssertionError("Not enough news items to run the test. Found: " + allNewsComponents.size() + ", required: 4");
        }

        List<String> allNewsTitles = allNewsComponents.stream()
                .map(NewsComponent::getTitleText)
                .toList();

        List<LocalDate> allNewsDates = allNewsComponents.stream()
                .map(NewsComponent::getPublicationDate)
                .toList();

        allNewsComponents.get(0).click();
        String openedTitle = allNewsTitles.get(0);

        NewsPage newsPage = new NewsPage(driver);
        softAssert.assertTrue(newsPage.isInterestingSectionVisible(), "Interesting news section is not visible.");

        newsPage.scrollToElement(newsPage.getInterestingForYouTitle());

        List<NewsComponent> recommended = newsPage.getRecommendedNewsCards().stream()
                .map(card -> new NewsComponent(driver, card))
                .toList();

        softAssert.assertEquals(recommended.size(), 3,
                "Widget should display exactly 3 recommended news. Found: " + recommended.size());

        List<String> recommendedTitles = recommended.stream()
                .map(NewsComponent::getTitleText)
                .toList();

        softAssert.assertFalse(recommendedTitles.contains(openedTitle),
                "Currently opened news should not be in the recommended list.");

        List<LocalDate> recommendedDates = recommended.stream()
                .map(NewsComponent::getPublicationDate)
                .toList();

        List<LocalDate> sortedDates = recommendedDates.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        softAssert.assertEquals(recommendedDates, sortedDates,
                "Recommended news should be sorted by newest date first.");

        softAssert.assertAll();
    }
}