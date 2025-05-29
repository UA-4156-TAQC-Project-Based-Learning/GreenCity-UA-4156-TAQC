package com.greencity.ui.pages.abstractNewsPage;

import com.greencity.ui.pages.BasePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public abstract class AbstractNewsPage extends BasePage {

    protected WebDriver driver;

    @FindBy(css = ".news-title")
    protected WebElement title;

    @FindBy(css = ".news-info-date")
    protected WebElement publicationDate;

    @FindBy(css = ".news-info-author")
    protected WebElement author;

    @FindBy(css = "img.news-image-img")
    protected WebElement newsImage;

    @FindBy(css = ".news-text")
    protected WebElement contentText;

    @FindBy(css = ".news-links-images .news-links-img")
    protected List<WebElement> socialMediaIcons;

    @FindBy(css = ".source-text")
    protected WebElement socialShareSection;

    public AbstractNewsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTitleText() {
        return title.getText().trim();
    }

    public String getPublicationDateText() {
        return publicationDate.getText().trim();
    }

    public String getAuthorText() {
        return author.getText().trim();
    }

    public boolean isNewsImageDisplayed() {
        return newsImage.isDisplayed();
    }

    public String getContentText() {
        return contentText.getText().trim();
    }

    public boolean isSocialShareVisible() {
        return socialShareSection.isDisplayed();
    }
}