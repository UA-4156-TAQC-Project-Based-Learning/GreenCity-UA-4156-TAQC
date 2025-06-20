package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.models.user.RequestSignIn;
import com.greencity.api.models.user.ResponseSignIn;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest extends ApiTestRunner {

    private AuthClient authClient;

    @BeforeClass
    public void setUpTest() {
        authClient = new AuthClient(testValueProvider.getBaseAPIUserUrl());
    }

    @Test
    public void signInTest() {
        RequestSignIn request = new RequestSignIn();
        request.setEmail(testValueProvider.getUserEmail());
        request.setPassword(testValueProvider.getUserPassword());
        request.setSecretKey(testValueProvider.getSecretKey());

        Response response = authClient.signIn(request);

        response.then().statusCode(200);
        ResponseSignIn responseSignIn = response.as(ResponseSignIn.class);

        Assert.assertNotNull(responseSignIn.getAccessToken(),"Access token is null");
        Assert.assertNotNull(responseSignIn.getRefreshToken(), "Refresh token is null");
        Assert.assertEquals(responseSignIn.getName(), testValueProvider.getUserName(), "Name is null");
    }
}
