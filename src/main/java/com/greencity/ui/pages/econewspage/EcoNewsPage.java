package com.greencity.ui.pages.econewspage;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;


@Getter
public class EcoNewsPage extends BasePage {

    private final By createButtonBy = By.xpath("//span[contains(@id, 'create-button-text')]");
    @FindBy(xpath = "//h1[contains(@class, 'main-header')]")
    private WebElement title;
    @FindBy(xpath = "//button[contains(@class, 'tag-button')]")
    private List<WebElement> filteringOptions;
    @FindBy(xpath = "//div[@id='create-button']")
    private WebElement createNewsButton;
//    @FindBy(xpath = "//li[contains(@class,'gallery-view-li')]")
    @FindBy(xpath = "//ul[contains(@class,'list gallery-view-active')]")
    private List<WebElement> newsCards;
    @FindBy(css = ".news-date")
    private List<WebElement> newsDates;
    @FindBy(xpath = "//div[@class='header_navigation-menu']")
    private WebElement navigationPanel;
    @FindBy(xpath = "//span[@aria-label='table view']")
    private WebElement galleryView;
    @FindBy(xpath = "//span[@aria-label='list view']")
    private WebElement listView;
    @Getter
    private List<NewsComponent> news;


    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateEditNewsPage clickCreateNewsButton() {
        scrollToElement(createNewsButton);
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
        return new CreateEditNewsPage(driver);
    }

    public NewsPage clickFirstNewsPage() {
        WebElement firstCard = newsCards.getFirst();
        firstCard.click();
        return new NewsPage(driver);
    }

    public WebElement getCreateButton() {
        return driver.findElement(createButtonBy);
    }

    public CreateEditNewsPage clickCreateButton() {
        getCreateButton().click();
        return new CreateEditNewsPage(driver);
    }

    public List<NewsComponent> getNewsComponents() {
        return newsCards.stream().map(el -> new NewsComponent(driver, el)).toList();
    }

    public List<String> getNewsDatesText() {
        return newsDates.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public NewsPage findNewsByTitleAndClick(String title) {
        List<NewsComponent> newsList = this.getNewsComponents();
        for (NewsComponent news : newsList) {
            if (news.getTitleText().equals(title)) {
                news.getTitle().click();
                break;
            }
        }
        return new NewsPage(driver);
    }

}
