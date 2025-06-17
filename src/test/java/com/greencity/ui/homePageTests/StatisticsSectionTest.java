package com.greencity.ui.homePageTests;

import com.greencity.ui.components.homePageComponents.statsSection.StatsSectionComponent;
import com.greencity.ui.components.homePageComponents.statsSection.statsSubsections.StatsSectionCupsComponent;
import com.greencity.ui.components.homePageComponents.statsSection.statsSubsections.StatsSectionEcoBagsComponent;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Feature("Statistics Section on Home Page")
@Owner("Prykhodchenko Oleksandra")
public class StatisticsSectionTest extends BaseTestRunner {

   private StatsSectionComponent statsSectionComponent;

    @BeforeMethod
    public void scrollToStatisticsSection() {
        statsSectionComponent = homePage.goToStatisticsSection();
    }

    @Test
    @Description("Verify that the Statistics section title is visible and correct")
    @Issue("109")
    public void testStatisticsSectionTitleVisible() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(statsSectionComponent.getStatisticsTitle().isDisplayed(), "Statistics title is not displayed");
        softAssert.assertTrue(statsSectionComponent.getStatisticsTitle().getText().contains("There are"),
                "Statistics title text is incorrect or missing");
        softAssert.assertAll();
    }

    @Test
    @Description("Verify content and elements in the Eco Bag subsection of Statistics")
    @Issue("109")
    public void testEcoBagStatisticsContent() {
        SoftAssert softAssert = new SoftAssert();
        StatsSectionEcoBagsComponent statsSectionEcoBagsComponent = statsSectionComponent
                .goToEcoBagsComponent();

        softAssert.assertTrue(statsSectionEcoBagsComponent
                .getImage()
                .isDisplayed(), "Eco Bag image is not displayed");

        softAssert.assertTrue(statsSectionEcoBagsComponent
                .getImage()
                .getAttribute("src")
                .contains("assets/img/habit-pic-bag.png"), "Wrong image src");

        softAssert.assertTrue(statsSectionEcoBagsComponent
                .getCountText().isDisplayed(), "Eco Bag count text not displayed");

        softAssert.assertTrue(statsSectionEcoBagsComponent
                        .getCountText().getText().contains("Did not take"),
                "Eco Bag count text does not contain expected text");

        softAssert.assertEquals(statsSectionEcoBagsComponent
                        .getQuestion().getText(),
                "And how many packages did you not take today?", "Eco Bag question text mismatch");

        softAssert.assertTrue(statsSectionEcoBagsComponent
                .getButton().isDisplayed(), "Eco Bag button not displayed");

        softAssert.assertEquals(statsSectionEcoBagsComponent
                        .getButton().getText(),
                "Start forming a habit!", "Eco Bag button label incorrect");

        softAssert.assertTrue(statsSectionEcoBagsComponent
                .getLink().isDisplayed(), "Eco Bag link not displayed");
        softAssert.assertEquals(statsSectionEcoBagsComponent
                        .getLink().getText(),
                "you can buy eco-bags here", "Eco Bag link text mismatch");

        softAssert.assertAll();
    }

    @Test
    @Description("Verify content and elements in the Cups subsection of Statistics")
    @Issue("109")
    public void testCupsStatisticsContent() {
        SoftAssert softAssert = new SoftAssert();
        StatsSectionCupsComponent statsSectionCupsComponent = statsSectionComponent
                .goToCupsComponent();

        softAssert.assertTrue(statsSectionCupsComponent
                .getImage()
                .isDisplayed(), "Cups image is not displayed");

        softAssert.assertTrue(statsSectionCupsComponent
                .getImage()
                .getAttribute("src")
                .contains("assets/img/habit-pic-cup.png"), "Wrong image src");

        softAssert.assertTrue(statsSectionCupsComponent
                .getCountText()
                .isDisplayed(), "Cups count text not displayed");

        softAssert.assertTrue(statsSectionCupsComponent
                        .getCountText()
                        .getText()
                        .contains("Did not throw away"),
                "Cups count text does not contain expected text");

        softAssert.assertEquals(statsSectionCupsComponent
                        .getQuestion()
                        .getText(),
                "And how many cups did you not throw away today?", "Cups question text mismatch");

        softAssert.assertTrue(statsSectionCupsComponent
                .getButton()
                .isDisplayed(), "Cups button not displayed");

        softAssert.assertEquals(statsSectionCupsComponent
                        .getButton()
                        .getText(),
                "Start forming a habit!", "Cups button label incorrect");

        softAssert.assertTrue(statsSectionCupsComponent
                .getLink()
                .isDisplayed(), "Cups link not displayed");

        softAssert.assertEquals(statsSectionCupsComponent
                        .getLink()
                        .getText(),
                "places that make a discount on a drink in your cup", "Cups link text mismatch");

        softAssert.assertAll();
    }
}

