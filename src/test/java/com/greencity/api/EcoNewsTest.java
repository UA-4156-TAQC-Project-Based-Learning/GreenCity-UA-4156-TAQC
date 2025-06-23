package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.econews.RequestEcoNews;
import com.greencity.api.models.econews.RequestUpdateNews;
import com.greencity.api.models.econews.ResponseEcoNews;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class EcoNewsTest extends AuthorizedApiTestRunner {

    private EcoNewsClient ecoNewsClient;


    @BeforeClass
    public void setUpTest() {
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIGreencityUrl());
        ecoNewsClient.setToken(token);
    }

    @Test
    public void createNews() {
        RequestEcoNews requestEcoNews = new RequestEcoNews();
        requestEcoNews.setTitle("news");
        requestEcoNews.setText("bla bla bla bla bla bla");
        requestEcoNews.setTags(List.of("News"));
        requestEcoNews.setSource("https://greencity.greencity.cx.ua/eco-news");
        requestEcoNews.setShortInfo("string");

        Response response = ecoNewsClient.createNews(requestEcoNews, "src/test/resources/imagesForTests/5mb.png");

        response.then().statusCode(201);

        ResponseEcoNews responseEcoNews = response.as(ResponseEcoNews.class);
        Long newsId = responseEcoNews.getId();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(responseEcoNews.getId(), "Id should not be null");
        softAssert.assertEquals(requestEcoNews.getTitle(), responseEcoNews.getTitle(), "Title should be the same");
        softAssert.assertEquals(requestEcoNews.getText(), responseEcoNews.getContent(), "Text should be the same");
        softAssert.assertEquals(requestEcoNews.getTags(), responseEcoNews.getTagsEn(), "Tags should be the same");
        softAssert.assertEquals(requestEcoNews.getSource(), responseEcoNews.getSource(), "Source should be the same");
        softAssert.assertEquals(requestEcoNews.getShortInfo(), responseEcoNews.getShortInfo(), "ShortInfo should be the same");
        softAssert.assertNotNull(responseEcoNews.getImagePath(), "Image path should not be null");
        softAssert.assertAll();

        ecoNewsClient.deleteNews(newsId).then().statusCode(200);
    }

    @Test
    public void updateNews() {
        RequestEcoNews request = createDefaultNews();
        Response createResponse = ecoNewsClient.createNews(request, null);
        Long newsId = createResponse.as(ResponseEcoNews.class).getId();

        RequestUpdateNews requestUpdate = new RequestUpdateNews();
        requestUpdate.setId(newsId);
        requestUpdate.setTitle("string");
        requestUpdate.setContent("update text for test bla bla ");
        requestUpdate.setShortInfo("short info");
        requestUpdate.setTags(List.of("News"));
        requestUpdate.setSource("https://greencity.greencity.cx.ua/eco-news");

        Response response = ecoNewsClient.updateNews(newsId, requestUpdate, null);
        response.then().statusCode(200);

        ResponseEcoNews responseUpdate = response.as(ResponseEcoNews.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(responseUpdate.getId(), newsId, "ID should match");
        softAssert.assertEquals(responseUpdate.getTitle(), requestUpdate.getTitle(), "Title should be updated");
        softAssert.assertEquals(responseUpdate.getContent(), requestUpdate.getContent(), "Content should be updated");
        softAssert.assertEquals(responseUpdate.getShortInfo(), requestUpdate.getShortInfo(), "Short info should be updated");
        softAssert.assertEquals(responseUpdate.getSource(), requestUpdate.getSource(), "Source should be updated");
        softAssert.assertAll();

        ecoNewsClient.deleteNews(newsId).then().statusCode(200);
    }

    @Test
    public void getNewsById() {
        RequestEcoNews request = createDefaultNews();
        Response responseCreated = ecoNewsClient.createNews(request, null);
        responseCreated.then().statusCode(201);

        Long newsId = responseCreated.as(ResponseEcoNews.class).getId();

        Response getResponse = ecoNewsClient.getNewsById(newsId);
        getResponse.then().statusCode(200);

        ResponseEcoNews ecoNews = getResponse.as(ResponseEcoNews.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(ecoNews.getId(), newsId, " ID should be the same.");
        softAssert.assertEquals(ecoNews.getTitle(), request.getTitle(), "Title should be the same.");
        softAssert.assertAll();

        ecoNewsClient.deleteNews(newsId).then().statusCode(200);
    }

    @Test
    public void deleteNewsById() {
        RequestEcoNews request = createDefaultNews();
        Response response = ecoNewsClient.createNews(request, null);
        response.then().statusCode(201);

        Long newsId = response.as(ResponseEcoNews.class).getId();
        Response deleteResponse = ecoNewsClient.deleteNews(newsId);
        deleteResponse.then().statusCode(200);

        Response getResponse = ecoNewsClient.getNewsById(newsId);
        getResponse.then().statusCode(404);

    }


    private RequestEcoNews createDefaultNews() {
        RequestEcoNews request = new RequestEcoNews();
        request.setTitle("default title");
        request.setText("Default text for tes");
        request.setTags(List.of("News"));
        request.setSource("https://example.com");
        request.setShortInfo("Some short info");
        return request;
    }
}
