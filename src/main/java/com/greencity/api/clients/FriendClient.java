package com.greencity.api.clients;

import io.restassured.response.Response;

public class FriendClient extends BaseClient{

    protected String recourseUrl = "/friends";

    public FriendClient(String baseUrl) {
        super(baseUrl);
    }

    public Response addNewFriend(Long friendId) {
        return preparedRequest()
                .post(recourseUrl + "/friends/" + friendId);
    }

    public Response deleteFriend(Long friendId) {
        return preparedRequest()
                .delete(recourseUrl + "/friends/" + friendId);
    }

    public Response findAllFriends() {
        return preparedRequest()
                .get(recourseUrl + "/friends?filterByCity=false&page=0&size=5");
    }

    public Response getAllUserFriends() {
        return preparedRequest()
                .get(recourseUrl + "/friends/28/all-user-friends?page=0&size=5");
    }

    public Response getRecommendedFriend() {
        return preparedRequest()
                .get(recourseUrl + "/friends/recommended-friends?type=FRIENDS_OF_FRIENDS&page=0&size=5");
    }
}
