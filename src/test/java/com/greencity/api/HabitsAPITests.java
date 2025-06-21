package com.greencity.api;

import com.greencity.api.clients.HabitClient;
import com.greencity.api.models.habits.HabitPage;
import com.greencity.api.models.habits.HabitTranslation;
import com.greencity.api.models.habits.ResponseHabits;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class HabitsAPITests extends AuthorizedApiTestRunner {

    @Test
    public void getHabitsAuthorizedTest() {
        HabitClient habitClient = new HabitClient(testValueProvider.getBaseAPIGreencityUrl());
        habitClient.setToken(token);

        int page = 0;
        int size = 1;
        Response response = habitClient.getHabits(page, size);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status should be 200");

        ResponseHabits responseHabits = response.as(ResponseHabits.class);

        softAssert.assertEquals(responseHabits.getCurrentPage(), 0, "Page should be " + page);
        softAssert.assertTrue(responseHabits.getTotalElements() > 0, "Elements number should be >0");
        softAssert.assertTrue(responseHabits.getTotalPages() > 0, "Pages number should be >0");


        List<HabitPage> habits = responseHabits.getPage();
        softAssert.assertFalse(habits.isEmpty(), "Habits list should not be empty");

        HabitPage habit = habits.getFirst();

        softAssert.assertTrue(habit.getId() > 0, "ID should be > 0");

        HabitTranslation translation = habit.getHabitTranslation();

        softAssert.assertNotNull(translation, "habitTranslation should not be null");
        softAssert.assertNotNull(translation.getName(), "Name should not be null");
        softAssert.assertNotNull(translation.getLanguageCode(), "Language should not be null");
        softAssert.assertNotNull(habit.getToDoListItems(), "toDoListItems should not be null");
        softAssert.assertTrue(habit.getToDoListItems().size() >= 0, "toDoListItems should not be null");
        softAssert.assertNotNull(habit.getCustomToDoListItems(), "customToDoListItems should not be null");
        softAssert.assertTrue(habit.getLikes() >= 0, "likes >= 0");
        softAssert.assertTrue(habit.getDislikes() >= 0, "dislikes >= 0");
        softAssert.assertAll();
    }

}
