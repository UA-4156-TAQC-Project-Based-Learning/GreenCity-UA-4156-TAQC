package com.greencity.ui.testrunners;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.elements.NewsTags;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.econewspage.EcoNewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static com.greencity.ui.pages.CreateEditNewsPage.generateText;

public class PreFilledEditFormTest extends TestRunnerWithUser{

    @Test
    public void verifyPreFilledEditForm(){

        String title = generateText(25)+"888";
        String content = generateText(25);

        EcoNewsPage ecoNewsPage= new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickCreateNewsButton()
                .createNews(title, NewsTags.EDUCATION_TAG, content);                ;

      //  ecoNewsPage.getNewsComponents().get(0).getTitle().click();

        List<NewsComponent> newsList=ecoNewsPage.getNewsComponents();
        for (NewsComponent news:newsList){
            System.out.println(news.getTitle().getText());
            System.out.println(news.getTitle().getText().trim()==title);
            if( news.getTitle().getText().trim()==title){
                news.getTitle().click();
                break;
            }
        }

      //  String actualTitleOfFirstNews=ecoNewsPage.getNewsComponents().get(0).getTitle().getText()


        System.out.println(5);

//        String actualTitleOfFirstNews=ecoNewsPage.getNewsComponents().get(0).getTitle().getText();
//
//        SoftAssert softAssert = new SoftAssert();
//        softAssert.assertTrue(driver.getCurrentUrl().contains("/news"), "EcoNews Page is not opened");
//
//        softAssert.assertEquals(actualTitleOfFirstNews, title);
//        softAssert.assertAll();


    }
}
