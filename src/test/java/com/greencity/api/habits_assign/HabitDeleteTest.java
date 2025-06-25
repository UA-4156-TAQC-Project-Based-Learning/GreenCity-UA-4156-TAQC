package com.greencity.api.habits_assign;

import com.greencity.api.clients.HabitClient;
import com.greencity.api.models.habits.AssignedHabitPage;
import com.greencity.api.models.habits.ResponseAssignedHabits;
import com.greencity.api.models.habits.ResponseHabits;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class HabitDeleteTest extends AuthorizedApiTestRunner {

    private HabitClient habitClient;

    @BeforeClass
    public void initClient() {
        habitClient = new HabitClient(testValueProvider.getBaseAPIGreencityUrl());
        habitClient.setSignIn(signIn);
    }

    @Test
    public void verifySuccessfulDeleteHabitAssignedUser() {

        int habitIdAssignToUser = habitClient.getHabits(0, 10).as((ResponseHabits.class)).getPage().getFirst().getId();

        List<AssignedHabitPage> assignedHabits = habitClient.getUserHabits(signIn.getUserId()).as(ResponseAssignedHabits.class).getPage();

        if (assignedHabits == null || assignedHabits.isEmpty()) {
            habitClient.assignHabit(habitIdAssignToUser);
        }

        assignedHabits = habitClient.getUserHabits(signIn.getUserId()).as(ResponseAssignedHabits.class).getPage();
        int habitIdToDelete = assignedHabits.getFirst().getId();
        habitClient.deleteAssignedHabits(habitIdToDelete).then().statusCode(200);

        List<AssignedHabitPage> assignedHabitAfterDelete = habitClient.getUserHabits(signIn.getUserId()).as(ResponseAssignedHabits.class).getPage();

        boolean isHabitAssigned = assignedHabitAfterDelete.stream()
                .anyMatch(h -> h.getId() == habitIdToDelete);

        Assert.assertFalse(isHabitAssigned, "Звичка не була видалена");

    }

    @Test
    public void deleteHabitWithInvalidId() {
        int invalidHabitAssignId = Integer.MIN_VALUE; // або можна спробувати 0 або Integer.MIN_VALUE

        habitClient.deleteAssignedHabits(invalidHabitAssignId)
                .then()
                .statusCode(404);
    }
    @Test
    public void deleteHabitWithInvalidPathParam() {
        given()
                .baseUri(testValueProvider.getBaseAPIGreencityUrl())
                .header("Authorization", "Bearer " + signIn.getAccessToken())
                .when()
                .delete("/habit/assign/delete/invalid_id") // ← не число
                .then()
                .statusCode(400);
    }

}
