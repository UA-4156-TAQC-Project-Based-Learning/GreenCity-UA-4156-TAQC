package com.greencity.api.clients;

import io.restassured.response.Response;

public class HabitClient extends BaseClient {

        private final String resourceUrl = "/habit";

    public HabitClient(String baseUrl) {
        super(baseUrl);
    }


    public Response getHabits(int page, int size) {
        return preparedRequest()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(resourceUrl);
    }

}
