package com.greencity.ui;

import com.greencity.ui.components.newsComponents.NewsCommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.BaseTestRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.time.Duration;

import static com.greencity.utils.LoginUtil.loginViaLocalStorage;

public class AddCommentsToNewsTest extends BaseTestRunner {

    @BeforeMethod
    public void beforeEachTest() {
        driver.get(testValueProvider.getBaseUIUrl());
        localStorageJS.clearLocalStorage();
        driver.navigate().refresh();
    }

    @Test
    @Issue("115")
    @Description("Verify that unregistered users can view but cannot add comments on the news page.")
    @Owner("Svitlana Kovalova")
    public void verifyCommentsSectionForUnregisteredUser() {
        SoftAssert softAssert = new SoftAssert();

        NewsPage newsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        NewsCommentsComponent comments = new NewsCommentsComponent(driver, newsPage.getCommentsRoot());

        softAssert.assertTrue(comments.getCommentsTitleText().equals("Comments"), "Title 'Comments' should be visible");
        softAssert.assertTrue(comments.getDisplayedCommentCount() >= 0, "Comments should be displayed");

        softAssert.assertFalse(comments.isElementInteractable(comments.getCommentInput()), "Comment input should not be available");
        softAssert.assertFalse(comments.isElementInteractable(comments.getCommentButton()), "Comment button should not be available");

        softAssert.assertAll();
    }

    @Test
    @Issue("115")
    @Description("Verify that registered users can view and add comments on the news page.")
    @Owner("Svitlana Kovalova")
    public void verifyCommentsSectionForLoggedInUser() {
        loginViaLocalStorage(driver, localStorageJS, testValueProvider);

        SoftAssert softAssert = new SoftAssert();

        NewsPage newsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        NewsCommentsComponent comments = new NewsCommentsComponent(driver, newsPage.getCommentsRoot());

        softAssert.assertTrue(comments.getCommentsTitleText().equals("Comments"), "Title 'Comments' should be visible");

        int before = comments.getDisplayedCommentCount();

        String commentText = "Test comment " + System.currentTimeMillis();
        comments.addComment(commentText);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(driver -> comments.getDisplayedCommentCount() > before);

        int after = comments.getDisplayedCommentCount();

        softAssert.assertEquals(after, before + 1, "New comment should be added");
        softAssert.assertAll();
    }
}
