package com.greencity.api.clients;

import io.restassured.response.Response;

public class FriendClient extends BaseClient{

    protected String recourseUrl = "/friends";

    public FriendClient(String baseUrl) {
        super(baseUrl);
    }

    public Response addNewFriend(Long friendId) {
        return preparedRequest()
                .post(recourseUrl + "/" + friendId);
    }

    public Response acceptNewRequest(Long friendId) {
        return preparedRequest()
                .patch(recourseUrl + "/" + friendId + "/acceptFriend");
    }

    public Response declineNewRequest(Long friendId) {
        return preparedRequest()
                .patch(recourseUrl + "/" + friendId + "/declineFriend");
    }

    public Response deleteFriend(Long friendId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + friendId);
    }

    public Response findAllFriends() {
        return preparedRequest()
                .get(recourseUrl + "?filterByCity=false&page=0&size=5");
    }

    public Response getAllUserFriends(int page, int size) {
        return preparedRequest()
            .get(recourseUrl + String.format("/28/all-user-friends?page=%d&size=%d", page, size));
    }

    public Response getRecommendedFriend() {
        return preparedRequest()
                .get(recourseUrl + "/recommended-friends?type=FRIENDS_OF_FRIENDS&page=0&size=5");
    }
}
