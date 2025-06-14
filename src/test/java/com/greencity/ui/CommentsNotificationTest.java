package com.greencity.ui;

import com.greencity.ui.components.newsComponents.NewsCommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.pages.notificationPage.NotificationPage;
import com.greencity.ui.testrunners.TestRunnerWithUsers;
import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CommentsNotificationTest extends TestRunnerWithUsers {

    @Test
    @Issue("120")
    @Description("Verify that User A does NOT receive email or notification when User B adds or deletes a comment on User A’s post.")
    @Owner("Svitlana Kovalova")
    public void verifyNoNotificationSentToUserAAfterUserBComments() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        loginAsUserA();

        NewsPage newsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        NewsCommentsComponent comments = new NewsCommentsComponent(driver, newsPage.getCommentsRoot());
        int initialCommentCount = comments.getDisplayedCommentCount();

        cleanLocalStorage();
        loginAsUserB();

        newsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        comments = new NewsCommentsComponent(driver, newsPage.getCommentsRoot());

        String commentText = "Notification test comment " + System.currentTimeMillis();
        comments.addComment(commentText);
        comments.waitForNewCommentCount(initialCommentCount, 5);

        int afterAddCount = comments.getDisplayedCommentCount();
        softAssert.assertEquals(afterAddCount, initialCommentCount + 1, "Comment should be added by User B");

        cleanLocalStorage();

        loginAsUserA();

        NotificationPage notificationPage = new HomePage(driver)
                .getHeader()
                .getUserDropdown()
                .goToNotifications();

        softAssert.assertTrue(notificationPage.isTitleDisplayed(), "Notifications page title should be visible");
        softAssert.assertTrue(notificationPage.isNoNotificationsMessageDisplayed(),
                "User A should NOT receive any notifications after User B adds comment");
        softAssert.assertEquals(notificationPage.getNotificationCount(), 0,
                "Notification list should be empty");

        softAssert.assertAll();
    }
}