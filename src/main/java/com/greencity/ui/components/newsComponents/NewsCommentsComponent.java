package com.greencity.ui.components.newsComponents;

import com.greencity.ui.components.baseComponents.BaseComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.util.List;

@Getter
public class NewsCommentsComponent extends BaseComponent {

    public NewsCommentsComponent(WebDriver driver, WebElement rootElement) {
        super(driver, rootElement);
        PageFactory.initElements(new DefaultElementLocatorFactory(rootElement), this);
    }

    @FindBy(css = ".comment-textarea")
    private WebElement commentInput;

    @FindBy(css = "button.primary-global-button")
    private WebElement commentButton;

    @FindBy(css = "app-comments-list > div.comment-body-wrapper")
    private List<WebElement> commentItems;

    @FindBy(xpath = "//p[text()='Comments']")
    private WebElement commentsTitle;

    @FindBy(css = ".total-count")
    private WebElement totalCommentsCount;

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
