package com.greencity.api.clients;
import com.greencity.api.models.econews.RequestEcoNews;
import com.greencity.api.models.econews.RequestUpdateNews;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EcoNewsClient extends BaseClient {

    protected String recourseUrl = "/eco-news";

    public EcoNewsClient(String baseUrl) {
        super(baseUrl);
    }

    @Step("Create news with image: {imagePath}")
    public Response createNews(RequestEcoNews request, String imagePath) {
        RequestSpecification requestSpecification = preparedRequest()
                .contentType(ContentType.MULTIPART)
                .multiPart("addEcoNewsDtoRequest", request, "application/json");
        attachImage(requestSpecification, imagePath);
        return requestSpecification.post(recourseUrl);

    }

    @Step("Update news ID {newsId} with image: {imagePath}")
    public Response updateNews(Long newsId, RequestUpdateNews request, String imagePath) {
        RequestSpecification requestSpecification = preparedRequest()
                .contentType(ContentType.MULTIPART)
                .multiPart("updateEcoNewsDto", request, "application/json");
        attachImage(requestSpecification, imagePath);
        return requestSpecification.log().all().put(recourseUrl + "/" + newsId);
    }

    @Step("Get news by ID: {newsId}")
    public Response getNewsById(Long newsId) {
        return preparedRequest()
                .get(recourseUrl + "/" + newsId);
    }

    @Step("Delete news by ID: {newsId}")
    public Response deleteNews(Long newsId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + newsId);
    }

    @Step("Like or Unlike news by ID: {newsId}")
    public Response likeUnlikeNews(Long newsId) {
        return preparedRequest()
                .post(recourseUrl + "/" + newsId + "/likes");
    }

    @Step("Add news ID {idNews} to favorites")
    public Response addToFavorites(Long idNews) {
        return preparedRequest()
                .post(recourseUrl + "/" + idNews + "/favorites");
    }

    @Step("Remove news ID {idNews} from favorites")
    public Response removeFromFavorites(Long idNews) {
        return preparedRequest()
                .delete(recourseUrl + "/" + idNews + "/favorites");
    }

    @Step("Get source and content for news ID {idNews}")
    public Response getContentAndSource(Long idNews) {
        return preparedRequest()
                .get(recourseUrl + "/" + idNews + "/summary");
    }

    @Step("Get recommended news for the news ID {idNews}")
    public Response getRecommended(Long idNews) {
        return preparedRequest()
                .get(recourseUrl + "/" + idNews + "/recommended");
    }

    @Step("Get user like status for news ID: {idNews} and user ID: {idUser}")
    public Response getUserLikeNews(Long idNews, Long idUser) {
        return preparedRequest()
                .get(recourseUrl + "/" + idNews + "/likes/" + idUser);
    }

    @Step("Get like count for news ID: {idNews}")
    public Response getLikeCount(Long idNews) {
        return preparedRequest()
                .get(recourseUrl + "/" + idNews + "/likes/count");
    }

    @Step("Get news count for user ID: {idUser}")
    public Response getNewsCount(Long idUser) {
        return preparedRequest()
                .get(recourseUrl + "/count?author-id=" + idUser);
    }

    @Step("Get all news with filters: tags={tags}, title={title}, idUser={idUser}, favorite={favorite}, page={page}, size={size}, sort={sort}")
    public Response getAllNews(String tags, String title, Long idUser,
                               boolean favorite, Integer page, Integer size, String sort) {
        RequestSpecification request = preparedRequest();
        if (tags != null) request.queryParam("tags", tags);
        if (title != null) request.queryParam("title", title);
        if (idUser != null) request.queryParam("author-id", idUser);
        request.queryParam("favorite", favorite);
        if (page != null) request.queryParam("page", page);
        if (size != null) request.queryParam("size", size);
        if (sort != null) request.queryParam("sort", sort);

        return request.get(recourseUrl);
    }

    private void attachImage(RequestSpecification requestSpecification, String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) {
            requestSpecification.multiPart("image", "", "");
            return;
        }
        try {
            File imageFile = new File(imagePath);
            String fileName = imageFile.getName().toLowerCase();
            String mimeType = fileName.endsWith(".png") ? "image/png" : fileName.endsWith(".gif") ? "image/png" : "image/jpeg";
            requestSpecification.multiPart("image", fileName, new FileInputStream(imageFile), mimeType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
