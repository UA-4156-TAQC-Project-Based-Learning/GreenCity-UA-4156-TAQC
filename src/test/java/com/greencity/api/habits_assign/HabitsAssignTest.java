package com.greencity.api.habits_assign;

import com.greencity.api.clients.HabitClient;
import com.greencity.api.models.habits.AssignedHabitPage;
import com.greencity.api.models.habits.HabitPage;
import com.greencity.api.models.habits.ResponseAssignedHabits;
import com.greencity.api.models.habits.ResponseHabits;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class HabitsAssignTest extends AuthorizedApiTestRunner {

    private HabitClient habitClient;

    @BeforeClass
    public void initClient() {
        habitClient = new HabitClient(testValueProvider.getBaseAPIGreencityUrl());
        habitClient.setToken(token);
    }

    @BeforeMethod
    public void getListOfHabits() {
        habitClient.deleteAllAssignedHabits(testValueProvider.getLocalStorageUserId());
    }

    @Test
    public void assignHabitToAuthUserTest() {

        int habitIdAssignToUser=getRandomHabitFromAllHabitsList();
        Response assignResponse = habitClient.assignHabit(habitIdAssignToUser);
        assignResponse.then().statusCode(201);

        Response userHabitsResponse = habitClient.getUserHabits(testValueProvider.getLocalStorageUserId());
        List<AssignedHabitPage> assignedHabit = userHabitsResponse.as(ResponseAssignedHabits.class).getPage();
        Assert.assertEquals(assignedHabit.getFirst().getHabit().getId(), habitIdAssignToUser, "Habit " + habitIdAssignToUser+" should be assigned to user");

    }

    public int getRandomHabitFromAllHabitsList() {
        Response responseAllHabits = habitClient.getHabits(0, 100);
        ResponseHabits allHabits = responseAllHabits.as(ResponseHabits.class);
        List<HabitPage> habitPages = allHabits.getPage();
        Random random = new Random();
        HabitPage randomHabit = habitPages.get(random.nextInt(habitPages.size()));
        int randomHabitId = randomHabit.getId();
        return randomHabitId;
    }

}
