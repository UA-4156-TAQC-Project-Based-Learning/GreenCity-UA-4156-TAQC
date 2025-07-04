package com.greencity.api.testRunner;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.models.user.RequestSignIn;
import com.greencity.api.models.user.ResponseSignIn;
import org.testng.annotations.BeforeClass;


public class AuthorizedApiTestRunner extends ApiTestRunner {
    protected static ResponseSignIn signIn;

    @BeforeClass
    @Override
    public void setUp() {
        super.setUp();

        AuthClient authClient = new AuthClient(testValueProvider.getBaseAPIUserUrl());
        RequestSignIn request = new RequestSignIn(
                testValueProvider.getUserEmail(),
                testValueProvider.getUserPassword(),
                testValueProvider.getSecretKey()
        );

        signIn = authClient
                .signIn(request)
                .then()
                .statusCode(200)
                .extract()
                .as(ResponseSignIn.class);

    }
}

