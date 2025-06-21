package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.econews.RequestEcoNews;
import com.greencity.api.models.econews.ResponseEcoNews;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
public class EcoNewsTest extends ApiTestRunner {

    private EcoNewsClient ecoNewsClient;

    @BeforeClass
    public void setUpTest() {
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIGreencityUrl());
        ecoNewsClient.setToken(testValueProvider.getLocalStorageAccessToken());
    }

    @Test
    public void createNews() {
        RequestEcoNews requestEcoNews = new RequestEcoNews();
        requestEcoNews.setTitle("news");
        requestEcoNews.setText("bla bla bla bla bla bla");
        requestEcoNews.setTags(List.of("News"));
        requestEcoNews.setSource("https://greencity.greencity.cx.ua/eco-news");
        requestEcoNews.setShortInfo("string");

        Response response = ecoNewsClient.createNews(requestEcoNews,"src/test/resources/imagesForTests/5mb.png");

        response.then().statusCode(201);

        ResponseEcoNews responseEcoNews = response.as(ResponseEcoNews.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseEcoNews.getId(),"Id should not be null");
        softAssert.assertEquals(requestEcoNews.getTitle(), responseEcoNews.getTitle(), "Title should be the same");
        softAssert.assertEquals(requestEcoNews.getText(), responseEcoNews.getContent(), "Text should be the same");
        softAssert.assertEquals(requestEcoNews.getTags(), responseEcoNews.getTagsEn(), "Tags should be the same");
        softAssert.assertEquals(requestEcoNews.getSource(), responseEcoNews.getSource(), "Source should be the same");
        softAssert.assertEquals(requestEcoNews.getShortInfo(), responseEcoNews.getShortInfo(), "ShortInfo should be the same");
        softAssert.assertNotNull(responseEcoNews.getImagePath(), "Image path should not be null");
        softAssert.assertAll();
    }

}
