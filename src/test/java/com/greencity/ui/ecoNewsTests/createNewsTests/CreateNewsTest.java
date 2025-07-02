package com.greencity.ui.ecoNewsTests.createNewsTests;

import com.greencity.jdbc.entity.EcoNewsEntity;
import com.greencity.jdbc.services.EcoNewsService;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CreateNewsTest extends TestRunnerWithUser {

    private EcoNewsService ecoNewsService;

    @BeforeClass
    private void init(){
        this.ecoNewsService = new EcoNewsService(
                testValueProvider.getJDBCGreenCityUsername(),
                testValueProvider.getJDBCGreenCityPassword(),
                testValueProvider.getJDBCGreenCityURL()
        );
    }

    @DataProvider(name = "newsData")
    public static Object[][] createNews() {
        return new Object[][]{
                {"title", NewsTags.NEWS_TAG, "https://www.greencity.cx", "bla bla bla bla bla bla bla"}
        };
    }

    @Feature("Create news")
    @Owner("kokun.v")
    @Description("Verifies that a user can create an Eco News article via UI and that the data is correctly saved in the database.")
    @Test(dataProvider = "newsData")
    public void createNews(String title, NewsTags tag, String source, String content) {

        homePage.getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .enterTitle(title)
                .clickTag(tag)
                .enterSource(source)
                .enterContent(content)
                .clickPublish();

        List<EcoNewsEntity> ecoNewsEntities = ecoNewsService.getNewsByUserId(testValueProvider.getLocalStorageUserId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(ecoNewsEntities.getLast().getTitle(), title);
        softAssert.assertEquals(ecoNewsEntities.getLast().getSource(), source);
        softAssert.assertEquals(ecoNewsEntities.getLast().getText().replaceAll("<[^>]*>",""), content);
        softAssert.assertAll();

    }
}


