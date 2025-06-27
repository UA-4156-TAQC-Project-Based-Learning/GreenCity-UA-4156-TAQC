package com.greencity.api.clients;

import io.qameta.allure.Step;
import io.restassured.response.Response;


public class FriendClient extends BaseClient{

    protected String recourseUrl = "/friends";
    public static final boolean FILTER_BY_CITY = true;
    public static final int DEFAULT_PAGE = 1;
    public static final int SIZE = 1;
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";

    public FriendClient(String baseUrl) {
        super(baseUrl);
    }
    @Step("Add new friend")
    public Response addNewFriend(Long friendId) {
        return preparedRequest()
                .post(recourseUrl + "/" + friendId);
    }
    @Step("Accept new friend")
    public Response acceptNewRequest(Long friendId) {
        return preparedRequest()
                .patch(recourseUrl + "/" + friendId + "/acceptFriend");
    }
    @Step("Decline friend")
    public Response declineNewRequest(Long friendId) {
        return preparedRequest()
                .patch(recourseUrl + "/" + friendId + "/declineFriend");
    }
    @Step("Delete friend")
    public Response deleteFriend(Long friendId) {
        return preparedRequest()
                .delete(recourseUrl + "/" + friendId);
    }

    @Step("Get all user friends and set FriendStatus related to current user")
    public Response getAllUserFriends(int page, int size) {
        return preparedRequest()
            .get(recourseUrl + String.format("/%d/all-user-friends?page=%d&size=%d",signIn.getUserId(), page, size));
    }
    @Step("Find recommended friends by type")
    public Response getRecommendedFriend() {
        return preparedRequest()
                .get(recourseUrl + "/recommended-friends?type=FRIENDS_OF_FRIENDS&page=0&size=5");
    }
    @Step("Find all user friends")
    public Response findAllFriends(String name, boolean filterByCity, int page, int size, FriendSortField sortField, boolean desc) {
        String sortParam = sortField.getField() + (desc ? ",desc" : ",asc");

        return preparedRequest()
                .queryParam("name", name)
                .queryParam("filterByCity", filterByCity)
                .queryParam("page", page)
                .queryParam("size", size)
                .queryParam("sort", sortParam)
                .get(recourseUrl);
    }

}
