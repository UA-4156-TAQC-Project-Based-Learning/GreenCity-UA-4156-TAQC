package com.greencity.ui.pages.econewspage;

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

    @FindBy(xpath = ".//h1[contains(@class, 'main-header')]")
    private WebElement title;

    @FindBy(xpath = ".//button[contains(@class, 'tag-button')]")
    private List<WebElement> tags;

    @FindBy(xpath = "//div[@id='create-button']")
    private WebElement createNewsButton;

    @FindBy(xpath = "//ul[contains(@class,'list gallery-view-active')]")
    private List<WebElement> newsCards;


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



}
