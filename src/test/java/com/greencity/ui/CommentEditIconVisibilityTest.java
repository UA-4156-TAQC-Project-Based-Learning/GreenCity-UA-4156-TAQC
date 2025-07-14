package com.greencity.ui;

import com.greencity.ui.components.newsComponents.CommentItem;
import com.greencity.ui.components.newsComponents.CommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.ui.testrunners.TestRunnerWithUser;
import com.greencity.utils.TestValueProvider;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Owner;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;

@Feature("News Comments")
public class CommentEditIconVisibilityTest extends TestRunnerWithUser {

    @Test
    @Issue("123")
    @Description("Ensure that the 'Edit' icon/link is visible only for comments created by the logged-in user.")
    @Owner("Svitlana Kovalova")
    public void verifyEditIconVisibilityForOwnCommentsOnly()  {
        SoftAssert softAssert = new SoftAssert();

        NewsPage newsPage = new HomePage(driver)
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        CommentsComponent commentsComponent = new CommentsComponent(driver, newsPage.getCommentsRoot());
        List<WebElement> commentElements = commentsComponent.getCommentItems();

        softAssert.assertTrue(commentElements.size() > 1, "There should be multiple comments to test.");

        String currentUser = new TestValueProvider().getLocalStorageName();

        boolean foundOwnComment = false;
        boolean foundOtherComment = false;

        for (WebElement commentElement : commentElements) {
            CommentItem commentItem = new CommentItem(driver, commentElement);

            String authorName = commentItem.getAuthorNameText().trim();
            boolean editButtonVisible = commentItem.isEditButtonVisible();

            if (authorName.equalsIgnoreCase(currentUser)) {
                foundOwnComment = true;
                softAssert.assertTrue(editButtonVisible,
                        "Edit button should be visible for the logged-in user's comment. Author: " + authorName);
            } else {
                foundOtherComment = true;
                softAssert.assertFalse(editButtonVisible,
                        "Edit button should NOT be visible for other users' comments. Author: " + authorName);
            }
        }

        softAssert.assertTrue(foundOwnComment, "Test data must contain at least one own comment.");
        softAssert.assertTrue(foundOtherComment, "Test data must contain at least one comment from another user.");
        softAssert.assertAll();
    }
}