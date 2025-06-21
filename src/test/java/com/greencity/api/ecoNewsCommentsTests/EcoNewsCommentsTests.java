package com.greencity.api.ecoNewsCommentsTests;

import com.greencity.api.clients.EcoNewsCommentClient;
import com.greencity.api.models.ecoNewsComments.RequestAddComment;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.qameta.allure.Owner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class EcoNewsCommentsTests extends AuthorizedApiTestRunner {

    private EcoNewsCommentClient client;
    private final Long validEcoNewsId = 1L;
    private final Long invalidEcoNewsId = 999999L;

    private final Long validCommentId = 82L;
    private final Long invalidCommentId = 999999L;

    @BeforeClass
    public void setup() {
        client = new EcoNewsCommentClient(testValueProvider.getBaseAPIGreencityUrl());
        client.setToken(token);
    }

    @DataProvider(name = "commentsProvider")
    public Object[][] commentsProvider() {
        return new Object[][]{
                {
                        new RequestAddComment("Test comment without images", 0L),
                        List.of()
                },
                {
                        new RequestAddComment("Test comment with one image", 0L),
                        List.of("src/test/resources/imagesForTests/5mb.png")
                },
                {
                        new RequestAddComment("Test comment with multiple images", 0L),
                        List.of(
                                "src/test/resources/imagesForTests/5mb.png",
                                "src/test/resources/imagesForTests/5mb.png"
                        )
                }
        };
    }


    @Test(description = "Add new comment - with and without images", dataProvider = "commentsProvider")
    @Owner("Prykhodchenko Oleksandra")
    public void addComment_WithDataProvider_ShouldReturn201or200(RequestAddComment comment, List<String> imagePaths) {
        Response response = client.addComment(comment, validEcoNewsId, imagePaths);
        Assert.assertTrue(response.statusCode() == 200 || response.statusCode() == 201,
                "Expected 200 or 201, but got " + response.statusCode());
    }

    @Test(description = "Add comment with invalid ecoNewsId - Negative")
    @Owner("Prykhodchenko Oleksandra")
    public void addComment_InvalidEcoNewsId_ShouldReturn404() {
        RequestAddComment comment = new RequestAddComment();
        comment.setText("Test comment");
        Response response = client.addComment(comment, invalidEcoNewsId, null);

        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Like or Unlike comment - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void likeOrUnlike_ValidComment_ShouldReturn200() {
        Response response = client.likeOrUnlike(validCommentId);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Like or Unlike comment - Invalid Comment Id")
    @Owner("Prykhodchenko Oleksandra")
    public void likeOrUnlike_InvalidComment_ShouldReturn404() {
        Response response = client.likeOrUnlike(invalidCommentId);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Dislike comment - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void dislike_ValidComment_ShouldReturn200() {
        Response response = client.dislike(validCommentId);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Dislike comment - Invalid Comment Id")
    @Owner("Prykhodchenko Oleksandra")
    public void dislike_InvalidComment_ShouldReturn404() {
        Response response = client.dislike(invalidCommentId);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Update comment - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void updateComment_ValidId_ShouldReturn200() {
        String newText = "Updated comment text";
        Response response = client.updateComment(validCommentId, newText);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Update comment - Invalid Id")
    @Owner("Prykhodchenko Oleksandra")
    public void updateComment_InvalidId_ShouldReturn404() {
        String newText = "Updated comment text";
        Response response = client.updateComment(invalidCommentId, newText);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Get comments count - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void getCommentsCount_ValidEcoNewsId_ShouldReturn200() {
        Response response = client.getCommentsCountResponse(validEcoNewsId);
        Assert.assertEquals(response.statusCode(), 200);
        int count = response.as(Integer.class);
        Assert.assertTrue(count >= 0);
    }

    @Test(description = "Get comments count - Invalid EcoNewsId")
    @Owner("Prykhodchenko Oleksandra")
    public void getCommentsCount_InvalidEcoNewsId_ShouldReturn404() {
        Response response = client.getCommentsCountResponse(invalidEcoNewsId);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Get active comments - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void getActiveComments_ValidEcoNewsId_ShouldReturn200() {
        Response response = client.getActiveComments(validEcoNewsId, 0, 5);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("page"));
    }

    @Test(description = "Get active comments - Invalid EcoNewsId")
    @Owner("Prykhodchenko Oleksandra")
    public void getActiveComments_InvalidEcoNewsId_ShouldReturn404() {
        Response response = client.getActiveComments(invalidEcoNewsId, 0, 5);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Get active replies - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void getActiveReplies_ValidParentCommentId_ShouldReturn200() {
        Response response = client.getActiveReplies(validCommentId, 0, 5, "createdDate,asc");
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("page"));
    }

    @Test(description = "Get active replies - Invalid ParentCommentId")
    @Owner("Prykhodchenko Oleksandra")
    public void getActiveReplies_InvalidParentCommentId_ShouldReturn404() {
        Response response = client.getActiveReplies(invalidCommentId, 0, 5, "createdDate,asc");
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Get active replies count - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void getActiveRepliesCount_ValidParentCommentId_ShouldReturn200() {
        Response response = client.getActiveRepliesCount(validCommentId);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Get active replies count - Invalid ParentCommentId")
    @Owner("Prykhodchenko Oleksandra")
    public void getActiveRepliesCount_InvalidParentCommentId_ShouldReturn404() {
        Response response = client.getActiveRepliesCount(invalidCommentId);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Get comment by id - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void getCommentById_ValidId_ShouldReturn200() {
        Response response = client.getCommentById(validCommentId);
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.getBody().asString().contains("id"));
    }

    @Test(description = "Get comment by id - Invalid Id")
    @Owner("Prykhodchenko Oleksandra")
    public void getCommentById_InvalidId_ShouldReturn404() {
        Response response = client.getCommentById(invalidCommentId);
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test(description = "Delete comment - Positive")
    @Owner("Prykhodchenko Oleksandra")
    public void deleteComment_ValidId_ShouldReturn200() {
        Response response = client.deleteComment(validCommentId);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Delete comment - Invalid Id")
    @Owner("Prykhodchenko Oleksandra")
    public void deleteComment_InvalidId_ShouldReturn404() {
        Response response = client.deleteComment(invalidCommentId);
        Assert.assertEquals(response.statusCode(), 404);
    }
}
