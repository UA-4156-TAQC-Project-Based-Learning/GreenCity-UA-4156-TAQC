package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.models.user.ResponseSignIn;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthTest extends ApiTestRunner {
    private AuthClient authClient;
    @BeforeClass
    public void setUp() {
        authClient = new AuthClient(testValueProvider.getBaseAPIUrl());
    }

    @Test
    public void loginTest() {
        String email = testValueProvider.getUserEmail();
        String password = testValueProvider.getUserPassword();
        int userId = Integer.parseInt(testValueProvider.getLocalStorageUserId());
        String name = testValueProvider.getUserName();

        Response response = authClient.login(email, password);
        response.then().statusCode(200);
        ResponseSignIn responseLogin = response.as(ResponseSignIn.class);

        Assert.assertEquals(responseLogin.getUserId(), userId);
        Assert.assertEquals(responseLogin.getName(), name);

    }
}
