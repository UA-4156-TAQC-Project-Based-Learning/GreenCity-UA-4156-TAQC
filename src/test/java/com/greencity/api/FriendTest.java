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
    private FriendClient friendClientUserB;
    private static final Long TEST_USER_A_ID = 5L;
    private static final Long TEST_USER_B_ID = 7L;

    @BeforeClass
    public void setUpTest() {
        AuthClient authClient = new AuthClient(testValueProvider.getBaseAPIUserUrl());
        RequestSignIn requestSignIn = new RequestSignIn();
        requestSignIn.setEmail(testValueProvider.getUserEmail());
        requestSignIn.setPassword(testValueProvider.getUserPassword());
        requestSignIn.setSecretKey(testValueProvider.getSecretKey());
        ResponseSignIn responseSignIn = authClient.signIn(requestSignIn).as(ResponseSignIn.class);

        friendClient = new FriendClient(testValueProvider.getBaseAPIGreencityUrl());
        friendClient.setToken(responseSignIn.getAccessToken());

        friendClientUserB = new FriendClient(testValueProvider.getBaseAPIGreencityUrl());
        friendClientUserB.setToken(testValueProvider.getLocalStorageAccessTokenUserB());
    }

    @Test
    public void addNewTest() {
        Response response = friendClient.addNewFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(200);
    }

    @Test
    public void addNewAlreadyExistsNegativeTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        Response response = friendClient.addNewFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(400);
    }

    @Test
    public void acceptNewTest() {
        Response response = friendClientUserB.acceptNewRequest(TEST_USER_A_ID);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void declineNewTest() {
        Response response = friendClientUserB.declineNewRequest(TEST_USER_A_ID);
        response.then().log().all().statusCode(200);

    }


    @Test
    public void deleteFriendTest() {
        Response response = friendClient.deleteFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(200);
    }


}
