package com.greencity.api.clients;

import com.greencity.api.models.user.RequestSignIn;
import io.restassured.response.Response;

public class AuthClient extends BaseClient {

    protected String recourseUrl = "api/testers/sign-in";

    public AuthClient(String baseUrl) {
        super(baseUrl);
    }

    public Response signIn(RequestSignIn request) {
        return preparedRequest()
                .body(request)
                .post(recourseUrl);

    }
}
