package com.greencity.ui.homePageTests;

import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

@Feature("Eco-news Subscription Section")
public class EcoNewsSubscriptionTest extends BaseTestRunner {

    @BeforeMethod
    public void scrollToSubscriptionSection() {
        homePage.scrollToElement(homePage.getSectionSubscription());
    }

    @Test
    @Description("Verify that the Eco-news subscription section is visible for guest user")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("105")
    public void testSubscriptionSectionIsVisible() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertTrue(homePage.getSectionSubscription().isDisplayed(), "Subscription Section not displayed");
        softAssert.assertTrue(homePage.getSectionSubscriptionQRImg().isDisplayed(), "QR image not displayed");
        softAssert.assertTrue(homePage.getSectionSubscriptionDescription().isDisplayed(), "Description not displayed");
        softAssert.assertTrue(homePage.getSectionSubscriptionEmailInput().isDisplayed(), "Email input not displayed");

        softAssert.assertAll();
    }

    @Test
    @Description("Verify subscription section title, description and email input placeholder")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("105")
    public void testSubscriptionTextsAndPlaceholder() {
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(homePage.getSectionSubscriptionTitle().getText(),
                "Receive interesting news", "Title mismatch");

        softAssert.assertEquals(homePage.getSectionSubscriptionDescription().getText(),
                "Subscribe for our newsletter and always be up to date with news and updates",
                "Description text mismatch");

        softAssert.assertEquals(homePage.getSectionSubscriptionEmailInput().getAttribute("placeholder"),
                "Enter your email", "Placeholder mismatch");

        softAssert.assertAll();
    }

    @Test
    @Description("Verify email validation with invalid email input")
    @Owner("Prykhodchenko Oleksandra")
    @Issue("105")
    public void testInvalidEmailValidation() {
        homePage.enterEmailIntoSectionSubscriptionEmailInput("eco-news");

        String classAttribute = homePage
                .getSectionSubscriptionValidationEmailError()
                .getAttribute("class");

        Assert.assertEquals(classAttribute, "visible", "Invalid email should trigger visible error");
    }

    @Test
    @Description("Verify email submission with valid email and success confirmation")
    @Issue("105")
    @Owner("Prykhodchenko Oleksandra")
    public void testValidEmailSubscription() {
        String email = generateRandomEmail();
        homePage.enterEmailIntoSectionSubscriptionEmailInput(email);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(homePage.getSectionSubscriptionValidationEmailError().getAttribute("class"),
                "hidden", "Valid email should not trigger error");


        softAssert.assertTrue(homePage
                        .clickSectionSubscriptionSubmitButton()
                        .getSectionSubscriptionSuccessMessage()
                        .isDisplayed(),
                "Success message not displayed");

        softAssert.assertEquals(homePage.getSectionSubscriptionSuccessMessage().getText().trim(),
                "You successfully subscribed to the newsletter", "Success message mismatch");

        softAssert.assertAll();
    }

    private String generateRandomEmail() {
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder localPart = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            localPart.append(chars.charAt(random.nextInt(chars.length())));
        }
        return localPart.toString() + "@example.com";
    }
}