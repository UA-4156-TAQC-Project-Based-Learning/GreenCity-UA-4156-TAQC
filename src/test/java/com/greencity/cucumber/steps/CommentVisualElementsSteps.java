package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.newsComponents.CommentItem;
import com.greencity.ui.components.newsComponents.CommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import java.util.List;

public class CommentVisualElementsSteps {
    private final Hooks hooks;
    private final SoftAssert softAssert;
    private List<WebElement> commentElements;
    private CommentItem firstCommentItem;
    private String loggedInUserName;

    public CommentVisualElementsSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
    }

    @Given("the user has successfully logged in to view comments")
    public void theUserHasSuccessfullyLoggedInToViewComments() {
        hooks.loginViaLocalStorage();
        loggedInUserName = hooks.getTestValueProvider().getLocalStorageName();
        softAssert.assertNotNull(loggedInUserName, "Logged in user name should not be null after login.");
        softAssert.assertFalse(loggedInUserName.isEmpty(), "Logged in user name should not be empty after login.");
    }

    @Given("the user opens the first news article page in Eco News")
    public void theUserOpensFirstNewsArticle() {
        NewsPage newsPage = new HomePage(hooks.getDriver())
                .getHeader()
                .goToEcoNews()
                .clickFirstNewsPage();

        CommentsComponent comments = new CommentsComponent(hooks.getDriver(), newsPage.getCommentsRoot());
        commentElements = comments.getCommentItems();
        if (!commentElements.isEmpty()) {
            firstCommentItem = new CommentItem(hooks.getDriver(), commentElements.get(0));
        }
    }

    @Given("the user observes the comments section")
    public void theUserObservesCommentsSection() {

    }

    @Then("at least one comment is displayed")
    public void atLeastOneCommentIsDisplayed() {
        softAssert.assertNotNull(commentElements, "Comment list should not be null.");
        softAssert.assertFalse(commentElements.isEmpty(), "There should be at least one comment.");
    }

    @Then("the comment text is visible")
    public void commentTextIsVisible() {
        softAssert.assertFalse(firstCommentItem.getCommentText().isEmpty(), "Comment text should be displayed.");
    }

    @Then("the comment author name is visible")
    public void commentAuthorNameIsVisible() {
        softAssert.assertFalse(firstCommentItem.getAuthorNameText().isEmpty(), "Author name should be displayed.");
    }

    @Then("the comment avatar icon is visible")
    public void commentAvatarIsVisible() {
        softAssert.assertTrue(firstCommentItem.getLikeCommentIcon().isDisplayed(), "Avatar icon should be displayed.");
    }

    @Then("the comment date is visible")
    public void commentDateIsVisible() {
        softAssert.assertFalse(firstCommentItem.getCommentDateText().isEmpty(), "Comment date should be displayed.");
    }

    @Then("the edit button is visible")
    public void editButtonIsVisible() {
        softAssert.assertTrue(firstCommentItem.getEditButton().isDisplayed(), "Edit button should be displayed.");
    }

    @Then("the delete button is visible")
    public void deleteButtonIsVisible() {
        softAssert.assertTrue(firstCommentItem.getDeleteButton().isDisplayed(), "Delete button should be displayed.");
    }

    @Then("the reply button is visible")
    public void replyButtonIsVisible() {
        softAssert.assertTrue(firstCommentItem.getReplyButton().isDisplayed(), "Reply button should be displayed.");
    }
}