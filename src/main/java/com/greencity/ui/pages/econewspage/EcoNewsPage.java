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
import java.util.stream.Collectors;


@Getter
public class EcoNewsPage extends BasePage {

    @FindBy(xpath = ".//h1[contains(@class, 'main-header')]")
    private WebElement title;

    @FindBy(xpath = ".//button[contains(@class, 'tag-button')]")
    private List<WebElement> tags;

    @FindBy(xpath = "//div[@id='create-button']")
    private WebElement createNewsButton;

    @FindBy(xpath = "//li[contains(@class,'gallery-view-li')]")
    private List<WebElement> newsCards;

    @FindBy(css = ".news-date")
    private List<WebElement> newsDates;

    public EcoNewsPage(WebDriver driver) {
        super(driver);
    }

    public CreateEditNewsPage clickCreateNewsButton() {
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

    public List<String> getNewsDatesText() {
        return newsDates.stream().map(WebElement::getText).collect(Collectors.toList());
    }
}
