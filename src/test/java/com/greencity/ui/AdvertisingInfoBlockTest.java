package com.greencity.ui;

import com.greencity.ui.components.baseComponents.SignInModal;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.pages.myspacepage.MySpacePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import static com.greencity.utils.LoginUtil.loginViaLocalStorage;

@Feature("Advertising Block on Home Page")
public class AdvertisingInfoBlockTest extends BaseTestRunner {

    @BeforeMethod
    public void beforeEachTest() {
        driver.get(testValueProvider.getBaseUIUrl());
        localStorageJS.clearLocalStorage();
        driver.navigate().refresh();
    }

    @Test
    @Issue("107")
    @Description("Verify that the advertising block works correctly for a logged-in user: " +
            "clicking the 'Start forming a habit!' button redirects to the My Space page, " +
            "and the My Space description is visible.")
    @Owner("Svitlana Kovalova")
    public void verifyAdvertisingBlockForUnloggedUser() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage(driver);

        softAssert.assertTrue(homePage.isAdvertisingTitleDisplayed(), "Advertising title should be displayed");
        softAssert.assertEquals(homePage.getAdvertisingTitleText(), "A new way to cultivate useful habits");
        softAssert.assertTrue(homePage.isAdvertisingTextDisplayed(), "Advertising text should be displayed");
        softAssert.assertTrue(homePage.isAdvertisingImageDisplayed(), "Advertising image should be displayed");
        softAssert.assertTrue(homePage.isStartFormingHabitButtonDisplayed(), "Start forming a habit button should be visible");
        softAssert.assertEquals(homePage.getStartFormingHabitButtonText(), "Start forming a habit!");

        homePage.clickStartFormingHabitButton();

        SignInModal signInModal = new SignInModal(driver);
        softAssert.assertTrue(signInModal.isDisplayed(), "Login form should be displayed for unlogged user");

        softAssert.assertAll();
    }

    @Test
    @Issue("107")
    @Description("Verify that the advertising block is displayed correctly on the Home page for an unlogged user, " +
            "and that clicking the 'Start forming a habit!' button opens the login form.")
    @Owner("Svitlana Kovalova")
    public void testAdvertisingBlockForLoggedInUser() {
        loginViaLocalStorage(driver, localStorageJS, testValueProvider);

        SoftAssert softAssert = new SoftAssert();

        HomePage homePage = new HomePage(driver);
        homePage.clickStartFormingHabitButton();

        MySpacePage mySpacePage = new MySpacePage(driver);

        softAssert.assertTrue(driver.getCurrentUrl().contains("/profile"), "User should be redirected to My Space page");
        softAssert.assertTrue(mySpacePage.getMySpaceDescription().isDisplayed(), "My Space description should be visible");
        softAssert.assertAll();
    }
}

