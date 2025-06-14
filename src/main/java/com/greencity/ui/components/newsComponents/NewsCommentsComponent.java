package com.greencity.ui.components.newsComponents;

import com.greencity.ui.components.baseComponents.BaseComponent;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

@Getter
public class NewsCommentsComponent extends BaseComponent {

    @FindBy(xpath = "//div[contains(@class,'comment-textarea') and @contenteditable='true']")
    private WebElement commentInput;
    @FindBy(xpath = "//button[contains(@class,'primary-global-button')]")
    private WebElement commentButton;
    @FindBy(xpath = "//app-comments-list//div[contains(@class,'comment-body-wrapper')]")
    private List<WebElement> commentItems;
    @FindBy(xpath = "//p[text()='Comments']")
    private WebElement commentsTitle;
    @FindBy(xpath = "//*[contains(@class,'total-count')]")
    private WebElement totalCommentsCount;

    public NewsCommentsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    @Step("Add comment: {text}")
    public void addComment(String text) {
        scrollToElement(commentInput);
        commentInput.click();
        commentInput.sendKeys(text);
        commentButton.click();
    }

    @Step("Get number of displayed comments")
    public int getDisplayedCommentCount() {
        return commentItems.size();
    }

    @Step("Get comment section title")
    public String getCommentsTitleText() {
        return commentsTitle.getText().trim();
    }

    @Step("Get total comments value from counter")
    public int getTotalCommentsValue() {
        return Integer.parseInt(totalCommentsCount.getText().trim());
    }

    @Step("Check if element is interactable")
    public boolean isElementInteractable(org.openqa.selenium.WebElement element) {
        try {
            return element.isDisplayed() && element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Scroll to element")
    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

}
