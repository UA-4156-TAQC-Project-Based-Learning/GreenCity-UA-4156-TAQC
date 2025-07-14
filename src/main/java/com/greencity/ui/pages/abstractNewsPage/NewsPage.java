package com.greencity.ui.pages.abstractNewsPage;

import com.greencity.ui.pages.CreateEditNewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//div[@class='news-title word-wrap']")
    private WebElement newsTitle;

    private final By editNewsButton = By.xpath("//div[@class='edit-news']");

    private final By deleteButton = By.xpath("//button[contains(@class, 'delete-news-button')]");

    private final By dialogPopUpRoot = By.xpath("//app-dialog-pop-up");

    @FindBy(xpath = "//img[contains(@class, 'news-image-img')]")
    private WebElement newsImage;

    public NewsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Verify Edit News button is visible")
    public boolean isEditNewsButtonVisible() {
        return !driver.findElements(editNewsButton).isEmpty();
    }

    public void clickBackButton() {
        backButton.click();
    }

    @Step("Click EditNews page")
    public CreateEditNewsPage clickEditNewsButton() {

        waitUntilElementVisible(driver.findElement(editNewsButton));
        driver.findElement(editNewsButton).click();
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

    @Step("Verify 'Interesting for you' section is visible")
    public boolean isInterestingSectionVisible() {
        return interestingForYouTitle.isDisplayed();
    }

    public int getRecommendedNewsCount() {
        return recommendedNewsCards.size();
    }

    @Step("Click Delete Button in the News Page")
    public WarningModalDialog clickDeleteButton(){
        driver.findElement(deleteButton).click();
        return new WarningModalDialog(driver, driver.findElement(dialogPopUpRoot));
    }

    public void clickButtonByText(String text) {
        switch (text) {
            case "Edit":
                clickEditNewsButton();
                break;
            case "Delete":
                clickDeleteButton();
                break;
            default:
                throw new RuntimeException("Unsupported text: " + text);
        }
    }
}

