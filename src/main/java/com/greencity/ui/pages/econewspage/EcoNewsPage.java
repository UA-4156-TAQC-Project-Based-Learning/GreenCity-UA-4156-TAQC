package com.greencity.ui.pages.econewspage;

import com.greencity.ui.components.NewsComponent;
import com.greencity.ui.pages.BasePage;
import com.greencity.ui.pages.CreateEditNewsPage;
import com.greencity.ui.pages.abstractNewsPage.NewsPage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


@Getter
public class EcoNewsPage extends BasePage {

    @FindBy(xpath = "//h1[contains(@class, 'main-header')]")
    private WebElement title;

    @FindBy(xpath = "//button[contains(@class, 'tag-button')]")
    private List<WebElement> filteringOptions;

    @FindBy(xpath = "//div[@id='create-button']")
    private WebElement createNewsButton;

    @FindBy(xpath = "//li[contains(@class,'gallery-view-li')]")
    private List<WebElement> newsCards;

    @FindBy(xpath = "//div[@class='header_navigation-menu']")
    private WebElement navigationPanel;

    @FindBy(xpath = "//span[@aria-label='table view']")
    private WebElement galleryView;

    @FindBy(xpath = "//span[@aria-label='list view']")
    private WebElement listView;


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

    public List<NewsComponent> getNewsComponents() {
        return newsCards.stream()
                .map(el -> new NewsComponent(driver, el))
                .toList();
    }


}
