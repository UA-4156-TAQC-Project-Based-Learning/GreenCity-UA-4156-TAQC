package com.greencity.api.clients;

import io.restassured.response.Response;


public class FriendClient extends BaseClient{

    protected String recourseUrl = "/friends";
    public static final Long TEST_USER_A_ID = 5L;
    public static final Long TEST_USER_B_ID = 7L;
    public static final String DEFAULT_NAME = "YuliiaTest2";
    public static final boolean FILTER_BY_CITY = true;
    public static final int DEFAULT_PAGE = 1;
    public static final int SIZE = 1;
    public static final String SORT_ASC = "ASC";
    public static final String SORT_DESC = "DESC";

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
