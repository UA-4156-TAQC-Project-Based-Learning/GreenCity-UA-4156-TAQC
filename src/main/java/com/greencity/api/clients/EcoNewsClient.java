package com.greencity.api.clients;

import com.greencity.api.models.econews.RequestEcoNews;
import io.restassured.response.Response;

public class EcoNewsClient extends BaseClient {

    protected String recourseUrl = "/eco-news";

    public EcoNewsClient(String baseUrl) {
        super(baseUrl);
    }

    public Response createNews(RequestEcoNews request) {

        return preparedRequest()
                .body(request)
                .post(recourseUrl);

    }

    public Response getNewsById(int newsId) {
        return preparedRequest()
                .get(recourseUrl + "/" + newsId);
    }


    public Response deleteNews(int newsId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + newsId);
    }
}
