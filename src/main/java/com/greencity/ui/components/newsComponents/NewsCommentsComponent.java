package com.greencity.ui.components.newsComponents;



import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class NewsCommentsComponent extends BaseComponent {

    @FindBy(xpath = "//*[contains(@class,'comment-textarea')]")
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

    public void addComment(String text) {
        commentInput.click();
        commentInput.sendKeys(text);
        commentButton.click();
    }

    public int getDisplayedCommentCount() {
        return commentItems.size();
    }

    public String getCommentsTitleText() {
        return commentsTitle.getText().trim();
    }

    public int getTotalCommentsValue() {
        return Integer.parseInt(totalCommentsCount.getText().trim());
    }
}
