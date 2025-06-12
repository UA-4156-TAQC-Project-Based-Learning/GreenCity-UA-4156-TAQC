package com.greencity.ui;

import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EcoNewsSectionDisplayedUnregisteredUserTest extends BaseTestRunner {
    @Issue("93")
    @Owner("Yuliia Terentieva")
    @Description("Verify that the Eco news section is displayed correctly for unregistered users on the main page.")
    @Feature("")
    @Test
    public void verifyEcoNewsSectionDisplayedUnregisteredUser(){
        driver.get(testValueProvider.getBaseUIUrl());
        homePage.scrollToElement(homePage.getEcoNewsSection());

//The "Eco news" title is centered on the page.
        int titleCenterX = homePage.getEcoNewsSectionTitle().getLocation().x + homePage.getEcoNewsSectionTitle().getSize().width / 2;
        int pageCenterX = driver.manage().window().getSize().getWidth() / 2;

        int tolerance = 10;

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(Math.abs(titleCenterX - pageCenterX) <= tolerance, "Title is not centered. Title center X: " + titleCenterX + ", Page center X: " + pageCenterX);

//The "Read all news" button is present and correctly placed below the title.
        softAssert.assertTrue(homePage.getReadNewsLink().isDisplayed());

        int titleY = homePage.getEcoNewsSectionTitle().getLocation().getY();
        int ecoNewsReadLinkY = homePage.getReadNewsLink().getLocation().getY();

        softAssert.assertTrue(ecoNewsReadLinkY > titleY, "Read all news link is not below the title.");

//Clicking the "Read all news" button opens a new page with a list of articles.
        softAssert.assertTrue(!homePage.clickReadNewsLink().getNewsComponents().isEmpty(), "News list should not be empty after clicking 'Read all news'");

//The articles are sorted in descending order by publication date (newest first).
        List<String> datesText = new EcoNewsPage(driver).getNewsDatesText();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDate> dates = datesText.stream().map(dateStr -> LocalDate.parse(dateStr, formatter)).toList();

        List<LocalDate> sortedDates = new ArrayList<>(dates);
        sortedDates.sort(Comparator.reverseOrder());

        softAssert.assertEquals(dates, sortedDates, "Articles are not sorted by date in descending order.");

        softAssert.assertAll();


    }
}
