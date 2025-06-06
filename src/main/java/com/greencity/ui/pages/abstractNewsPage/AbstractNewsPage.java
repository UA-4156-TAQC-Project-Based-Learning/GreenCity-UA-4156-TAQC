package com.greencity.ui.pages.abstractNewsPage;

import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public abstract class AbstractNewsPage extends BasePage {

    @FindBy(xpath = "//div[contains(@class,'news-title-container')]")
    protected WebElement title;

    @FindBy(xpath = "//div[contains(@class,'news-info-date')]")
    protected WebElement publicationDate;

    @FindBy(xpath = "//div[contains(@class,'news-info-author')]")
    protected WebElement author;

    @FindBy(xpath = "//img[contains(@class,'news-image-img')]")
    protected WebElement newsImage;

    @FindBy(xpath = "//div[contains(@class,'news-text-content')]")
    protected WebElement contentText;

    @FindBy(xpath = "//img[contains(@class,'news-links-img')]")
    protected List<WebElement> socialMediaIcons;

    @FindBy(xpath = "//div[@class='source-text word-wrap']")
    protected List<WebElement> socialShareSections;

    public AbstractNewsPage(WebDriver driver) {
        super(driver);
    }

    public String getTitleText() {
        return title.getText().trim();
    }

    public String getPublicationDateText() {
        return publicationDate.getText().trim();
    }

    public String getAuthorText() {
        String rawText = author.getText().trim();
        if (rawText.toLowerCase().startsWith("by ")) {
            return rawText.substring(3).trim();  // прибирає "by "
        }
        return rawText;
    }

    public boolean isNewsImageDisplayed() {
        return newsImage.isDisplayed();
    }

    public String getContentText() {
        scrollToElement(contentText);
        return contentText.getText();
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isSocialShareVisible() {
        return socialShareSections.stream().anyMatch(WebElement::isDisplayed);
    }
}