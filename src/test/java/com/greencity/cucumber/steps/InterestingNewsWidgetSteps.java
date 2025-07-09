package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class InterestingNewsWidgetSteps {

    private final Hooks hooks;
    private final SoftAssert softAssert;

    private EcoNewsPage ecoNewsPage;
    private NewsPage newsPage;
    private List<NewsComponent> allNewsComponents;
    private List<NewsComponent> recommendedNews;
    private String openedNewsTitle;
    private String loggedInUserName;

    public InterestingNewsWidgetSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
    }

    @Given("the user has successfully logged in to view interesting news")
    public void theUserHasSuccessfullyLoggedIn() {
        hooks.loginViaLocalStorage();
        loggedInUserName = hooks.getTestValueProvider().getLocalStorageName();
        softAssert.assertNotNull(loggedInUserName, "Logged in user name should not be null after login.");
        softAssert.assertFalse(loggedInUserName.isEmpty(), "Logged in user name should not be empty after login.");
    }

    @Given("the user is on the Eco News page with no active tag filter")
    public void userIsOnEcoNewsPageWithNoFilter() {
        ecoNewsPage = new HomePage(hooks.getDriver())
                .getHeader()
                .goToEcoNews();

        boolean filterActive = ecoNewsPage.isAnyTagFilterActive();
        softAssert.assertFalse(filterActive, "No tag filter should be active.");

        allNewsComponents = ecoNewsPage.getNewsComponents();
        softAssert.assertTrue(allNewsComponents.size() >= 4,
                "There should be at least 4 news items to run this test.");

        if (allNewsComponents.size() < 4) {
            throw new AssertionError("Not enough news items to run the test. Found: " + allNewsComponents.size());
        }
    }

    @Given("no tag filter is selected")
    public void noTagFilterIsSelected() {
        boolean filterActive = ecoNewsPage.isAnyTagFilterActive();
        softAssert.assertFalse(filterActive, "No tag filter should be active.");
    }

    @Given("at least 4 news items are available")
    public void atLeast4NewsItemsAvailable() {
        softAssert.assertTrue(allNewsComponents.size() >= 4,
                "There should be at least 4 news items to run this test.");
        if (allNewsComponents.size() < 4) {
            throw new AssertionError("Not enough news items to run the test. Found: " + allNewsComponents.size());
        }
    }

    @When("the user opens the first news article")
    public void userOpensFirstNewsArticle() {
        openedNewsTitle = allNewsComponents.get(0).getTitleText();
        allNewsComponents.get(0).click();

        newsPage = new NewsPage(hooks.getDriver());
        boolean interestingVisible = newsPage.isInterestingSectionVisible();
        softAssert.assertTrue(interestingVisible, "Interesting news section is not visible.");
    }

    @Then("the recommended news widget displays exactly 3 most recent news excluding the opened one")
    public void recommendedNewsWidgetDisplays3MostRecent() {
        newsPage.scrollToElement(newsPage.getInterestingForYouTitle());

        recommendedNews = newsPage.getRecommendedNewsCards().stream()
                .map(card -> new NewsComponent(hooks.getDriver(), card))
                .toList();

        softAssert.assertEquals(recommendedNews.size(), 3,
                "Widget should display exactly 3 recommended news.");

        List<String> recommendedTitles = recommendedNews.stream()
                .map(NewsComponent::getTitleText)
                .toList();

        softAssert.assertFalse(recommendedTitles.contains(openedNewsTitle),
                "Currently opened news should not be in the recommended list.");

        List<LocalDate> recommendedDates = recommendedNews.stream()
                .map(NewsComponent::getPublicationDate)
                .toList();

        List<LocalDate> sortedDates = recommendedDates.stream()
                .sorted()
                .toList();

        softAssert.assertEquals(recommendedDates, sortedDates,
                "Recommended news should be sorted by oldest date first.");

        softAssert.assertAll();
    }
}