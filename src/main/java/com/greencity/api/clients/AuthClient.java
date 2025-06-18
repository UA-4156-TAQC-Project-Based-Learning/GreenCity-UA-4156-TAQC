package com.greencity.api.clients;

import io.restassured.response.Response;

public class AuthClient extends BaseClient{
    protected String resourceUrl = "/ownSecurity";

    public AuthClient(String baseUrl) {
        super(baseUrl);
    }


    public Response login(String email, String password) {
        return preparedRequest()
                .body("\"email\": " + email +
                        "  \"password\": " + password +
                        "  \"captchaToken\": " + token)
                .post(resourceUrl + "/signIn");
    }

}
