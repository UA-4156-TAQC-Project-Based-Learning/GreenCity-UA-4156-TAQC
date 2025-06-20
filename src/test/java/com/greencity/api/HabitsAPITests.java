package com.greencity.api;

import com.greencity.api.clients.HabitClient;
import com.greencity.api.models.habits.ResponseHabits;
import com.greencity.api.testRunner.AuthorizedApiTestRunner;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;


public class HabitsAPITests extends AuthorizedApiTestRunner {

    private HabitClient habitClient;


    @Test
    public void getHabitsAuthorizedTest(){
        HabitClient habitClient = new HabitClient(testValueProvider.getBaseAPIGreencityUrl());
        habitClient.setToken(token);

        Response response = habitClient.getHabits(0, 1);

        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(response.statusCode(), 200, "Status should be 200");

        softAssert.assertAll();
    }

}
