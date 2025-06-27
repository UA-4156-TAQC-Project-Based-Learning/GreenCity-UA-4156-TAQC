package com.greencity.api;

import com.greencity.api.clients.AuthClient;
import com.greencity.api.clients.FriendClient;
import com.greencity.api.clients.FriendSortField;
import com.greencity.api.models.user.RequestSignIn;
import com.greencity.api.models.user.ResponseSignIn;
import com.greencity.api.testRunner.ApiTestRunner;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


@Epic("User API")
@Feature("Friend-controller")
@Issue("168")
@Owner("Yuliia Terentieva")
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
    @Description("Verify possibility to add new friend.")
    public void addNewTest() {
        Response response = friendClient.addNewFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(200);
        }


    @Test
    @Description("Verify response status in case add new friend request already exists.")
    @Feature("Friend-controller")
    public void addNewAlreadyExistsNegativeTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        Response response = friendClient.addNewFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(400);
    }

    @Test
    @Description("Verify accept new friend.")
    public void acceptNewTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        Response response = friendClientUserB.acceptNewRequest(TEST_USER_A_ID);
        response.then().log().all().statusCode(200);

    }

    @Test
    @Description("Verify decline new friend.")
    public void declineNewTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        Response response = friendClientUserB.declineNewRequest(TEST_USER_A_ID);
        response.then().log().all().statusCode(200);

    }

    @Test
    @Description("Verify delete existing friend.")
    public void deleteFriendTest() {
        friendClient.addNewFriend(TEST_USER_B_ID);
        friendClientUserB.acceptNewRequest(TEST_USER_A_ID);
        Response response = friendClient.deleteFriend(TEST_USER_B_ID);
        response.then().log().all().statusCode(200);
    }

    @Test
    @Description("Verify find all friends for current user.")
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

    @Test
    @Description("Verify getAllUserFriends returns 200 for valid page and size.")
    public void testGetAllUserFriends() {
        int page = 0;
        int size = 5;
        Response response = friendClient.getAllUserFriends(page, size);
        response.then().log().all().statusCode(200);
    }

    @Test
    @Description("Verify getRecommendedFriend returns 200 and list is not null.")
    public void testGetRecommendedFriend() {
        Response response = friendClient.getRecommendedFriend();
        response.then().log().all().statusCode(200);
    }



}
