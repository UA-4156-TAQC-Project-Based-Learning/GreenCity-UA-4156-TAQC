package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.clients.FriendClient;
import com.greencity.api.clients.FriendSortField;
import com.greencity.api.models.user.RequestSignIn;
import com.greencity.api.models.user.ResponseSignIn;
import com.greencity.api.testRunner.ApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FriendTest extends ApiTestRunner {
    private FriendClient friendClient;
    private FriendClient friendClientUserB;
    public static final Long TEST_USER_A_ID = 5L;
    public static final Long TEST_USER_B_ID = 7L;
    public static final String DEFAULT_NAME = "YuliiaTest2";

    @BeforeClass
    public void setUpTest() {
        AuthClient authClient = new AuthClient(testValueProvider.getBaseAPIUserUrl());

        RequestSignIn requestSignIn = new RequestSignIn();
        requestSignIn.setEmail(testValueProvider.getUserEmail());
        requestSignIn.setPassword(testValueProvider.getUserPassword());
        requestSignIn.setSecretKey(testValueProvider.getSecretKey());
        ResponseSignIn responseSignIn = authClient.signIn(requestSignIn).as(ResponseSignIn.class);

        friendClient = new FriendClient(testValueProvider.getBaseAPIGreencityUrl());
        friendClient.setSignIn(responseSignIn);

        RequestSignIn requestSignInUserB = new RequestSignIn();
        requestSignInUserB.setEmail(testValueProvider.getUserBEmail());
        requestSignInUserB.setPassword(testValueProvider.getUserBPassword());
        requestSignInUserB.setSecretKey(testValueProvider.getUserBSecretKey());
        ResponseSignIn responseSignInUserB = authClient.signIn(requestSignInUserB).as(ResponseSignIn.class);

        friendClientUserB = new FriendClient(testValueProvider.getBaseAPIGreencityUrl());
        friendClientUserB.setSignIn(responseSignInUserB);
    }

    @BeforeMethod
    public void resetFriendState() {
        friendClient.deleteFriend(TEST_USER_B_ID);
        friendClientUserB.declineNewRequest(TEST_USER_A_ID);
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
        friendClient.addNewFriend(TEST_USER_B_ID);
        Response response = friendClientUserB.acceptNewRequest(TEST_USER_A_ID);
        response.then().log().all().statusCode(200);

    }

    @Test
    public void declineNewTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        Response response = friendClientUserB.declineNewRequest(TEST_USER_A_ID);
        response.then().log().all().statusCode(200);

    }


    @Test
    public void deleteFriendTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        friendClientUserB.acceptNewRequest(TEST_USER_A_ID);
        Response response = friendClient.deleteFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(200);
    }


    @Test
    public void testFindFriendsWithDefaultParams() {
        Response response = friendClient.findAllFriends(
                DEFAULT_NAME,
                FriendClient.FILTER_BY_CITY,
                FriendClient.DEFAULT_PAGE,
                FriendClient.SIZE,
                FriendSortField.NAME,
                FriendClient.SORT_DESC.equals("DESC")
        );

        response.then().statusCode(200);
    }


}
