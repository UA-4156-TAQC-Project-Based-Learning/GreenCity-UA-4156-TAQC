package com.greencity.ui.pages.abstractNewsPage;

import com.greencity.ui.pages.CreateEditNewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@Getter
public class NewsPage extends AbstractNewsPage {

    @FindBy(xpath = "//div[contains(@class,'back-button')]//a[contains(@class,'button-link')]")
    private WebElement backButton;

    @FindBy(xpath = "//img[contains(@class,'news_like')]")
    private WebElement likeIcon;

    @FindBy(xpath = "//span[contains(@class,'numerosity_likes')]")
    private WebElement likesCount;

    @FindBy(xpath = "//p[contains(text(),'May be interesting for you')]")
    private WebElement interestingForYouTitle;

    @FindBy(xpath = "//app-news-list-gallery-view")
    private List<WebElement> recommendedNewsCards;

    @FindBy(xpath = "//app-comments-list")
    private WebElement commentsRoot;

    @FindBy(xpath = "//div[@class='edit-news']")
    private WebElement editNewsButton;

    @FindBy(xpath = "//div[@class='news-title word-wrap']")
    private WebElement newsTitle;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    public void clickBackButton() {
        backButton.click();
    }

    public CreateEditNewsPage clickEditNewsButton() {
        editNewsButton.click();
        return new CreateEditNewsPage(driver);
    }

    public void clickLikeIcon() {
        likeIcon.click();
    }

    public int getLikesCountValue() {
        try {
            return Integer.parseInt(likesCount.getText().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
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

