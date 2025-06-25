package com.greencity.api;

import com.greencity.api.clients.EcoNewsClient;
import com.greencity.api.models.econews.RequestEcoNews;
import com.greencity.api.models.econews.RequestUpdateNews;
import com.greencity.api.models.econews.ResponseEcoNews;
import com.greencity.api.models.econews.ResponseEcoNewsList;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

@Epic("EcoNews API")
public class EcoNewsTest extends AuthorizedApiTestRunner {

    private EcoNewsClient ecoNewsClient;


    @BeforeClass
    public void setUpTest() {
        ecoNewsClient = new EcoNewsClient(testValueProvider.getBaseAPIGreencityUrl());
        ecoNewsClient.setSignIn(signIn);
    }

    @DataProvider(name = "getAllNews")
    public static Object[][] getAllNewsFilter() {
        return new Object[][]{
                {null, null, signIn.getUserId(), false, null, 10, null}
        };
    }

    @Test
    @Owner("kokun.v")
    @Feature("Create News")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that authorized user can create eco-news with image and get expected response data.")
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
    @Owner("kokun.v")
    @Feature("Update News")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that user can update previously created news and receive updated data.")
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
    @Owner("kokun.v")
    @Feature("Get News")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verifies that user can fetch eco-news by ID and the content is correct.")
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
    @Owner("kokun.v")
    @Feature("Delete News")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verifies that user can delete eco-news by ID and it will no longer be accessible.")
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

    @Test(dataProvider = "getAllNews")
    @Owner("kokun.v")
    @Feature("Get news")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Should create news and verify it appears in getAllNews response.")
    public void getAllNews(String tags, String title, Long idUser, boolean favorite, Integer page, Integer size, String sort) {
        RequestEcoNews request = createDefaultNews();
        Response response = ecoNewsClient.createNews(request, null);
        Long newsId = response.as(ResponseEcoNews.class).getId();

        Response getAll = ecoNewsClient.getAllNews(tags, title, idUser, favorite, page, size, sort);
        getAll.then().statusCode(200);

        ResponseEcoNewsList allEcoNews = getAll.as(ResponseEcoNewsList.class);
        boolean foundNews = allEcoNews.getPage()
                .stream()
                .anyMatch(news -> news.getId().equals(newsId));
        Assert.assertTrue(foundNews, "Created news should appear in getAllNews response.");

        ecoNewsClient.deleteNews(newsId).then().statusCode(200);
    }

    @Test
    @Owner("kokun.v")
    @Feature("Add favorite")
    @Severity(SeverityLevel.MINOR)
    @Description("Create news and add to favorites after remove from favorites.")
    public void addToFavoritesAndRemove() {
        RequestEcoNews request = createDefaultNews();
        Response response = ecoNewsClient.createNews(request, null);
        Long newsId = response.as(ResponseEcoNews.class).getId();

        Response addToFav = ecoNewsClient.addToFavorites(newsId);
        addToFav.then().statusCode(200);

        Response remove = ecoNewsClient.removeFromFavorites(newsId);
        remove.then().statusCode(200);

        ecoNewsClient.deleteNews(newsId).then().statusCode(200);

    }

    @Test
    @Owner("kokun.v")
    @Feature("Like news")
    @Severity(SeverityLevel.MINOR)
    @Description("Test to like and unlike news.")
    public void likeAndUnlike() {
        Long idNews = 60L;

        Response response = ecoNewsClient.likeUnlikeNews(idNews);
        response.then().statusCode(200);

        Response checkLike = ecoNewsClient.getUserLikeNews(idNews, signIn.getUserId());
        checkLike.then().statusCode(200);
        checkLike.then().body(equalTo("true"));


        Response unlike = ecoNewsClient.likeUnlikeNews(idNews);
        unlike.then().statusCode(200);

        Response checkLike2 = ecoNewsClient.getUserLikeNews(idNews, signIn.getUserId());
        checkLike2.then().statusCode(200);
        checkLike2.then().body(equalTo("false"));

    }

    @Test
    @Owner("kokun.v")
    @Feature("Get news")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get content and source in eco news by id, and check that they're the same.")
    public void getSummary() {
        RequestEcoNews request = createDefaultNews();
        Response response = ecoNewsClient.createNews(request, null);
        Long newsId = response.as(ResponseEcoNews.class).getId();

        Response contentResponse = ecoNewsClient.getContentAndSource(newsId);
        contentResponse.then().statusCode(200);

        ResponseEcoNews summary = contentResponse.as(ResponseEcoNews.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(summary.getContent(), request.getText(), "The content must be the same.");
        softAssert.assertEquals(summary.getSource(), request.getSource(), "The source must be the same.");
        softAssert.assertAll();

        ecoNewsClient.deleteNews(newsId).then().statusCode(200);
    }

    @Test
    @Owner("kokun.v")
    @Feature("Create news")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Verifies that news can't be created without a title.")
    public void createNewsWithoutTitle() {
        RequestEcoNews request = new RequestEcoNews();
        request.setTitle("");
        request.setText("Default text for tes");
        request.setTags(List.of("News"));
        request.setSource("https://example.com");
        request.setShortInfo("Some short info");
        Response response = ecoNewsClient.createNews(request, null);
        response.then().statusCode(400);

        List<Map<String, String>> errors = response.jsonPath().getList("");
        boolean titleError = errors.stream()
                .anyMatch(error -> error.get("name").equals("title") &&
                        (error.get("message").contains("must not be empty") ||
                                error.get("message").contains("size must be between 1 and 170")));
        Assert.assertTrue(titleError, "Expected validation error for empty title not found");
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
