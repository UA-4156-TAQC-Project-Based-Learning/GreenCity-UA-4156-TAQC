package com.greencity.ui.testrunners;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.greencity.ui.pages.CreateEditNewsPage.generateText;

public class PreFilledEditFormTest extends TestRunnerWithUser {

    @Test
    public void verifyPreFilledEditForm() {

        String title = generateText(25);
        String source="http://www.validSource.com";
        String content = generateText(25);

        CreateEditNewsPage createEditNewsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .createNews(title, source, NewsTags.EDUCATION_TAG, content)
                .findNewsByTitleAndClick(title)
                .clickEditNewsButton();

        String actualTitle=createEditNewsPage.getTitleInput().getText();
        String actualSource=createEditNewsPage.getSourceInput().getText();
        String actualContent=createEditNewsPage.getContentInput().getText();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(actualTitle.equals(title), "Expected title is:"+title+", actual title is: "+actualTitle);
        softAssert.assertTrue(actualSource.equals(source), "Expected source is:"+source+", actual title is: "+actualSource);
        softAssert.assertTrue(actualContent.equals(content), "Expected content is:"+content+", actual title is: "+actualContent);
        softAssert.assertAll();
        System.out.println(5);


    }
}
