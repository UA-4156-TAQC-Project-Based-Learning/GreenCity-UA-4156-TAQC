package com.greencity.ui.components.newsComponents;

import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NewsCommentItem extends BaseComponent {
    @FindBy(xpath = ".//*[@class='author-name']")
    private WebElement authorName;

    @FindBy(xpath = ".//*[contains(@class,'comment-date-month')]")
    private WebElement commentDate;

    @FindBy(xpath = ".//*[contains(@class,'comment-text')]")
    private WebElement commentText;

    @FindBy(xpath = ".//*[contains(@class,'comment-likes')]//img")
    private WebElement likeCommentIcon;

    @FindBy(xpath = "//span[contains(@class,'like-amount')]")
    private WebElement likeCount;

    @FindBy(xpath = "//button[contains(@class,'edit')]")
    private WebElement editButton;

    @FindBy(xpath = "//button[contains(@class,'delete')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//button[contains(@class,'reply')]")
    private WebElement replyButton;

    public NewsCommentItem(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
    }

    public String getAuthorNameText() {
        return authorName.getText().trim();
    }

    public String getCommentDateText() {
        return commentDate.getText().trim();
    }

    public String getCommentText() {
        return commentText.getText().trim();
    }

    public void clickLikeButton() {
        likeCommentIcon.click();
    }

    public int getLikeCount() {
        try {
            String countText = likeCount.getText().trim();
            return countText.isEmpty() ? 0 : Integer.parseInt(countText);
            } catch (NumberFormatException e) {
            return 0;
            }
    }

    public void clickEdit() {
        editButton.click();
    }

    public void clickDelete() {
        deleteButton.click();
    }

    public void clickReply() {
        replyButton.click();
    }
}


