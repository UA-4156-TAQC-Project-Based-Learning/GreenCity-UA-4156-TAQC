package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.clients.FriendClient;
import com.greencity.api.models.user.RequestSignIn;
import com.greencity.api.models.user.ResponseSignIn;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FriendTest extends ApiTestRunner {
    private FriendClient friendClient;

    @BeforeClass
    public void setUpTest() {
        AuthClient authClient = new AuthClient(testValueProvider.getBaseAPIUserUrl());

        RequestSignIn request = new RequestSignIn();
        request.setEmail(testValueProvider.getUserEmail());
        request.setPassword(testValueProvider.getUserPassword());
        request.setSecretKey(testValueProvider.getSecretKey());

        ResponseSignIn response = authClient.signIn(request).as(ResponseSignIn.class);

        friendClient = new FriendClient(testValueProvider.getBaseAPIUserUrl());
        friendClient.setToken(response.getAccessToken());
    }

    @Test
    public void addFriendTest() {
        Long friendId = 103L;
        Response response = friendClient.addNewFriend(friendId);
        response.then().statusCode(200);
    }

    @Test
    public void deleteFriendTest() {
        Long friendId = 103L;
        Response response = friendClient.deleteFriend(friendId);
        response.then().statusCode(200);
    }


}
