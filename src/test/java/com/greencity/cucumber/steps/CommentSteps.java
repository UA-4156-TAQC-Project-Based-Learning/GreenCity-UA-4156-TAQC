package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.newsComponents.CommentItem;
import com.greencity.ui.components.newsComponents.CommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class CommentSteps {

    private final Hooks hooks;
    private final SoftAssert softAssert;
    private List<WebElement> commentElements;
    private String loggedInUserName;
    private final NewsPage newsPage;
    private int previousCommentCount;

    public CommentSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
        this.newsPage = new NewsPage(hooks.getDriver());
    }

    public CommentsComponent getCommentsComponent() {
        return new CommentsComponent(hooks.getDriver(), newsPage.getCommentsRoot());
    }

    @Given("the user is logged in")
    public void userIsLoggedIn() {
        hooks.loginViaLocalStorage();
        loggedInUserName = hooks.getTestValueProvider().getLocalStorageName();
        softAssert.assertNotNull(loggedInUserName, "Logged in user name should not be null after login.");
        softAssert.assertFalse(loggedInUserName.isEmpty(), "Logged in user name should not be empty after login.");
    }

    @Given("the user navigates to the first news page in Eco News")
    public void userNavigatesToFirstNewsPage() {
        commentElements = new CommentsComponent(
                hooks.getDriver(),
                new HomePage(hooks.getDriver())
                        .getHeader()
                        .goToEcoNews()
                        .clickFirstNewsPage()
                        .getCommentsRoot())
                .getCommentItems();
    }

    @When("the user views the list of comments")
    public void userViewsListOfComments() {
    }

    @Then("there should be multiple comments")
    public void thereShouldBeMultipleComments() {
        softAssert.assertNotNull(commentElements, "Comment elements list should not be null.");
        softAssert.assertTrue(commentElements.size() > 1,
                "Expected multiple comments, but found: " + commentElements.size());
    }

    @Then("at least one comment is authored by the logged-in user")
    public void atLeastOneCommentByLoggedInUser() {
        String currentUser = hooks.getTestValueProvider().getLocalStorageName();
        boolean found = commentElements.stream().anyMatch(el ->
                new CommentItem(hooks.getDriver(), el).getAuthorNameText().trim().equalsIgnoreCase(currentUser));
        softAssert.assertTrue(found, "Expected at least one comment by the logged-in user: " + currentUser);
    }

    @Then("at least one comment is authored by another user")
    public void atLeastOneCommentByAnotherUser() {
        String currentUser = hooks.getTestValueProvider().getLocalStorageName();
        boolean found = commentElements.stream().anyMatch(el ->
                !new CommentItem(hooks.getDriver(), el).getAuthorNameText().trim().equalsIgnoreCase(currentUser));
        softAssert.assertTrue(found, "Expected at least one comment by another user (not " + currentUser + ")");
    }

    @Then("the edit icon is visible only for comments authored by the logged-in user")
    public void editIconVisibleOnlyForOwnComments() {
        commentElements.forEach(el -> {
            CommentItem item = new CommentItem(hooks.getDriver(), el);
            boolean isEditVisible = item.isEditButtonVisible();
            String author = item.getAuthorNameText().trim();

            if (author.equalsIgnoreCase(loggedInUserName)) {
                try {
                    isEditVisible = item.isEditButtonVisible();
                } catch (Exception e) {
                    isEditVisible = false;
                }
                softAssert.assertTrue(isEditVisible,
                        "Edit button should be visible for user's own comment. Author: " + author);
            } else {
                try {
                    isEditVisible = item.isEditButtonVisible();
                } catch (Exception e) {
                    isEditVisible = item.isEditButtonVisible();
                }
                softAssert.assertFalse(isEditVisible,
                        "Edit button should NOT be visible for others' comments. Author: " + author);
            }
        });
    }

    @When("add comment {string}")
    public void addComment(String comment){
        getCommentsComponent().addComment(comment);
    }

    @Then("comment {string} appears below the input field")
    public void isCommentDisplayed(String expectedComment){
        String createdComment = getCommentsComponent().getCommentItems().getFirst().getText();
        softAssert.assertTrue(createdComment.contains(expectedComment));
    }

    @Then("displays correct date")
    public void isDateCorrect(){
    String actualDate = getCommentsComponent()
            .getOwnComment(hooks.getTestValueProvider().getUserName())
            .getCommentDateText();

    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH);
    String expectedDate = currentDate.format(formatter);

    softAssert.assertEquals(actualDate, expectedDate ,"The date must be the same.");
    }

    @Then("username are displayed")
    public void isUsernameDisplayed(){
        CommentItem ownComment = getCommentsComponent().getOwnComment(hooks.getTestValueProvider().getUserName());
        softAssert.assertTrue(ownComment.getAuthorName().isDisplayed(), "Username must be displayed.");
        softAssert.assertEquals(ownComment.getAuthorNameText(), hooks.getTestValueProvider().getUserName(), "The name must be the same.");
    }

    @Then("avatar are displayed")
    public void isAvatarDisplayed(){
        CommentItem ownComment = getCommentsComponent().getOwnComment(hooks.getTestValueProvider().getUserName());
        softAssert.assertTrue(ownComment.getCommentAvatar().isDisplayed(),"Avatar should be displayed.");
    }

    @Then("total comment count is updated correctly")
    public void isCommentCountUpdate(){
    String countText = getCommentsComponent().getTotalCommentsCount().getText();
    int currentCount = Integer.parseInt(countText.replaceAll("\\D+",""));
    int expectedCount = previousCommentCount +1;

    softAssert.assertEquals(currentCount,expectedCount, "The comment count must be update.");
    }

    @When("remember comment count")
    public void currentCommentCount(){
        String countText = getCommentsComponent().getTotalCommentsCount().getText();
        previousCommentCount = Integer.parseInt(countText.replaceAll("\\D+", ""));
    }

}