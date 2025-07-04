package com.greencity.ui;

import com.greencity.jdbc.enums.TagType;
import com.greencity.jdbc.services.TagService;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
@Feature("Create News")
@Issue("15")
@Owner("Yuliia Terentieva")

public class CheckingTagSelectionTest extends TestRunnerWithUser {

    private TagService tagService;

    @BeforeClass
    public void init(){
        this.tagService = new TagService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
    }

    @Description("Verify that the user can select between 1 and 3 tags from the predefined list.")
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
                .getText()
                .trim();

        List<Long> ecoNewsTagIds = tagService.getTagsByType(TagType.ECO_NEWS)
                .stream()
                .map(tag -> tag.getId())
                .toList();

        System.out.println("DataBase contains tags with id (ECO_NEWS): " + ecoNewsTagIds);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(newsTag, NewsTags.NEWS_TAG.getEnglishName(), "Expected News tag is missed.");


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
                .flatMap(el -> Arrays.stream(el.getText().split("\\|")))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .toList();



        List<String> expected = Arrays.asList(
                NewsTags.NEWS_TAG.getEnglishName(),
                NewsTags.EVENTS_TAG.getEnglishName(),
                NewsTags.EDUCATION_TAG.getEnglishName()
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
                .flatMap(el -> Arrays.stream(el.getText().split("\\|")))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .toList();


        for (String uiTag : actualTags) {
            softAssert.assertTrue(
                    NewsTags.containsEnglishName(uiTag),
                    "Tag '" + uiTag + "' from UI was NOT found in known NewsTags enum (englishName)."
            );
        }

        softAssert.assertEquals(actualTags.size(), 3, "Expected 3 tags on the news card.");
        softAssert.assertAll();

    }
}
