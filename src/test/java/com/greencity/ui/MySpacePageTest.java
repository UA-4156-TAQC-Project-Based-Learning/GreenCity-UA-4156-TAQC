package com.greencity.ui;

import com.greencity.ui.pages.myspacepage.MySpacePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MySpacePageTest extends TestRunnerWithUser {


    @Test
    public void firstTest() {

        driver.get("http://localhost:4205/#/greenCity/profile");
        MySpacePage mySpacePage = new MySpacePage(driver);
        WebElement mySpaceDescription = mySpacePage.getMySpaceDescription();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(mySpaceDescription.isDisplayed());
        softAssert.assertEquals(mySpaceDescription.getText(),
                "Welcome to My Habits. It seems you don’t have any habits in progress. Let’s start to acquire a new one!");
        softAssert.assertAll();
    }
}
