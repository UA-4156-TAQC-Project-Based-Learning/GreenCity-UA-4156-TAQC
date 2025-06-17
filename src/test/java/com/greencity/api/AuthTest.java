package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.models.user.ResponseSignIn;
import com.greencity.api.testRunner.ApiTestRunner;
import com.greencity.utils.TestValueProvider;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest extends ApiTestRunner {

    private AuthClient authClient;

    @BeforeClass
    public void setUp() {
        if (testValueProvider == null) {
            testValueProvider = new TestValueProvider(); // fallback
        }
        authClient = new AuthClient(testValueProvider.getBaseAPIUrl());
    }

    @Test
    public void signInTest() {
        String email = testValueProvider.getUserEmail();
        String password = testValueProvider.getUserPassword();
        Response response = authClient.signIn(email,password);
        response.then().statusCode(200);
        ResponseSignIn responseSignIn = response.as(ResponseSignIn.class);

    }
}
