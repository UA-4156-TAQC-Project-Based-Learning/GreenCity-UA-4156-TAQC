package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.econews.RequestEcoNews;
import com.greencity.api.models.econews.ResponseEcoNews;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        requestEcoNews.setSource(" ");
        requestEcoNews.setShortInfo("string");

        Response response = ecoNewsClient.createNews(requestEcoNews);

        response.then().statusCode(201);

        ResponseEcoNews responseEcoNews = response.as(ResponseEcoNews.class);
    }

}
