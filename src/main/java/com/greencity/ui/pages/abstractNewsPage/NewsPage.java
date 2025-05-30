package com.greencity.ui.pages.abstractNewsPage;

import com.greencity.ui.components.newsComponents.NewsCommentsComponent;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

@Getter
public class NewsPage extends AbstractNewsPage {

    @FindBy(css = "div.back-button a.button-link")
    private WebElement backButton;

    @FindBy(css = "img.news_like")
    protected WebElement likeIcon;

    @FindBy(css = "span.numerosity_likes")
    protected WebElement likesCount;

    @FindBy(xpath = "//p[contains(text(),'May be interesting for you')]")
    private WebElement interestingForYouTitle;

    @FindBy(css = "app-news-list-gallery-view")
    private List<WebElement> recommendedNewsCards;

    @FindBy(css = "app-comments-list")
    private WebElement commentsRoot;

    private final NewsCommentsComponent commentsComponent;

    public NewsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        this.commentsComponent = new NewsCommentsComponent(driver, commentsRoot);
    }


    public void clickBackButton() {
        backButton.click();
    }

    public void clickLikeIcon() {
        likeIcon.click();
    }

    public int getLikesCountValue() {
        return Integer.parseInt(likesCount.getText().trim());
    }

    public boolean isLikeIconDisplayed() {
        return likeIcon.isDisplayed();
    }

    public boolean isInterestingSectionVisible() {
        return interestingForYouTitle.isDisplayed();
    }

    public int getRecommendedNewsCount() {
        return recommendedNewsCards.size();
    }
}

