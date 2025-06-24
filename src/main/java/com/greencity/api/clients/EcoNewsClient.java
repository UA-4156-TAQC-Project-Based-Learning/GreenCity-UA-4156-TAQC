package com.greencity.api.clients;
import com.greencity.api.models.econews.RequestEcoNews;
import com.greencity.api.models.econews.RequestUpdateNews;
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

    public Response createNews(RequestEcoNews request, String imagePath) {
        RequestSpecification requestSpecification = preparedRequest()
                .contentType(ContentType.MULTIPART)
                .multiPart("addEcoNewsDtoRequest", request, "application/json");
        attachImage(requestSpecification, imagePath);
        return requestSpecification.post(recourseUrl);

    }

    public Response updateNews(Long newsId, RequestUpdateNews request, String imagePath) {
        RequestSpecification requestSpecification = preparedRequest()
                .contentType(ContentType.MULTIPART)
                .multiPart("updateEcoNewsDto", request, "application/json");
        attachImage(requestSpecification, imagePath);
        return requestSpecification.log().all().put(recourseUrl + "/" + newsId);
    }

    public Response getNewsById(Long newsId) {
        return preparedRequest()
                .get(recourseUrl + "/" + newsId);
    }


    public Response deleteNews(Long newsId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + newsId);
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
