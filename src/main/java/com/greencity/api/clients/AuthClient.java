package com.greencity.api.clients;

import io.restassured.response.Response;

public class AuthClient extends BaseClient {

    protected String recourseUrl = "/ownSecurity";

    public AuthClient(String baseUrl) {
        super(baseUrl);
    }

    public Response signIn(String email, String password) {
        return preparedRequest()
                .body("{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}")
                .post(recourseUrl + "/signIn");
        
    }
}
