package com.greencity.api.clients;

import com.greencity.api.models.habits.AssignedHabitPage;
import com.greencity.api.models.habits.ResponseAssignedHabits;
import io.restassured.response.Response;

import java.util.List;

public class HabitClient extends BaseClient {

    private final String resourceUrl = "/habit";

    public HabitClient(String baseUrl) {
        super(baseUrl);
    }

    public Response getHabits(int page, int size) {
        return preparedRequest()
                .queryParam("page", page)
                .queryParam("size", size)
                .get(resourceUrl);
    }

    public Response getUserHabits(int user_id) {

        return preparedRequest()
                .queryParam("lang", "en")
                .get(resourceUrl + "/assign/allUser/" + user_id);
    }

    public Response assignHabit(int habitId) {
        return preparedRequest()
                .post(resourceUrl + "/assign/" + habitId);
    }

    public Response deleteAssignedHabits(int assignedHabitId) {
        return preparedRequest()
                .delete(resourceUrl + "/assign/delete/" + assignedHabitId);
    }

    public void deleteAllAssignedHabits(int userId) {
        Response response = getUserHabits(userId);
        List<AssignedHabitPage> assignedHabits = response.as(ResponseAssignedHabits.class).getPage();

        for (AssignedHabitPage assignedHabit : assignedHabits) {
            int assignedHabitId = assignedHabit.getId();
            deleteAssignedHabits(assignedHabitId);
        }
    }
}
