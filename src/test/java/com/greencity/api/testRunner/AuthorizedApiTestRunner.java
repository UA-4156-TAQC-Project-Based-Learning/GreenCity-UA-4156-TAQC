package com.greencity.api.testRunner;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.models.user.RequestSignIn;
import com.greencity.api.models.user.ResponseSignIn;
import org.testng.annotations.BeforeSuite;

public class AuthorizedApiTestRunner extends ApiTestRunner {
    protected static String token;

    @BeforeSuite
    @Override

    public void setUp() {
        super.setUp();

        AuthClient authClient = new AuthClient(testValueProvider.getBaseAPIUserUrl());
        RequestSignIn request = new RequestSignIn(
                testValueProvider.getUserEmail(),
                testValueProvider.getUserPassword(),
                testValueProvider.getSecretKey()
        );

        ResponseSignIn  response = authClient
                .signIn(request)
                .then()
                .statusCode(200)
                .extract()
                .as(ResponseSignIn.class);

        token = response.getAccessToken();
    }
}

