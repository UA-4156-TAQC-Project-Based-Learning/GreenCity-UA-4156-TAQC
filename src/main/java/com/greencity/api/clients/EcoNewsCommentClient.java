package com.greencity.api.clients;


import com.greencity.api.models.ecoNewsComments.RequestAddComment;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class EcoNewsCommentClient extends AuthClient{

    protected String resourceUrl = "/eco-news";
    protected String resourceUrlComment = "/comments";

    public EcoNewsCommentClient(String baseUrl) {
        super(baseUrl);
    }

    @Step("Add new comment by eco news id")
    public Response addComment(RequestAddComment comment, Long ecoNewsId, List<String> imagePaths) {
        RequestSpecification requestSpecification = preparedRequest()
                .contentType(ContentType.MULTIPART)
                .multiPart("addEcoNewsCommentDtoRequest",comment,"application/json");
        attachImage(requestSpecification, imagePaths);

        return requestSpecification.post(String.format("%s/%d%s", resourceUrl, ecoNewsId, resourceUrlComment));
    }

    private void attachImage(RequestSpecification requestSpecification, List<String> imagePaths) {
        if (imagePaths == null || imagePaths.isEmpty()){
            requestSpecification.multiPart("image", "", "");
            return;
        }
        try {
            for (String imagePath : imagePaths) {
                File imageFile = new File(imagePath);
                String fileName = imageFile.getName().toLowerCase();
                String mimeType = fileName.endsWith(".png") ? "image/png" : fileName.endsWith(".gif")? "image/png": "image/jpeg";
                requestSpecification.multiPart("image", fileName, new FileInputStream(imageFile), mimeType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Step("Like or Unlike comment by id")
    public Response likeOrUnlike(Long commentId) {
        return preparedRequest()
                .queryParam("commentId", commentId)
                .post(String.format("%s%s/like", resourceUrl, resourceUrlComment));
    }

    @Step("Like/unlike comment and get an instance of a comment with updated data")
    public Response likeOrUnlikeV2(Long commentId) {
        return preparedRequest()
                .queryParam("commentId", commentId)
                .post(String.format("%s%s/likeV2", resourceUrl, resourceUrlComment));
    }

    @Step("Dislike comment")
    public Response dislike(Long commentId) {
        return preparedRequest()
                .queryParam("commentId", commentId)
                .post(String.format("%s%s/dislike", resourceUrl, resourceUrlComment));
    }

    @Step("Dislike comment and get an instance of a comment with updated data")
    public Response dislikeV2(Long commentId) {
        return preparedRequest()
                .queryParam("commentId", commentId)
                .post(String.format("%s%s/dislikeV2", resourceUrl, resourceUrlComment));
    }

    @Step("Update comment")
    public Response updateComment(Long commentId, String newText) {
        return preparedRequest()
                .queryParam("commentId", commentId)
                .body(newText)
                .patch(String.format("%s%s", resourceUrl, resourceUrlComment));
    }

    @Step("Count comments")
    public Response getCommentsCountResponse(Long ecoNewsId) {
        return preparedRequest()
                .get(String.format("%s/%d%s/count", resourceUrl, ecoNewsId, resourceUrlComment));
    }

    @Step("Get all active comments (without replies)")
    public Response getActiveComments(Long ecoNewsId, Integer page, Integer size) {
        return preparedRequest()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(String.format("%s/%d%s/active", resourceUrl, ecoNewsId, resourceUrlComment));
    }

    @Step("Get all replies to comment")
    public Response getActiveReplies(Long parentCommentId, Integer page, Integer size, String sort) {
        return preparedRequest()
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sort", sort)
                .get(String.format("%s%s/%d/replies/active", resourceUrl, resourceUrlComment, parentCommentId));
    }

    @Step("Count replies for comment")
    public Response getActiveRepliesCount(Long parentCommentId) {
        return preparedRequest()
                .get(String.format("%s%s/%d/replies/active/count", resourceUrl, resourceUrlComment, parentCommentId));
    }

    @Step("Get comment by id")
    public Response getCommentById(Long commentId) {
        return preparedRequest()
                .get(String.format("%s%s/%d", resourceUrl, resourceUrlComment, commentId));
    }

    @Step("Mark comment as deleted.")
    public Response deleteComment(Long commentId) {
        return preparedRequest()
                .delete(String.format("%s%s/%d", resourceUrl, resourceUrlComment, commentId));
    }
}
