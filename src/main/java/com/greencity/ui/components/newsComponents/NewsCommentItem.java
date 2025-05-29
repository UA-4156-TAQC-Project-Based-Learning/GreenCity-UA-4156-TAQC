package com.greencity.ui.components.newsComponents;

import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class NewsCommentItem extends BaseComponent {
    @FindBy(css = ".author-name")
    private WebElement authorName;

    @FindBy(css = ".comment-date-month")
    private WebElement commentDate;

    @FindBy(css = ".comment-text")
    private WebElement commentText;

    @FindBy(css = ".comment-likes img")
    private WebElement likeCommentIcon;

    @FindBy(css = "span.like-amount")
    private WebElement likeCount;

    @FindBy(css = "button.edit")
    private WebElement editButton;

    @FindBy(css = "button.delete")
    private WebElement deleteButton;

    @FindBy(css = "button.reply")
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
        return Integer.parseInt(likeCount.getText().trim());
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


