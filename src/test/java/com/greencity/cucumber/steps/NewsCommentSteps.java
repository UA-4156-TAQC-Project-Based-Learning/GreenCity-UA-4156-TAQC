package com.greencity.cucumber.steps;

import com.greencity.cucumber.hooks.Hooks;
import com.greencity.ui.components.newsComponents.NewsCommentItem;
import com.greencity.ui.components.newsComponents.NewsCommentsComponent;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import com.greencity.ui.pages.homepage.HomePage;
import com.greencity.utils.TestValueProvider;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class NewsCommentSteps {

    private final Hooks hooks;
    private final SoftAssert softAssert;
    private List<WebElement> commentElements;
    private String loggedInUserName;

    public NewsCommentSteps(Hooks hooks) {
        this.hooks = hooks;
        this.softAssert = hooks.getSoftAssert();
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
        commentElements = new NewsCommentsComponent(
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
                new NewsCommentItem(hooks.getDriver(), el).getAuthorNameText().trim().equalsIgnoreCase(currentUser));
        softAssert.assertTrue(found, "Expected at least one comment by the logged-in user: " + currentUser);
    }

    @Then("at least one comment is authored by another user")
    public void atLeastOneCommentByAnotherUser() {
        String currentUser = hooks.getTestValueProvider().getLocalStorageName();
        boolean found = commentElements.stream().anyMatch(el ->
                !new NewsCommentItem(hooks.getDriver(), el).getAuthorNameText().trim().equalsIgnoreCase(currentUser));
        softAssert.assertTrue(found, "Expected at least one comment by another user (not " + currentUser + ")");
    }

    @Then("the edit icon is visible only for comments authored by the logged-in user")
    public void editIconVisibleOnlyForOwnComments() {
        commentElements.forEach(el -> {
            NewsCommentItem item = new NewsCommentItem(hooks.getDriver(), el);
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

}