package com.greencity.ui;

import com.greencity.ui.components.newsComponents.NewsCommentItem;
import com.greencity.ui.components.newsComponents.NewsCommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

@Feature("News Comments")
public class CommentVisualElementsDisplayedTest extends TestRunnerWithUser {

    @Test
    @Issue("118")
    @Description("Verify that at least one comment exists and all its visual elements (text, username, avatar, date, buttons) are displayed correctly.")
    @Owner("Svitlana Kovalova")
    public void verifyCommentVisualElementsDisplayed() {
        SoftAssert softAssert = new SoftAssert();

        NewsPage newsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        NewsCommentsComponent comments = new NewsCommentsComponent(driver, newsPage.getCommentsRoot());

        int commentCount = comments.getDisplayedCommentCount();
        softAssert.assertTrue(commentCount > 0, "There should be at least one comment");

        WebElement commentElement = comments.getCommentItems().get(0);
        NewsCommentItem commentItem = new NewsCommentItem(driver, commentElement);

        softAssert.assertFalse(commentItem.getCommentText().isEmpty(), "Comment text should be displayed");
        softAssert.assertFalse(commentItem.getAuthorNameText().isEmpty(), "Username should be displayed");
        softAssert.assertTrue(commentItem.getLikeCommentIcon().isDisplayed(), "Avatar (like icon) should be displayed");
        softAssert.assertFalse(commentItem.getCommentDateText().isEmpty(), "Comment date should be displayed");

        softAssert.assertTrue(commentItem.getEditButton().isDisplayed(), "Edit button should be displayed");
        softAssert.assertTrue(commentItem.getDeleteButton().isDisplayed(), "Delete button should be displayed");
        softAssert.assertTrue(commentItem.getReplyButton().isDisplayed(), "Reply button should be displayed");

        softAssert.assertAll();
    }
}
