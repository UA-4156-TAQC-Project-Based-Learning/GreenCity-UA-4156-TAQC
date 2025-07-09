package com.greencity.ui.pages.econewspage;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
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
    @FindBy(xpath = "//ul[contains(@class,'list gallery-view-active')]/li[contains(@class,'gallery-view-li')]")
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

    @Step("Click Create News Button in the Eco News Page")
    public CreateEditNewsPage clickCreateNewsButton() {
        scrollToElement(createNewsButton);
        waitUntilElementClickable(createNewsButton);
        createNewsButton.click();
        return new CreateEditNewsPage(driver);
    }

    @Step("Click First News Page in the Eco News Page")
    public NewsPage clickFirstNewsPage() {
        WebElement firstCard = newsCards.getFirst();
        firstCard.click();
        return new NewsPage(driver);
    }

    public WebElement getCreateButton() {
        return driver.findElement(createButtonBy);
    }

    @Step("Click Create Button in the Eco News Page")
    public CreateEditNewsPage clickCreateButton() {
        getCreateButton().click();
        return new CreateEditNewsPage(driver);
    }

    @Step("Retrieve all news components on Eco News page")
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

    public NewsComponent findNewsByTitle(String title) {
        List<NewsComponent> newsList = this.getNewsComponents();
        for (NewsComponent news : newsList) {
            if (news.getTitleText().equals(title)) {
                return news;
            }
        }
        throw new NoSuchElementException("News with title '" + title + "' not found on the page.");
    }

    public boolean isAnyTagFilterActive() {
        return filteringOptions.stream()
                .anyMatch(tag -> tag.getAttribute("class").contains("active"));
    }

    public List<NewsComponent> getAllNewsComponents() {
        return newsCards.stream()
                .map(el -> new NewsComponent(driver, el))
                .toList();
    }

    public List<String> getAllNewsTitles() {
        return getAllNewsComponents().stream()
                .map(NewsComponent::getTitleText)
                .toList();
    }

}
